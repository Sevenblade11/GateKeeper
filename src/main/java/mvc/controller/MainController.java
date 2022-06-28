package mvc.controller;

import client.SessionGateway;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import mvc.model.Account;
import mvc.model.ScreenType;
import mvc.model.Session;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controls the switching between views. Singleton object.
 *
 * Keeps track of any important information for views, such as session token.
 */
public class MainController implements Initializable {

    private static final Logger LOGGER = LogManager.getLogger();
    private static MainController instance = null;
    private ScreenType screenType;
    private ObservableList<Account> accountList;
    private Session session;

    @FXML
    private BorderPane rootPane;

    private MainController(){

    }

    public static MainController getInstance(){
        if(instance == null)
            instance = new MainController();
        return instance;
    }

    /**
     * Switches the views based on screen type. Various screen types will require different
     * parameters.
     * Login - No additional parameters needed.
     * Home - No additional parameters needed.
     * Detail - Specific account to show details about.
     * @param screenType The screen needed to swap to.
     * @param args Any information that the screen(s) need.
     */
    public void switchScene(ScreenType screenType, Object ... args){
        FXMLLoader loader = null;
        switch(screenType){
            case LOGIN -> {
                loader = new FXMLLoader(this.getClass().getResource("/LogIn.fxml"));
                loader.setController(new LogInController());
            }
            case HOME -> {
                loader = new FXMLLoader(this.getClass().getResource("/HomeView.fxml"));
                loader.setController(new HomeViewController(accountList));
            }
            case DETAIL -> {
                if(args[0] instanceof Account) {
                    loader = new FXMLLoader(this.getClass().getResource("/DetailView.fxml"));
                    loader.setController(new DetailViewController((Account) args[0], accountList));
                }
            }
        }
        if(loader != null)
            showWindow(loader);
    }

    private void showWindow(FXMLLoader loader){
        Parent rootNode;
        try{
            rootNode = loader.load();
            rootPane.setCenter(rootNode);
        }
        catch (IOException e){
            LOGGER.error(e.getMessage());
        }
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public void setAccountList(ObservableList<Account> accountList){
        this.accountList = accountList;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
