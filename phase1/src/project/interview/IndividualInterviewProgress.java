package project.interview;

import java.util.Optional;

public class IndividualInterviewProgress {
    private InterviewProgress overallProgress;
    private boolean currentRoundFinished;

    public IndividualInterviewProgress(InterviewProgress progress) {
        currentRoundFinished = false;
        overallProgress = progress;
    }

    public boolean isCurrentRoundFinished() {
        return currentRoundFinished;
    }

    public void setCurrentRoundFinished() {
        this.currentRoundFinished = true;
    }

    public InterviewProgress getOverallProgress() {
        return overallProgress;
    }

}
