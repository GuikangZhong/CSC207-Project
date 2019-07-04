package project.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import project.user.User;

import java.io.IOException;

public class LoginController {

    @FXML
    private TextField usernameInput;
    @FXML
    private TextField passwordInput;

    public void returnButton(ActionEvent event) throws IOException {
        Parent main = FXMLLoader.load(getClass().getResource("Main.fxml"));
        Scene mainScene = new Scene(main);
        Stage loginWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
        loginWindow.setScene(mainScene);
        loginWindow.show();
    }

    public void loginButton(ActionEvent event) throws IOException {
        User user = Main.system.login(usernameInput.getText(), passwordInput.getText());
        SceneSwitcher.switchScene(this.getClass(), event, "ApplicantMenu.fxml");
//        if (user == null){
//            Stage window = new Stage();
//
//            //Block events to other windows
//            window.initModality(Modality.APPLICATION_MODAL);
//            window.setTitle("Warning");
//            window.setHeight(100.0);
//            window.setWidth(250.0);
//
//            Label label = new Label("User does not exist");
//            Button closeButton = new Button("Close");
//            closeButton.setOnAction(e -> window.close());
//
//            VBox layout = new VBox(10);
//            layout.getChildren().addAll(label, closeButton);
//            layout.setAlignment(Pos.CENTER);
//
//            //Display window and wait for it to be closed before returning
//            Scene scene = new Scene(layout);
//            window.setScene(scene);
//            window.showAndWait();
//        }
//        else{
//            SceneSwitcher.switchScene(this.getClass(), event, "ApplicantMenu.fxml");
//        }

    }
}
