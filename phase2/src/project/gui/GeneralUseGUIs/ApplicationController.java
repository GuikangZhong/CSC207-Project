package project.gui.GeneralUseGUIs;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import project.system.MainSystem;
import project.user.User;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;

public abstract class ApplicationController implements Initializable {
    @FXML
    protected TreeView<String> options;
    protected MainSystem system;
    protected User user;
    protected Menu menu;

    public ApplicationController() {

    }

    public ApplicationController(ApplicationController other) {
        initFromController(other);
    }

    public static void showModal(Consumer<Stage> consumer) {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        consumer.accept(window);
        window.showAndWait();
    }

    public static void showModal(String text) {
        showModal("Warning", text);
    }

    public static void showModal(String title, String text) {
        showModal(window -> {
            //Block events to other windows

            window.setTitle(title);
            window.setHeight(100.0);
            window.setWidth(300.0);

            Label label = new Label(text);
            Button closeButton = new Button("Close");
            closeButton.setOnAction(e -> window.close());

            VBox layout = new VBox(10);
            layout.getChildren().addAll(label, closeButton);
            layout.setAlignment(Pos.CENTER);

            //Display window and wait for it to be closed before returning
            Scene scene = new Scene(layout);
            window.setScene(scene);

        });
    }

    static void Assert(boolean value) {
        if (!value) {
            throw new RuntimeException("");
        }
    }

    public MainSystem getSystem() {
        return system;
    }

    // Don't ever call it outside main
    void setSystem(MainSystem system) {
        this.system = system;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Menu getMenu() {
        return menu;
    }

    void setMenu(Menu menu) {
        this.menu = menu;
    }

    void initFromController(ApplicationController other) {
        system = other.system;
        user = other.user;
        menu = other.menu;
        if (getMenu() != null && options != null)
            options.setRoot(getMenu().getOptions());
    }

    public void selectItems(MouseEvent event) throws IOException {
        TreeItem<String> item = options.getSelectionModel().getSelectedItem();
        if (item != null)
            getMenu().switchScene(this, event, item.getValue());
    }

    @Override
    public final void initialize(URL location, ResourceBundle resources) {
        if (getMenu() != null)
            options.setRoot(getMenu().getOptions());

    }

    public void postInit() {
        assert system != null;
    }
}
