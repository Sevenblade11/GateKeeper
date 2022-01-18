package mvc.controller;

import javafx.beans.Observable;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import mvc.model.Account;
import mvc.model.ScreenType;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class HomeViewController implements Initializable {

    private ObservableList<Account> accountList;

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        accountColumn.setCellValueFactory(cellData -> cellData.getValue().accountNameProperty());
        usernameColumn.setCellValueFactory(cellData -> cellData.getValue().usernameProperty());
        passwordColumn.setCellValueFactory(cellData -> cellData.getValue().hashedPasswordProperty());
        emailColumn.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
        websiteColumn.setCellValueFactory(cellData -> cellData.getValue().websiteProperty());

        accountListView.setItems(accountList);
    }

    public HomeViewController(ObservableList<Account> accountList){
        accountListView = new TableView<>();
        this.accountList = accountList;
    }

    @FXML
    private void logOut(){
        MainController.getInstance().switchScene(ScreenType.LOGIN);
    }

}
