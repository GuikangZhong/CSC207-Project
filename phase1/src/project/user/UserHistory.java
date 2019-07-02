package project.user;

import project.system.SystemClock;
import java.io.Serializable;
import java.time.LocalDateTime;

public class UserHistory implements Serializable {
    private static final long serialVersionUID = -7593163688503182842L;
    private LocalDateTime dateCreated;

    public LocalDateTime getDateCreated() {
        return this.dateCreated;
    }

    UserHistory(SystemClock clock) {
        this.dateCreated = LocalDateTime.now(clock.getClock());
    }

}
