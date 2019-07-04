package project.interview;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class InterviewSetup implements Serializable {


    private static final long serialVersionUID = -1939537566544034408L;
    private List<Round> rounds;
    private Map<String, Integer> record;

    public InterviewSetup(List<Round> rounds) {
        this.rounds = rounds;
    }

    public boolean addRound(Round round) {
        if (!record.containsKey(round.roundType())) {
            record.put(round.roundType(), 0);
        }
        int n = record.get(round.roundType());
        if (n == round.getMaxRoundNumber()) {
            return false;
        }
        record.put(round.roundType(), n + 1);
        return true;
    }

    public List<Round> getRounds() {
        return rounds;
    }

    public int getTotalRoundCount() {
        return rounds.size();
    }
}
