package project.interview;

import project.application.Company;
import project.application.JobPosting;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class Interview {
    private InterviewSetup setup;
    private int round = 0;

    public JobPosting getJobPosting() {
        return jobPosting;
    }

    private JobPosting jobPosting;

    public Interview() {
    }

    public String getHR() {
        return HR;
    }

    private String HR;

    InterviewStatus getStatus() {
        // TODO:
        return null;
    }

    List<Round> getRoundsFinished() {
        return Collections.unmodifiableList(setup.getRounds().subList(0, round));
    }

    List<Round> getRoundsInFuture() {
        return Collections.unmodifiableList(setup.getRounds().subList(round + 1, setup.getRounds().size()));
    }

    Round getRoundInProgress() {
        return setup.getRounds().get(round);

    }

    void updateOnRoundFinished() {
        jobPosting.getCompany().getHrManager().updateOnInterviewRoundFinished(this);
    }
}
