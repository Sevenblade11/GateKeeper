package mvc.controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import mvc.model.Account;
import mvc.model.ScreenType;

import java.net.URL;
import java.util.ResourceBundle;

public class HomeViewController implements Initializable {

    private ObservableList<Account> accountList;
    private Account selectedAccount;

    @FXML
    private TableView<Account> accountListView;
    @FXML
    private TableColumn<Account, String> accountColumn;
    @FXML
    private TableColumn<Account, String> usernameColumn;
    @FXML
    private TableColumn<Account,String> passwordColumn;
    @FXML
    private TableColumn<Account,String> emailColumn;
    @FXML
    private TableColumn<Account,String> websiteColumn;

    @FXML
    private Button deleteButton;
    @FXML
    private Button editButton;

    public HomeViewController(ObservableList<Account> accountList){
        accountListView = new TableView<>();
        this.accountList = accountList;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        accountColumn.setCellValueFactory(cellData -> cellData.getValue().accountNameProperty());
        usernameColumn.setCellValueFactory(cellData -> cellData.getValue().usernameProperty());
        passwordColumn.setCellValueFactory(cellData -> cellData.getValue().encryptPasswordProperty());
        emailColumn.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
        websiteColumn.setCellValueFactory(cellData -> cellData.getValue().websiteProperty());

        accountListView.setItems(accountList);
        disableButtons();
    }

    @FXML
    private void selectAccount(){
        try {
            TablePosition pos = accountListView.getSelectionModel().getSelectedCells().get(0);
            selectedAccount = accountListView.getItems().get(pos.getRow());
            enableButtons();
        }
        catch(IndexOutOfBoundsException ignored){}
    }

    @FXML
    private void deleteAccount(){
        accountList.remove(selectedAccount);
        accountListView.refresh();
        disableButtons();
    }

    @FXML
    private void createAccount(){
        MainController.getInstance().switchScene(ScreenType.DETAIL, new Account("","","","",""));
    }

    @FXML
    private void editAccount(){
        MainController.getInstance().switchScene(ScreenType.DETAIL, selectedAccount);
    }

    @FXML
    private void logOut(){
        MainController.getInstance().switchScene(ScreenType.LOGIN);
    }

    private void disableButtons(){
        deleteButton.setDisable(true);
        editButton.setDisable(true);
    }

    private void enableButtons(){
        deleteButton.setDisable(false);
        editButton.setDisable(false);
    }

}
