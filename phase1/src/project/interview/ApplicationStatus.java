package project.interview;

import java.util.List;

public class ApplicationStatus {
    private List<String> finishedRound;

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("Finished rounds:\n");
        for (String s : finishedRound) {
            stringBuilder.append(s + "\n");
        }
        return stringBuilder.toString();
    }

    public void addFinishedFound(Round round) {
        finishedRound.add(round.toString());
    }
}
