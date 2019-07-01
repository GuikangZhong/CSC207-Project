package project;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.xml.soap.Text;
import java.io.IOException;
import org.json.*;

public class Main extends Application {
    public Main() {
        //Optional constructor
    }

    @Override
    public void init() {

    }
    public void handle(){
        System.out.println("a");
    }
    @Override
    public void start(Stage primaryStage) {
//        final StackPane root = new StackPane();
//        final Button login = new Button();
//        // Setting text to button
//        login.setText("Log in");
//        final Button signup = new Button();
//        // Setting text to button
//        signup.setText("Sign up");
//        HBox passwordBox = new HBox();
//        PasswordField password = new PasswordField();
//        passwordBox.getChildren().addAll(new Label("Password"), password);
//        passwordBox.setSpacing(10);
//
//        HBox usernameBox = new HBox();
//        TextField username = new TextField("");
//        usernameBox.getChildren().addAll(new Label("Username"), username);
//        usernameBox.setSpacing(10);
//
//        HBox hbox = new HBox();
//        hbox.getChildren().addAll(login, signup);
//        VBox vbox = new VBox();
//        vbox.getChildren().addAll(usernameBox, passwordBox, hbox);
//        root.getChildren().addAll(vbox);
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Window.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void stop() {

    }


    public static void main(String[] arguments) {

    }
}
