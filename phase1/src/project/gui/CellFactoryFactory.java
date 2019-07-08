package project.gui;

import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;
import project.interview.InterviewGroup;
import project.user.Applicant;
import project.user.Interviewer;

public class CellFactoryFactory {
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
                            setText(t.getInterview().getJobPosting().getJobTitle());
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