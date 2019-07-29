package project.application;

import project.interview.InterviewSetup;
import project.observer.SystemObserver;
import project.system.MainSystem;
import project.user.*;
import project.utils.Logging;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;
import java.util.logging.Logger;

public class Company implements Serializable, SystemObserver {
    private static final long serialVersionUID = 2088083308860080279L;

    private String name;
    private JobPostingManager jobPostingManager;
    private InterviewerManager interviewerManager;
    private HRManager hrManager;
    static private Logger logger = Logging.getLogger();
    private MainSystem system;
    private HashMap<String, InterviewSetup> interviewFormats;
    private List<Company> subsidiaries;
    private Company parentCompany;


    public Company(String name, MainSystem system) {
        this.name = name;
        this.system = system;
        jobPostingManager = new JobPostingManager(system, this);
        hrManager = new HRManager(system, this);
        interviewerManager = new InterviewerManager(system, this);
        interviewFormats = new HashMap<>();
        subsidiaries = new ArrayList<>();
        parentCompany = null;
    }

    public InterviewSetup getInterviewFormat(String formatName) {
        return interviewFormats.get(formatName);
    }

    public Set<String> getFormatNames(){
        return Collections.unmodifiableSet(interviewFormats.keySet());
    }

    public void addInterviewFormat(String formatName, InterviewSetup setup) {

//        interviewFormats.put(formatName, setup)
        if (interviewFormats.keySet().contains(formatName)){
            throw new RuntimeException();
        }
        for (InterviewSetup format: interviewFormats.values()){
            if (format.equalSetup(setup)){
                throw new RuntimeException();
            }
        }
        interviewFormats.put(formatName, setup);
    }


    public MainSystem getSystem() {
        return system;
    }

    public String getName() {
        return name;
    }

    public JobPostingManager getJobPostingManager() {
        return jobPostingManager;
    }

    public HRManager getHrManager() {
        return hrManager;
    }

    public InterviewerManager getInterviewerManager() {
        return interviewerManager;
    }

    //    public boolean addUser(User user) {
//        if (user.getType() == User.Type.INTERVIEWER) {
//            return interviewerManager.addUser((Interviewer) user);
//        }
//        return false;
//    }
//
//    public User getUser(String username) {
//         if (interviewerManager.containsUser(username)) {
//            return interviewerManager.getUser(username);
//        }
//        return null;
//    }
    public boolean addUser(User user) {
        if (user.getType() == User.Type.HR) {
            return hrManager.addUser((HR) user);
        } else if (user.getType() == User.Type.INTERVIEWER) {
            return interviewerManager.addUser((Interviewer) user);
        }
        return false;
    }

    public User getUser(String username) {
        if (hrManager.containsUser(username)) {
            return hrManager.getUser(username);
        } else if (interviewerManager.containsUser(username)) {
            return interviewerManager.getUser(username);
        }
        return null;
    }

    public void addSubsidiary(Company subsidiary){
        subsidiaries.add(subsidiary);
    }

    public void setParentCompany(Company parent){
        if (parentCompany == null)
            parentCompany = parent;
        else
            throw new RuntimeException("You cannot set parent for the second time");
    }

    public Company getParentCompany() {
        return parentCompany;
    }

    public List<Company> getSubsidiaries(){
        return subsidiaries;
    }

    @Override
    public void updateOnTime(LocalDateTime now) {
        logger.info("Update on " + now);
        jobPostingManager.updateOnTime(now);
    }
}
