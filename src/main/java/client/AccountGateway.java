package client;

import mvc.model.Session;

public class AccountGateway {
    private final String url;
    private final Session session;

    public AccountGateway(String url, Session session){
        this.url = url;
        this.session = session;
    }

    

}
