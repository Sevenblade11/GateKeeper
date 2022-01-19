package mvc.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import mvc.model.ScreenType;

import java.net.URL;
import java.util.ResourceBundle;

public class LogInController implements Initializable {

    @FXML
    private Button logInButton;

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        resetView();
    }

    @FXML
    private void logIn(){
        if(!(usernameField.getText().equals("") || passwordField.getText().equals("")))
            MainController.getInstance().switchScene(ScreenType.HOME);
    }

    @FXML
    private void checkLogInStatus(){
        if(!(usernameField.getText().equals("") || passwordField.getText().equals("")))
            logInButton.setStyle("-fx-background-color: #a3d9c9");
        else
            logInButton.setStyle("-fx-background-color: grey");
    }

    private void resetView(){
        usernameField.setText("");
        usernameField.setPromptText("Username");

        passwordField.setText("");
        passwordField.setPromptText("Password");

        logInButton.setStyle("-fx-background-color: grey");
    }

}
