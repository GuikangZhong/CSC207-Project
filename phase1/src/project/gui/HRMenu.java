package project.gui;

import javafx.event.Event;
import javafx.scene.control.TreeItem;

import java.io.IOException;

public class HRMenu {
    public TreeItem<String> getOption() {
        return option;
    }

    private TreeItem<String> option;

    public HRMenu(){
        option = new TreeItem<>("Option");
        TreeItem<String> document = new TreeItem<>("View all applicants");
        TreeItem<String> jobPosting = new TreeItem<>("View applicants for a job posting");
        TreeItem<String> application = new TreeItem<>("Inspect interviewers"); // Maybe change the name in the future.....
        TreeItem<String> history = new TreeItem<>("Interview status");  // Handout P3 Paragraph 2
        option.getChildren().addAll(document, jobPosting, application, history);
        option.setExpanded(true);
    }

    public void selectItem(Class c, Event event, TreeItem<String> item)throws IOException {
        System.out.println("a");
        if (item != null){
            if (item.getValue().equals("View all applicants")){
                SceneSwitcher.switchScene(c, event, "AllApplicants.fxml");
            }
            if (item.getValue().equals("View applicants for a job posting")){
                SceneSwitcher.switchScene(c, event, "PostingApplicants.fxml");
            }
            if (item.getValue().equals("Inspect interviewers")){
                SceneSwitcher.switchScene(c, event, "Miscellaneous.fxml");
            }
        }
    }
}
