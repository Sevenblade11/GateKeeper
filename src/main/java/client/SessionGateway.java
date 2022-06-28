package client;

import mvc.model.Session;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * Deals with log on credentials.
 */
public class SessionGateway {

    private static final Logger LOGGER = LogManager.getLogger();

    private static final String URL = "http://127.0.0.1:8080";

    public static Session authenticate(String userName, String hashedPassword) {
        CloseableHttpResponse response = null;
        CloseableHttpClient httpclient = null;

        try {
            httpclient = HttpClients.createDefault();

            JSONObject credentials = new JSONObject();
            credentials.put("password", hashedPassword);
            credentials.put("username", userName);

            HttpPost loginRequest = new HttpPost(URL + "/login");
            StringEntity reqEntity = new StringEntity(credentials.toString());

            loginRequest.setEntity(reqEntity);
            loginRequest.setHeader("Accept", "application/json");
            loginRequest.setHeader("Content-type", "application/json");
            response = httpclient.execute(loginRequest);

            return sessionResponse(response);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private static Session sessionResponse(CloseableHttpResponse response) throws IOException {

        switch (response.getStatusLine().getStatusCode()) {
            case 200 -> {
                String strResponse = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
                JSONObject responseJson = new JSONObject(strResponse);
                Session session = new Session(responseJson.getString("sessionToken"), responseJson.getInt("userId"));
                session.setUrl(URL);
                LOGGER.info("Successfully created session token.");
                return session;
            }
            case 401 -> LOGGER.error("Invalid credentials.");
            default -> LOGGER.error("Unknown error has occurred.");
        }
        return null;
    }
}
