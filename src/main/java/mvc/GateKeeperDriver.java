package mvc;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import mvc.controller.MainController;
import mvc.model.Account;
import mvc.model.PasswordGenerator;
import mvc.model.ScreenType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GateKeeperDriver extends Application {
    public static final Logger LOGGER = LogManager.getLogger();

    public static void main(String[] args) {
        launch(args);

    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/MainView.fxml"));
        loader.setController(MainController.getInstance());
        Parent rootNode = loader.load();
        Scene scene = new Scene(rootNode);
        stage.setScene(scene);
        stage.setOpacity(0.98);

        stage.setTitle("GateKeeper");
        stage.show();
        MainController.getInstance().setAccountList(getList());
        MainController.getInstance().switchScene(ScreenType.LOGIN);

    }

    public ObservableList<Account> getList(){
        ObservableList<Account> accountList = FXCollections.observableArrayList(
                new Account("bob123", "bob", "2398uoweijf", "gmail.com", "bob123@gmail.com"),
                new Account("kelly4", "kjen", "K05fjs", "battle.net", "kellyjen81@yahoo.com")
                );
        return accountList;
    }
}
