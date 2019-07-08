package project.interview;

import project.observer.InterviewGroupObserver;
import project.observer.RoundObserver;
import project.user.Applicant;
import project.utils.Logging;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public abstract class Round implements Serializable, InterviewGroupObserver {
    private static final long serialVersionUID = 8042645594070245259L;
    private int number;
    private List<InterviewGroup> groups;
    private List<RoundObserver> observers;
    static private Logger logger = Logging.getLogger();

    public abstract String roundType();

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


    public int getNumber() {
        return number;
    }

    void setGroups(List<InterviewGroup> groups) {
        if (this.groups != null) {
            throw new RuntimeException("You have already assigned groups!!!");
        }
        for (InterviewGroup group : groups) {
            group.addObserver(this);
        }
        this.groups = groups;
    }

    public List<InterviewGroup> getGroups() {
        if (!assigned()) {
            throw new RuntimeException("You must assigned the group first!!");
        }
        return groups;
    }

    public boolean isAllGroupsSubmitted() {
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

    public List<Applicant> getApplicantsPassed() {
        List<Applicant> names = new ArrayList<>();
        for (InterviewGroup group : groups) {
            names.addAll(group.getApplicantsPassed());
        }
        return names;
    }

    public Round() {
        number = -1;
        observers = new ArrayList<>();
    }

    public String toString() {
        return roundType() + "#" + number;
    }

    boolean assigned() {
        return groups != null;
    }
}
