package project.interview;

import project.application.Job;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Holds a setup of a interview.
 * That is it contains all possible rounds.
 */
public class InterviewSetup implements Serializable, Cloneable {
    private static final long serialVersionUID = -1939537566544034408L;
    private List<Round> rounds;
    private Map<String, Integer> record;

    protected boolean isTemplate() {
        return isTemplate;
    }

    private boolean isTemplate;

    public InterviewSetup() {
        this.rounds = new ArrayList<>();
        record = new HashMap<>();
        isTemplate = true;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        InterviewSetup setup  = (InterviewSetup)super.clone();
        setup.rounds = new ArrayList<>();
        for(Round round: rounds){
            setup.rounds.add((Round)round.clone());
        }
        setup.isTemplate = false;
        return setup;
    }

    public InterviewSetup createSetupWithJob(Job job) throws CloneNotSupportedException{
        InterviewSetup setup = (InterviewSetup)this.clone();
        for (Round round: setup.getRounds()){
//            Round temp = (Round) round.clone();
            round.setJob(job);
        }
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

    public boolean equalSetup(InterviewSetup other){
        if (rounds.size() != other.getRounds().size()){
            return false;
        }
        for (int i = 0; i < rounds.size(); i++){
            if (!(rounds.get(0).getRoundType().equals(other.getRounds().get(0).getRoundType()))){
                return false;
            }
        }
        return true;
    }
}
