package mvc;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import mvc.controller.MainController;
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
        stage.setScene(new Scene(rootNode));
        stage.setTitle("GateKeeper");
        stage.show();
        MainController.getInstance().switchScene(ScreenType.LOGIN);
    }
}
