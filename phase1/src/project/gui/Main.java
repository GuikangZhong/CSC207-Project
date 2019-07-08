package project.gui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import project.application.Company;
import project.system.MainSystem;
import project.user.*;
import project.utils.Logging;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Logger;


public class Main extends Application {


    static String autoSaveFileName = "auto.ser";
    static private Logger logger = Logging.getLogger();
    private static MainSystem system;
    public static void main(String[] args) throws IOException, ClassNotFoundException{
        try {
            system = MainSystem.loadFromFile(autoSaveFileName);
        } catch (IOException e){
            logger.info("New system");
            system = new MainSystem();


        } catch (ClassNotFoundException e){
            throw new ClassNotFoundException("Class not found");
        }
        launch(args);
        system.serializeToFile(autoSaveFileName);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Main.fxml"));
        Parent root = loader.load();
        loader.<ApplicationController>getController().setSystem(system);
        primaryStage.setTitle("App");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
