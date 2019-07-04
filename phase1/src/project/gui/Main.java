package project.gui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import project.application.Company;
import project.system.MainSystem;
import project.user.*;

import java.io.IOException;

public class Main extends Application {

    static MainSystem system;
    static String autoSaveFileName = "auto.ser";

    public static void main(String[] args) throws IOException, ClassNotFoundException{
//        MainSystem system = MainSystem.loadFromFile("C:\\Users\\Eric Zhong\\group_0002\\phase1\\src\\database\\autosave.csv");
        // test case
        try {
            system = MainSystem.loadFromFile(autoSaveFileName);
        } catch (IOException e){
            system = new MainSystem();
        } catch (ClassNotFoundException e){
            throw new ClassNotFoundException("Class not found");
        }
        launch(args);
        system.serializeToFile(autoSaveFileName);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
        primaryStage.setTitle("App");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public void loginButton(ActionEvent event) throws IOException {
        SceneSwitcher.switchScene(this.getClass(), event, "LoginPage.fxml");

    }

    public void signUpButton(ActionEvent event) throws IOException{
        SceneSwitcher.switchScene(this.getClass(), event, "Type.fxml");
    }

}
