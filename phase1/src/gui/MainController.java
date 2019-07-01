package gui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import java.io.IOException;

public class MainController {

    public void loginButton(ActionEvent event) throws IOException {
        Parent loginPage = FXMLLoader.load(getClass().getResource("LoginPage.fxml"));
        Scene loginScene = new Scene(loginPage);
        Stage loginWindow = (Stage)((Node)event.getSource()).getScene().getWindow();
        loginWindow.setScene(loginScene);
        loginWindow.show();

    }

    public void signUpButton(){
        System.out.println("User signing up...");
    }
}
