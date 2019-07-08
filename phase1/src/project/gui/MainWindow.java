package project.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import project.utils.Logging;

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ResourceBundle;
import java.util.logging.Logger;

public class MainWindow extends ApplicationController implements Initializable {
    @FXML
    private TextField companyName;


    public void loginButton(ActionEvent event) throws IOException {
        SceneSwitcher.switchScene(this, event, "LoginPage.fxml");

    }

    public void signUpButton(ActionEvent event) throws IOException {
        SceneSwitcher.switchScene(this, event, "Type.fxml");
    }

    public void addCompanyButton(ActionEvent event) throws IOException {
        SceneSwitcher.switchScene(this, event, "AddCompany.fxml");
    }

    static private Logger logger = Logging.getLogger();
    public void addCompanyConfirmButton(ActionEvent event) throws IOException {
        if (companyName.getText() != null) {
            String name = companyName.getText().toLowerCase();
            boolean added = getSystem().addCompany(name);
            if (added) {
                logger.info("Successfully added");
                SceneSwitcher.switchScene(this, event, "Main.fxml");
            } else {
                logger.warning("Company already exist");
            }
        }
    }

    public void returnButton(ActionEvent event) throws IOException {
        SceneSwitcher.switchScene(this, event, "Main.fxml");
    }

    public void setButtonClicked(ActionEvent event) {
        Stage window = new Stage();

        //Block events to other windows
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Set Time");
        window.setHeight(100.0);
        window.setWidth(300.0);

        TextField year = new TextField(), month = new TextField(), day = new TextField();
        Button closeButton = new Button("OK");
        closeButton.setOnAction(e -> {
            LocalDate date = LocalDate.of(
                    Integer.parseInt(year.getText()),
                    Integer.parseInt(month.getText()),
                    Integer.parseInt(day.getText()));
            LocalDateTime dateTime = LocalDateTime.of(date, getSystem().now().toLocalTime());
            getSystem().setSystemClockTime(dateTime);

            window.close();
        });

        HBox hBox = new HBox(10);
        hBox.getChildren().addAll(year, month, day);

        VBox layout = new VBox(10);
        layout.getChildren().addAll(hBox, closeButton);
        layout.setAlignment(Pos.CENTER);

        //Display window and wait for it to be closed before returning
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }
}