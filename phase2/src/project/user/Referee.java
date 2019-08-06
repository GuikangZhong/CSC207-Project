package project.user;

import project.application.Company;
import project.application.JobPosting;
import project.observer.SystemObserver;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;

public class Referee extends User implements Serializable, SystemObserver {
    private static final long serialVersionUID = -5182837267201535114L;
    private HashMap<Applicant, List<JobPosting>> requests;

    public Referee(UserHistory history, String username, String password, String realName, List<Company> company) {
        super(history, username, password, realName, company);
        requests = new HashMap<>();
    }

    public void addRequest(Applicant applicant, JobPosting jobPosting) {
        if (!requests.containsKey(applicant)) {
            List<JobPosting> postings = new ArrayList<>();
            postings.add(jobPosting);
            requests.put(applicant, postings);
        } else {
            List<JobPosting> postings = requests.get(applicant);
            postings.add(jobPosting);
        }
    }

    public void removeRequest(Applicant applicant, JobPosting jobPosting) {
        List<JobPosting> jobPostingList = requests.get(applicant);
        jobPostingList.remove(jobPosting);
    }

    public HashMap<Applicant, List<JobPosting>> getRequests() {
        return requests;
    }

    @Override
    public Type getType() {
        return Type.REFEREE;
    }

    @Override
    public void updateOnTime(LocalDateTime now) {

    }
}
