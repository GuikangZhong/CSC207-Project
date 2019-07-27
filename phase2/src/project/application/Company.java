package project.application;

import project.interview.InterviewSetup;
import project.observer.SystemObserver;
import project.system.MainSystem;
import project.user.*;
import project.utils.Logging;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.logging.Logger;

public class Company implements Serializable, SystemObserver {
    private static final long serialVersionUID = 2088083308860080279L;

    private String name;
    private JobPostingManager jobPostingManager;
    private InterviewerManager interviewerManager;
    static private Logger logger = Logging.getLogger();
    private MainSystem system;
    private HashMap<String, InterviewSetup> interviewFormats;


    public Company(String name, MainSystem system) {
        this.name = name;
        this.system = system;
        jobPostingManager = new JobPostingManager(system, this);
        interviewerManager = new InterviewerManager(system, this);
        interviewFormats = new HashMap<>();
    }

    public InterviewSetup getInterviewFormat(String formatName){
        return interviewFormats.get(formatName);
    }
    public void addInterviewFormat(String formatName, InterviewSetup setup){
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
        return system.getHrManager();
    }

    public InterviewerManager getInterviewerManager() {
        return interviewerManager;
    }

    public boolean addUser(User user) {
        if (user.getType() == User.Type.INTERVIEWER) {
            return interviewerManager.addUser((Interviewer) user);
        }
        return false;
    }

    public User getUser(String username) {
         if (interviewerManager.containsUser(username)) {
            return interviewerManager.getUser(username);
        }
        return null;
    }

    @Override
    public void updateOnTime(LocalDateTime now) {
        logger.info("Update on " + now);
        jobPostingManager.updateOnTime(now);
    }
}