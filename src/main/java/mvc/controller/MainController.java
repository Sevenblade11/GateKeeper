package mvc.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import mvc.model.ScreenType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    private static final Logger LOGGER = LogManager.getLogger();
    private static MainController instance = null;
    private ScreenType screenType;

    @FXML
    private BorderPane rootPane;

    private MainController(){

    }

    public static MainController getInstance(){
        if(instance == null)
            instance = new MainController();
        return instance;
    }

    public void switchScene(ScreenType screenType, Object ... args){
        FXMLLoader loader = null;
        switch(screenType){
            case LOGIN -> {
                loader = new FXMLLoader(this.getClass().getResource("/LogIn.fxml"));
                loader.setController(new LogInController());
            }
            case HOME -> {
                loader = new FXMLLoader(this.getClass().getResource("/HomeView.fxml"));
                loader.setController(new HomeViewController());
            }
            case DETAIL -> {
                loader = new FXMLLoader(this.getClass().getResource("/DetailView.fxml"));
                loader.setController(new DetailViewController());
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
