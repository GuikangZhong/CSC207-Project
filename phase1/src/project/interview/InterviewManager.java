package project.interview;

import java.util.HashMap;
import java.util.Map;

public class InterviewManager {
    private Map<String, Interview> interviews;

    public InterviewManager() {
        interviews = new HashMap<>();
    }

    public Interview getInterview(String jobTitle) {
        return interviews.get(jobTitle);
    }

    public void addInterview(String jobTitle, Interview interview) {
        interviews.put(jobTitle, interview);
    }
}
