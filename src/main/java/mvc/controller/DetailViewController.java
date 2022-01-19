package mvc.controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import mvc.model.Account;
import mvc.model.EncryptionDecryption;
import mvc.model.PasswordGenerator;
import mvc.model.ScreenType;

import java.net.URL;
import java.util.ResourceBundle;

public class DetailViewController implements Initializable {

    private Account selectedAccount;
    private ObservableList<Account> accountList;

    @FXML
    private TextField accountNameField;
    @FXML
    private TextField usernameField;
    @FXML
    private TextField passwordField;
    @FXML
    private TextField websiteField;
    @FXML
    private TextField emailField;

    @FXML
    private Button createUpdateButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        resetView();
    }

    public DetailViewController(Account account, ObservableList<Account> accountList){
        selectedAccount = account;
        this.accountList = accountList;
    }

    @FXML
    private void cancel(){
        MainController.getInstance().switchScene(ScreenType.HOME);
    }

    @FXML
    private void generatePassword(){
        String password = PasswordGenerator.generatePassword(15, true, true);
        passwordField.setText(password);
        checkFields();
    }

    @FXML
    private void createUpdate(){
        if(selectedAccount.getAccountName().equals(""))
            createAccount();
        else
            updateAccount();
    }

    @FXML
    private void checkFields(){
        createUpdateButton.setDisable(usernameField.getText().equals("") || passwordField.getText().equals("") ||
                websiteField.getText().equals(""));
    }

    private void createAccount(){
        Account newAccount = new Account(usernameField.getText(), accountNameField.getText(),
                EncryptionDecryption.encrypt(passwordField.getText()), websiteField.getText(), emailField.getText());
        accountList.add(newAccount);
        MainController.getInstance().switchScene(ScreenType.HOME);
    }

    private void updateAccount(){
        selectedAccount.setAccountName(accountNameField.getText());
        selectedAccount.setEmail(emailField.getText());
        selectedAccount.setUsername(usernameField.getText());
        selectedAccount.setWebsite(websiteField.getText());
        selectedAccount.setEncryptPassword(EncryptionDecryption.encrypt(passwordField.getText()));
        MainController.getInstance().switchScene(ScreenType.HOME);
    }

    private boolean isFormatCorrect(){
        return false;
    }

    private void resetView(){
        if(selectedAccount.getAccountName().equals("")) {
            accountNameField.setText("");
            usernameField.setText("");
            passwordField.setText("");
            websiteField.setText("");
            emailField.setText("");
            createUpdateButton.setText("Create");
            createUpdateButton.setDisable(true);
        }
        else {
            accountNameField.setText(selectedAccount.getAccountName());
            usernameField.setText(selectedAccount.getUsername());
            passwordField.setText(EncryptionDecryption.decrypt(selectedAccount.getEncryptPassword()));
            websiteField.setText(selectedAccount.getWebsite());
            emailField.setText(selectedAccount.getEmail());
            createUpdateButton.setText("Update");
            createUpdateButton.setDisable(false);
        }
    }


}
