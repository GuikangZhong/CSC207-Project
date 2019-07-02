package project.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class InterviewerMenuController implements Initializable {
    @FXML
    private TreeView<String> options;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TreeItem<String> option = new TreeItem<>("Option");
        TreeItem<String> interview = new TreeItem<>("Upcoming interviews");
        TreeItem<String> history = new TreeItem<>("Your history");
        TreeItem<String> exit = new TreeItem<>("Sign out");


        option.getChildren().addAll(interview, history, exit);
        option.setExpanded(true);

        options.setRoot(option);
    }

    public void selectItems(MouseEvent event) throws IOException {
        TreeItem<String> item = options.getSelectionModel().getSelectedItem();
        if (item.getValue().equals("Sign out")){
            Parent main = FXMLLoader.load(getClass().getResource("Main.fxml"));
            Scene mainScene = new Scene(main);
            Stage loginWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
            loginWindow.setScene(mainScene);
            loginWindow.show();
        }
    }
}

