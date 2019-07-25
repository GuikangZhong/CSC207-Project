package project.interview;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Holds a setup of a interview.
 * That is it contains all possible rounds.
 */
public class InterviewSetup implements Serializable, Cloneable {
    private static final long serialVersionUID = -1939537566544034408L;
    private List<Round> rounds;
    private Map<String, Integer> record;

    public InterviewSetup() {
        this.rounds = new ArrayList<>();
        record = new HashMap<>();
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        InterviewSetup clone = new InterviewSetup();
        clone.rounds = new ArrayList<>(rounds);
        clone.record = new HashMap<>(record);
        return clone;
    }

    /**
     * @param round: The round to be added.
     * @return if given round can be added to the setup
     */
    public boolean addRound(Round round) {
        if (!record.containsKey(round.roundType())) {
            record.put(round.roundType(), 0);
        }
        int n = record.get(round.roundType());
        if (n == round.getMaxRoundNumber()) {
            return false;
        }
        record.put(round.roundType(), n + 1);
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
}
