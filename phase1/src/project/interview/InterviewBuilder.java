package project.interview;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InterviewBuilder implements Serializable {
    private static final long serialVersionUID = -2884587935651178357L;
    private HashMap<String, Integer> map;
    private List<Interview> interviews;

    public InterviewBuilder() {
        map = new HashMap<>();
        interviews = new ArrayList<>();
    }

    public List<Interview> getInterviews() {
        return interviews;
    }

    public boolean addInterview(Interview interview) {
        String type = interview.getInterviewType();
        if (!map.containsKey(type)) {
            map.put(type, 1);
        }
        if (map.get(type).equals(interview.getMaxInterview())) {
            return false;
        }
        map.put(type, map.get(type) + 1);
        return true;
    }


}
