package mvc.controller;

import config.ConfigManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import mvc.model.EncryptionDecryption;
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

    @FXML
    private CheckBox saveUsername;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        resetView();
    }

    @FXML
    private void logIn(){
        if(!(usernameField.getText().isBlank() || passwordField.getText().isBlank())) {
            MainController.getInstance().switchScene(ScreenType.HOME);
        }
    }

    @FXML
    private void saveUsernameChecked(){
        if(saveUsername.isSelected())
            ConfigManager.writeConfig("username", usernameField.getText());
        else
            ConfigManager.removeProperty("username");
    }

    @FXML
    private void checkUsername(){
        if(saveUsername.isSelected()) {
            saveUsername.setSelected(false);
            saveUsernameChecked();
        }
        checkLogInStatus();
    }

    @FXML
    private void checkLogInStatus(){
        if(!(usernameField.getText().isBlank() || passwordField.getText().isBlank()))
            logInButton.setStyle("-fx-background-color: #a3d9c9");
        else
            logInButton.setStyle("-fx-background-color: grey");
    }

    private void resetView(){
        passwordField.setText("");
        passwordField.setPromptText("Password");

        logInButton.setStyle("-fx-background-color: grey");

        if(!(ConfigManager.readConfig("username") == null)){
            saveUsername.setSelected(true);
            usernameField.setText(ConfigManager.readConfig("username"));
        }
        else{
            usernameField.setText("");
            usernameField.setPromptText("Username");
        }
    }

}
