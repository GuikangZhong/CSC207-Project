package project.interview;

import java.util.Optional;

public class IndividualInterviewProgress {
    private InterviewProgress overallProgress;

    public InterviewProgress getOverallProgress() {
        return overallProgress;
    }

    public boolean isCurrentRoundFinished() {
        return currentRoundFinished;
    }

    private boolean currentRoundFinished;

    public IndividualInterviewProgress(InterviewProgress progress) {
        currentRoundFinished = false;
        overallProgress = progress;
    }
}
