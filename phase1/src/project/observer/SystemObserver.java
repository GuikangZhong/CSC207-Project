package project.observer;

import java.time.LocalDateTime;

public interface SystemObserver {
    void updateOnTime(LocalDateTime now);
}
