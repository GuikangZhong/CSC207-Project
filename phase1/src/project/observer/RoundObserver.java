package project.observer;

import project.interview.Round;

public interface RoundObserver {
    void updateOnRoundFinished(Round round);
}
