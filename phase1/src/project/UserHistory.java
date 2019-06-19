package project;

import java.time.Clock;
import java.time.LocalDateTime;

public class UserHistory {
    private LocalDateTime dateCreated;
    UserHistory(Clock clock){
        dateCreated = LocalDateTime.now(clock);
    }
}
