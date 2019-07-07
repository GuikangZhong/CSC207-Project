package project.gui;

import javafx.event.Event;

import java.io.IOException;

public class ApplicantHistoryController extends ApplicationController {
    public void exit(Event event) throws IOException {
        SceneSwitcher.switchScene(this, event, "Main.fxml");
    }
}
