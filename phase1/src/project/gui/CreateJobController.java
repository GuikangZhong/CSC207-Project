package project.gui;


import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import project.application.*;
import project.user.HR;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class CreateJobController extends ApplicationController {
    @FXML
    private TreeView<String> options = new TreeView<>();

    @FXML
    private TextField title = null;
    @FXML
    private TextField openDays = null;
    @FXML
    private TextField numOpenings = null;
    @FXML
    private TextArea desp = null;

    @FXML
    private Label companyName;



    @Override
    void postInit(){
        super.postInit();
        companyName.setText(getUser().getCompany());
    }

    public void submitJobPos(ActionEvent event) throws IOException {
        String title1 = title.getText();
        String days = openDays.getText();
        String numOpen = numOpenings.getText();
        String description = desp.getText();
        String comName = companyName.getText();
        Company company = getSystem().getCompany(comName);
        Job job = new Job(title1, company);
        Requirement requirement = new BasicRequirement();
        JobPosting jobPosting = new JobPosting(job,  getSystem().now(),
                getSystem().getClock().calculateFutureTime( getSystem().now(), Integer.parseInt(days))
                , requirement, Integer.parseInt(numOpen), description);
        JobPostingManager jobPostingManager = company.getJobPostingManager();
        if(jobPostingManager.addJobPosting(jobPosting)){
            System.out.println("job added successfully");
        }

    }

    public void exit(Event event) throws IOException{
        SceneSwitcher.switchScene(this, event, "Main.fxml");
    }
}
