package project.gui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ApplicantMenuController implements Initializable {
    @FXML
    private TreeView<String> options;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TreeItem<String> option = new TreeItem<>("Option");
        TreeItem<String> document = new TreeItem<>("Document");
        TreeItem<String> jobPosting = new TreeItem<>("Job posting");
        TreeItem<String> application = new TreeItem<>("Application");
        TreeItem<String> history = new TreeItem<>("Your history");
        TreeItem<String> exit = new TreeItem<>("Sign out");


        option.getChildren().addAll(document, jobPosting, application, history, exit);
        option.setExpanded(true);

        options.setRoot(option);
    }

    public void selectItems(MouseEvent event) throws IOException{
        TreeItem<String> item = options.getSelectionModel().getSelectedItem();
        if (item.getValue().equals("Sign out")){
            SceneSwitcher.switchScene(this.getClass(), event, "Main.fxml");
        }
    }
}
