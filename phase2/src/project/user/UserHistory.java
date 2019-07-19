package project.user;

import java.io.Serializable;
import java.time.LocalDateTime;

public class UserHistory implements Serializable {
    private static final long serialVersionUID = -7593163688503182842L;

    private LocalDateTime dateCreated;

    public UserHistory(LocalDateTime now) {
        this.dateCreated = now;
    }

    public LocalDateTime getDateCreated() {
        return this.dateCreated;
    }
}
