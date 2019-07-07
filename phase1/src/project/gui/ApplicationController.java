package project.gui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseEvent;
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

    }
}
