package project.interview;

import project.observer.InterviewGroupObserver;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ApplicationStatus implements Serializable, InterviewGroupObserver {
    private static final long serialVersionUID = 8468246521663599935L;
    private List<String> finishedRound;

    public ApplicationStatus() {
        finishedRound = new ArrayList<>();
    }

    @Override
    public String toString() {
        if (finishedRound.size() == 0) {
            return "Submitted";
        } else {
            StringBuilder stringBuilder = new StringBuilder("Finished rounds:\n");
            for (String s : finishedRound) {
                stringBuilder.append(s + "\n");
            }
            return stringBuilder.toString();
        }
    }

    void currentRoundFinished(Round round) {
        finishedRound.add(round.toString());
    }

    @Override
    public void updateOnGroupSubmitted(InterviewGroup group) {
        currentRoundFinished(group.getRound());
    }
}
