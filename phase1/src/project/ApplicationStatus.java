package project;

import project.interview.InterviewStatus;

import java.util.HashMap;

public class ApplicationStatus {
    public HashMap<String, InterviewStatus> getStatus() {
        return status;
    }

    private HashMap<String, InterviewStatus> status;
}
