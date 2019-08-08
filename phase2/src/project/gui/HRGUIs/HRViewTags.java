package project.gui.HRGUIs;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import project.application.Company;
import project.gui.GeneralUseGUIs.ApplicationController;
import project.gui.GeneralUseGUIs.SceneSwitcher;

import java.io.IOException;
import java.util.List;

public class HRViewTags extends ApplicationController implements Initializable {
    @FXML
    private TextField newTag;
    @FXML
    private ListView<String> tagList;
    @FXML
    private Label companyName;

    private String oldTag;

    @Override
    public void postInit(){
        super.postInit();
        companyName.setText(user.getSignedInCompany().getName());
        Company company = getSystem().getCompany(companyName.getText());
        List<String> tags = company.getTags();
        if (tags.size() != 0) {
            for (String tag : tags){
                tagList.getItems().add(tag);
            }
        }
    }

    public void addTag(ActionEvent event){
        Company company = getSystem().getCompany(companyName.getText());
        if (company.addTag(newTag.getText())){
            showModal("Great","Tag added successfully");
            tagList.getItems().add(newTag.getText());
        }else {
            showModal("Tag already exists.");
        }
    }

    public void tagClicked(MouseEvent event){
        oldTag = tagList.getSelectionModel().getSelectedItem();
    }

    public void removeTag(ActionEvent event){
        Company company = getSystem().getCompany(companyName.getText());
        company.removeTag(oldTag);
        tagList.getItems().remove(oldTag);
    }

    public void exit(Event event) throws IOException {
        SceneSwitcher.switchScene(this, event, "Main.fxml");
    }
}
