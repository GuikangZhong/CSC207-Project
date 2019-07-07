package project.gui;

import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneSwitcher {

    public static void switchScene(ApplicationController current, Event event, String sceneName) throws IOException {
        FXMLLoader loader = new FXMLLoader(current.getClass().getResource(sceneName));
        Parent main = loader.load();
        ApplicationController controller = loader.<ApplicationController>getController();

        Scene mainScene = new Scene(main);
        Stage loginWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
        loginWindow.setScene(mainScene);
        loginWindow.show();
        controller.initFromController(current);
        controller.postInit();
        assert controller.getSystem()!=null;
    }
}
