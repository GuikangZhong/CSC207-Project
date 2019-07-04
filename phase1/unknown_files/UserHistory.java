package project;

import java.io.Serializable;
import java.time.Clock;
import java.time.LocalDateTime;

public class UserHistory implements Serializable {
    private LocalDateTime dateCreated;
    
    public LocalDateTime getDateCreated() {
    	return dateCreated;
    }
    
    UserHistory(Clock clock){
        dateCreated = LocalDateTime.now(clock);
    }
}
