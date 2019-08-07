package project.interview;


import project.application.JobPosting;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Holds a setup of a interview.
 * That is it contains all possible rounds.
 */
public class InterviewSetup implements Serializable {
    private static final long serialVersionUID = -1939537566544034408L;
    private List<Round> rounds;
    private Map<String, Integer> record;
    private boolean isTemplate;

    public InterviewSetup() {
        this.rounds = new ArrayList<>();
        record = new HashMap<>();
        isTemplate = true;
    }

    /**
     * When a new InterviewSetup is created, by default it serves the purpose of being a certain "template" and should
     * not be used as a concrete setting for a certain interview.
     * @return true if and only if it serves as a template.
     */
    protected boolean isTemplate() {
        return isTemplate;
    }
    
    /**
     * Creates a concrete setup for the job based on the "template format".
     * @param jobPosting: the job that the program need to set the interview format for.
     * @return The concrete setup of interview for job.
     */
    public InterviewSetup createSetupWithJob(JobPosting jobPosting){
        InterviewSetup setup = new InterviewSetup();
        RoundFactory factory = new RoundFactory();
        for (Round round: getRounds()){
            Round tempRound = factory.createRound(round.getRoundType());
            tempRound.setJobPosting(jobPosting);
            setup.addRound(tempRound);
        }
        setup.isTemplate = false;
        return setup;
    }

    /**
     * @param round: The round to be added.
     * @return if given round can be added to the setup
     */
    public boolean addRound(Round round) {
        if (!record.containsKey(round.getRoundType())) {
            record.put(round.getRoundType(), 0);
        }
        int n = record.get(round.getRoundType());
        if (n == round.getMaxRoundNumber()) {
            return false;
        }
        record.put(round.getRoundType(), n + 1);
        round.setNumber(n + 1);
        rounds.add(round);
        return true;
    }


    public List<Round> getRounds() {
        return rounds;
    }

    public int getTotalRoundCount() {
        return rounds.size();
    }


    /**
     * Only used for the purpose of checking whether a format of interview process exists for the company,
     * Does NOT serve the purpose of equals().
     * @param other: the interview format to be compared.
     * @return true if and only if the type of each round is the same.
     */
    public boolean equalSetup(InterviewSetup other){
        if (rounds.size() != other.getRounds().size()){
            return false;
        }
        for (int i = 0; i < rounds.size(); i++){
            if (!(rounds.get(i).getRoundType().equals(other.getRounds().get(i).getRoundType()))){
                return false;
            }
        }
        return true;
    }
}
