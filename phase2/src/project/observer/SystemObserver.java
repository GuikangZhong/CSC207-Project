package project.observer;

import java.time.LocalDateTime;

public interface SystemObserver {
    /**
     * MainSystem notify all observer by this method when there is
     * either a time change or user login
     * @param now
     */
    void updateOnTime(LocalDateTime now);
}
