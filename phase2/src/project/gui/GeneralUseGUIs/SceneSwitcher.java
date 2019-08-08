package project.gui.GeneralUseGUIs;

import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.function.BiConsumer;

public class SceneSwitcher {

    public static void switchScene(ApplicationController current, Event event, String sceneName) throws IOException {
        switchScene(current, event, sceneName, (a, b) -> {
        });

    }

    private static <T extends ApplicationController, U extends ApplicationController> T
    switchHelper(U current, Event event, String sceneName)
            throws IOException {
        FXMLLoader loader = new FXMLLoader(current.getClass().getResource(sceneName));
        Parent main = loader.load();
        T controller = loader.<T>getController();
        ApplicationController.Assert(current.getSystem() != null);
        Scene mainScene = new Scene(main);
        Stage loginWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
        loginWindow.setScene(mainScene);
        loginWindow.show();
        controller.initFromController((U) current);
        ApplicationController.Assert(controller.getSystem() != null);
        return controller;
    }

    public static <NextT extends ApplicationController, CurrentT extends ApplicationController>
    void switchScene(CurrentT current, Event event, String sceneName,
                     BiConsumer<NextT, CurrentT> callback) throws IOException {
        NextT controller = switchHelper(current, event, sceneName);
        callback.accept(controller, current);
        controller.postInit();
        ApplicationController.Assert(controller.getSystem() != null);
    }
}
