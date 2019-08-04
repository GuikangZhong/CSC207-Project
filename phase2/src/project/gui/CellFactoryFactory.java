package project.gui;

import javafx.beans.binding.Bindings;
import javafx.scene.control.*;
import javafx.util.Callback;
import project.application.JobPosting;
import project.interview.InterviewGroup;
import project.user.Applicant;
import project.user.Interviewer;

class CellFactoryFactory {
    static Callback<ListView<Applicant>, ListCell<Applicant>>  getCellFactoryForApplicant(){
        return new Callback<ListView<Applicant>, ListCell<Applicant>>() {

            @Override
            public ListCell<Applicant> call(ListView<Applicant> p) {
                ListCell<Applicant> cell = new ListCell<Applicant>() {

                    @Override
                    protected void updateItem(Applicant t, boolean bln) {
                        super.updateItem(t, bln);
                        if (t != null) {
                            setText(t.getRealName());
                        }else{
                            setText("");
                        }
                    }
                };
                ContextMenu contextMenu = new ContextMenu();
                MenuItem showInfo = new MenuItem();
                showInfo.textProperty().bind(Bindings.format("Show info \"%s\"", cell.itemProperty()));
                contextMenu.getItems().addAll(showInfo);
                cell.setContextMenu(contextMenu);
                return cell;
            }
        };
    }

    static Callback<ListView<Interviewer>, ListCell<Interviewer>>  getCellFactoryForInterviewer(){
        return new Callback<ListView<Interviewer>, ListCell<Interviewer>>() {

            @Override
            public ListCell<Interviewer> call(ListView<Interviewer> p) {

                ListCell<Interviewer> cell = new ListCell<Interviewer>() {

                    @Override
                    protected void updateItem(Interviewer t, boolean bln) {
                        super.updateItem(t, bln);
                        if (t != null) {
                            setText(t.getRealName());
                        } else {
                            setText("");
                        }
                    }
                };
                return cell;
            }
        };
    }

    static Callback<ListView<InterviewGroup>, ListCell<InterviewGroup>>  getCellFactoryForInterviewGroup(){
        return new Callback<ListView<InterviewGroup>, ListCell<InterviewGroup>>() {

            @Override
            public ListCell<InterviewGroup> call(ListView<InterviewGroup> p) {

                ListCell<InterviewGroup> cell = new ListCell<InterviewGroup>() {

                    @Override
                    protected void updateItem(InterviewGroup t, boolean bln) {
                        super.updateItem(t, bln);
                        if (t != null) {
                            setText(t.getJobPosting().getJobTitle());
                        }else{
                            setText("");
                        }
                    }

                };

                return cell;
            }
        };
    }

    static Callback<ListView<JobPosting>, ListCell<JobPosting>>  getCellFactoryForJobPosting(){
        return new Callback<ListView<JobPosting>, ListCell<JobPosting>>() {

            @Override
            public ListCell<JobPosting> call(ListView<JobPosting> p) {

                ListCell<JobPosting> cell = new ListCell<JobPosting>() {

                    @Override
                    protected void updateItem(JobPosting t, boolean bln) {
                        super.updateItem(t, bln);
                        if (t != null) {
                            setText(t.getJobTitle());
                        }else{
                            setText("");
                        }
                    }

                };
                return cell;
            }
        };
    }

    static Callback<ListView<String>, ListCell<String>>  getCellFactoryForTag(){
        return new Callback<ListView<String>, ListCell<String>>() {

            @Override
            public ListCell<String> call(ListView<String> p) {

                ListCell<String> cell = new ListCell<String>() {

                    @Override
                    protected void updateItem(String  t, boolean bln) {
                        super.updateItem(t, bln);
                        if (t != null) {
                            setText(t);
                        }else{
                            setText("");
                        }
                    }

                };

                return cell;
            }
        };
    }
}
