package project.application;

import project.system.SystemClock;

import java.io.Serializable;

public class Company implements Serializable {
    private String name;

    private JobPostingManager jobPostingManager;
    private SystemClock clock;
    public Company(String name, SystemClock clock){
        this.name = name;
        this.clock = clock;
    }


}
