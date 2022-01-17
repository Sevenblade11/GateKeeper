package mvc.controller;

import javafx.fxml.Initializable;
import mvc.model.ScreenType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    private static final Logger LOGGER = LogManager.getLogger();
    private static MainController instance = null;
    private ScreenType screenType;

    private MainController(){

    }

    private static MainController getInstance(){
        if(instance == null)
            instance = new MainController();
        return instance;
    }

    public void switchScene(ScreenType screenType, Object ... args){
        switch(screenType){

            case LOGIN -> {
            }
            case HOME -> {
            }
            case DETAIL -> {
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
