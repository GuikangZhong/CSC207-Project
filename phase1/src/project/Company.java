package project;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;

public class Company implements Serializable {
    private String name;

    private JobPostingManager jobPostingManager;
    private SystemClock clock;
    public Company(String name, SystemClock clock){
        this.name = name;
        this.clock = clock;
    }


}
