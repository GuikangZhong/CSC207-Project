package project.interview;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InterviewBuilder {
    private HashMap<String, Integer> map;
    private List<Interview> interviews;

    public InterviewBuilder(){
        map = new HashMap<>();
        interviews = new ArrayList<>();
    }

    public List<Interview> getInterviews() {
        return interviews;
    }

    public boolean addInterview(Interview interview){
        String type = interview.getInterviewType();
        if(!map.containsKey(type)){
            map.put(type, 0);
        }
        if(map.get(type).equals(interview.getMaxInterview())){
            return false;
        }
        map.put(type, map.get(type) + 1);
        return true;
    }


}
