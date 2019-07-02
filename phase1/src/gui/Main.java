package gui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
        primaryStage.setTitle("App");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public void loginButton(ActionEvent event) throws IOException {
        Parent loginPage = FXMLLoader.load(getClass().getResource("LoginPage.fxml"));
        Scene loginScene = new Scene(loginPage);
        Stage loginWindow = (Stage)((Node)event.getSource()).getScene().getWindow();
        loginWindow.setScene(loginScene);
        loginWindow.show();

    }

    public void signUpButton(ActionEvent event) throws IOException{
        Parent loginPage = FXMLLoader.load(getClass().getResource("SignUpPage.fxml"));
        Scene loginScene = new Scene(loginPage);
        Stage loginWindow = (Stage)((Node)event.getSource()).getScene().getWindow();
        loginWindow.setScene(loginScene);
        loginWindow.show();
    }

}
