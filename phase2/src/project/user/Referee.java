package project.user;

import project.application.JobPosting;
import project.application.ReferenceLetter;
import project.observer.SystemObserver;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;

public class Referee extends User implements Serializable, SystemObserver {
    private static final long serialVersionUID = -5182837267201535114L;
    private HashMap<Applicant, List<JobPosting>> requestList;

    public Referee(UserHistory history, String username, String password, String realName, List<String> company){
        super(history, username, password, realName, company);
        requestList = new HashMap<>();
    }

    public void addItems(Applicant applicant, JobPosting jobPosting) {
        List<JobPosting> postings = requestList.get(applicant);
        if (postings == null) {
            postings = new ArrayList<>();
            postings.add(jobPosting);
            requestList.put(applicant, postings);
        } else {
            postings.add(jobPosting);
            requestList.put(applicant, postings);
        }
    }

    @Override
    public Type getType() {
        return Type.REFEREE;
    }

    @Override
    public void updateOnTime(LocalDateTime now) {

    }
}
