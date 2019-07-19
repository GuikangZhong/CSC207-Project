package project.observer;

import project.interview.Round;

public interface RoundObserver {
    /**
     * is called when a round is finished (all its groups are submitted)
     * @param round
     */
    void updateOnRoundFinished(Round round);
}
