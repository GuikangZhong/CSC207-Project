package project.gui;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HRMenuController implements Initializable {
    @FXML
    private TreeView<String> options;
    @FXML
    private Label companyName;

    public static HRMenu getMenu() {
        return menu;
    }

    static private HRMenu menu = null;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(null == menu){
            menu = new HRMenu();
        }
        companyName.setText(Main.user.getCompany());
        options.setRoot(menu.getOptions());
    }

    public void selectItems(MouseEvent event) throws IOException {
        TreeItem<String> item = options.getSelectionModel().getSelectedItem();
        menu.selectItem(this.getClass(),event, item);


    }

    public void exit(Event event) throws IOException{
        SceneSwitcher.switchScene(this.getClass(), event, "Main.fxml");
    }
}
