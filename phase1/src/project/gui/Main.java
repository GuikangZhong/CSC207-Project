package project.gui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import project.application.Company;
import project.system.MainSystem;
import project.user.*;

import java.io.IOException;

public class Main extends Application {

    static MainSystem system;
    static Company company;
    static ApplicantManager applicantManager;
    static HRManager hrManager;
    static InterviewerManager interviewerManager;

    public static void main(String[] args) throws IOException, ClassNotFoundException{
//        MainSystem system = MainSystem.loadFromFile("C:\\Users\\Eric Zhong\\group_0002\\phase1\\src\\database\\autosave.csv");
        // test case
        system = new MainSystem();
        company = new Company("Microsoft", system);
        applicantManager = new ApplicantManager(system);
        hrManager = new HRManager(system, company);
        interviewerManager = new InterviewerManager(system, company);
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
