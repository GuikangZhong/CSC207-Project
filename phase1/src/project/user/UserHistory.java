package project.user;

import java.io.Serializable;
import java.time.Clock;
import java.time.LocalDateTime;

public class UserHistory implements Serializable {
    private LocalDateTime dateCreated;

    public LocalDateTime getDateCreated() {
        return this.dateCreated;
    }

    UserHistory(Clock clock){
        this.dateCreated = LocalDateTime.now(clock);
    }
}
