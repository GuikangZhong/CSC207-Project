package project.user;

import java.io.Serializable;
import java.time.Clock;
import java.time.LocalDateTime;

public class UserHistory implements Serializable {
    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    private LocalDateTime dateCreated;
    UserHistory(Clock clock){
        dateCreated = LocalDateTime.now(clock);
    }
}
