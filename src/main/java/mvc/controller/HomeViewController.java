package mvc.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import mvc.model.ScreenType;

import java.net.URL;
import java.util.ResourceBundle;

public class HomeViewController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    private void logOut(){
        MainController.getInstance().switchScene(ScreenType.LOGIN);
    }


}
