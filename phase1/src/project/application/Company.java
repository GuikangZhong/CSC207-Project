package project.application;

import project.observer.SystemObserver;
import project.system.MainSystem;
import project.user.*;
import project.utils.Logging;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.logging.Logger;

public class Company implements Serializable, SystemObserver {
    private static final long serialVersionUID = 2088083308860080279L;

    private String name;
    private JobPostingManager jobPostingManager;
    private HRManager hrManager;
    private InterviewerManager interviewerManager;
    static private Logger logger = Logging.getLogger();
    private MainSystem system;


    public Company(String name, MainSystem system) {
        this.name = name;
        this.system = system;
        jobPostingManager = new JobPostingManager(system, this);
        hrManager = new HRManager(system, this);
        interviewerManager = new InterviewerManager(system, this);
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

    @Override
    public void updateOnTime(LocalDateTime now) {
        logger.info("Update on " + now);
        jobPostingManager.updateOnTime(now);
    }
}
