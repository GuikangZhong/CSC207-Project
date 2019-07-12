package project.interview;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/** This holds all interviews
 *  It's quite empty at the moment
 */
public class InterviewManager implements Serializable {
    private static final long serialVersionUID = 8861388530518635925L;
    private Map<String, Interview> interviews;

    public InterviewManager() {
        interviews = new HashMap<>();
    }

    public void addInterview(String jobTitle, Interview interview) {
        interviews.put(jobTitle, interview);
    }
}
