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

public class CreateJobController implements Initializable {
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
    public void initialize(URL location, ResourceBundle resources) {
        companyName.setText(Main.user.getCompany());
        options.setRoot(HRMenuController.getMenu().getOptions());
    }

    public void selectItems(MouseEvent event) throws IOException {
        TreeItem<String> item = options.getSelectionModel().getSelectedItem();
        HRMenu.selectItem(this.getClass(), event, item);
    }

    public void submitJobPos(ActionEvent event) throws IOException {
        String title1 = title.getText();
        String days = openDays.getText();
        String numOpen = numOpenings.getText();
        String description = desp.getText();
        String comName = companyName.getText();
        Company company = Main.system.getCompany(comName);
        Job job = new Job(title1, company);
        Requirement requirement = new BasicRequirement();
        JobPosting jobPosting = new JobPosting(job, Main.system.now(),
                Main.system.getClock().calculateFutureTime(Main.system.now(), Integer.parseInt(days))
                , requirement, Integer.parseInt(numOpen), description);
        JobPostingManager jobPostingManager = company.getJobPostingManager();
        if(jobPostingManager.addJobPosting(jobPosting)){
            System.out.println("job added successfully");
        }

    }

    public void exit(Event event) throws IOException{
        SceneSwitcher.switchScene(this.getClass(), event, "Main.fxml");
    }
}
