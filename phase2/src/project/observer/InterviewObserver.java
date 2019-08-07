package project.observer;

import project.interview.Interview;

public interface InterviewObserver {
    /**
     * is called when a round is finished
     * @param interview
     */
    void updateOnInterviewRoundFinished(Interview interview);

//    void updateOnHireResult(Interview interview);

    /**
     * is called when a interview has exhausted all of its rounds
     * @param interview
     */
    void updateOnNoMoreRounds(Interview interview);
}
