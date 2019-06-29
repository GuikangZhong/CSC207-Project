package project.observer;

import java.time.LocalDateTime;

public interface SystemTimeUpdateObserver {
    void updateOnTime(LocalDateTime now);
}
