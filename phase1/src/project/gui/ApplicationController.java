package project.gui;

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

public abstract class ApplicationController implements Initializable  {
    @FXML
    protected TreeView<String> options;
    public ApplicationController(){

    }
    public ApplicationController(ApplicationController other){
        initFromController(other);
    }
    public MainSystem getSystem() {
        return system;
    }

    public User getUser() {
        return user;
    }

    public Menu getMenu() {
        return menu;
    }

    protected MainSystem system;

    public void setUser(User user) {
        this.user = user;
    }

    protected User user;
    protected Menu menu;

    void initFromController(ApplicationController other){
        system = other.system;
        user = other.user;
        menu = other.menu;
        if(getMenu()!=null && options != null)
            options.setRoot(getMenu().getOptions());
    }

    // Don't ever call it outside main
    void setSystem(MainSystem system){
        this.system = system;
    }

    void setMenu(Menu menu){
        this.menu = menu;
    }

    public void selectItems(MouseEvent event) throws IOException {
        TreeItem<String> item = options.getSelectionModel().getSelectedItem();
        if(item!=null)
            getMenu().switchScene(this,event, item.getValue());
    }

    @Override
    public final void initialize(URL location, ResourceBundle resources) {
        if(getMenu()!=null)
            options.setRoot(getMenu().getOptions());

    }

    void postInit(){
        assert system != null;
    }

    static void showModal(String text){
        Stage window = new Stage();

        //Block events to other windows
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Warning");
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
        window.showAndWait();
    }

    static void Assert(boolean value){
        if(!value){
            throw new RuntimeException("");
        }
    }
}
