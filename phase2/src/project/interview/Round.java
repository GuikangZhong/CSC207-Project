package project.interview;

import project.application.Application;
import project.application.JobPosting;
import project.observer.InterviewGroupObserver;
import project.observer.RoundObserver;
import project.user.Applicant;
import project.utils.Logging;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public abstract class Round implements Serializable, InterviewGroupObserver, Cloneable {
    private static final long serialVersionUID = 8042645594070245259L;
    private int number;
    private List<InterviewGroup> groups;
    private List<RoundObserver> observers;
    static private Logger logger = Logging.getLogger();

    public void setJobPosting(JobPosting jobPosting) {
        this.jobPosting = jobPosting;
    }

    private JobPosting jobPosting;

    public Round() {
        number = -1;
        observers = new ArrayList<>();
    }

//    public Round(Job job){
//        number = -1;
//        observers = new ArrayList<>();
//        setJob(job);
//    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        if(groups != null || observers.size() > 0){
            throw new RuntimeException("You cannot clone this round by now!!!");
        }
        return super.clone();
    }

    public abstract String getRoundType();

    public abstract int getMaxRoundNumber();

    public void addObserver(RoundObserver observer) {
        logger.info(this + " addObserver " + observer);
        observers.add(observer);
    }

    public void setNumber(int number) {
        if (number > getMaxRoundNumber()) {
            throw new RuntimeException("No you cannot add more rounds !!!");
        }
        this.number = number;
    }


    /**
     * Set the round with given group
     * Can be called only once
     * Further calling attempts results in RuntimeException
     * @param groups
     */
    void setGroups(List<InterviewGroup> groups) {
        if(groups == null){
            throw new RuntimeException("You assigned a null");
        }
        if (this.groups != null) {
            throw new RuntimeException("You have already assigned groups!!!");
        }
        for (InterviewGroup group : groups) {
            group.addObserver(this);
            group.setRound(this);
            for(Applicant applicant:group.getApplicants()){
                Application application = applicant.getApplicationOf(jobPosting.getJobTitle()).get();
                group.addObserver(application.getStatus());
            }
        }
        this.groups = groups;
    }

    boolean isAllGroupsSubmitted() {
        for (InterviewGroup group : groups) {
            if (!group.isSubmitted())
                return false;
        }
        return true;
    }

    @Override
    public void updateOnGroupSubmitted(InterviewGroup group) {
        logger.info(this + " one group submitted");
        if (isAllGroupsSubmitted()) {
            logger.info("All submitted");
            for(Applicant applicant : getApplicantsPassed()){
                logger.info(applicant.getUsername() + " : " + applicant.getRealName() + " passed");
            }
            for (RoundObserver observer : observers) {
                observer.updateOnRoundFinished(this);
            }
        }
    }

    List<Applicant> getApplicantsPassed() {
        List<Applicant> names = new ArrayList<>();
        for (InterviewGroup group : groups) {
            names.addAll(group.getApplicantsPassed());
        }
        return names;
    }

    public String toString() {
        return getRoundType() + "#" + number;
    }

    boolean assigned() {
        return groups != null;
    }

    void withdraw(Applicant applicant){
        if (groups != null) {
            for(InterviewGroup group: groups){
                group.withdraw(applicant);
            }
        }
    }


}
