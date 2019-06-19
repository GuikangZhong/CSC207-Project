package project;

import java.io.Serializable;
import java.util.List;

public class JobPosting implements Serializable {
    private Job job;
    public enum Status{
        OPEN,
        CLOSED,
        FILLED
    }
    Status status;
    private Requirement requirement;
    private int numberNeeded;
    private List<Application> applications ;
}
