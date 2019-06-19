package project;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

public class JobPosting implements Serializable {
    private Job job;
    public enum Status{
        OPEN,
        CLOSED,
        FILLED
    }

    public Job getJob() {
        return job;
    }

    public Status getStatus() {
        return status;
    }

    public Requirement getRequirement() {
        return requirement;
    }

    public int getNumberNeeded() {
        return numberNeeded;
    }

    public Collection<Application> getApplications() {
        return applications;
    }

    Status status;
    private Requirement requirement;
    private int numberNeeded;
    private Collection<Application> applications ;

    void addApplication(Application application){
        applications.add(application);
    }
    void removeApplication(Application application){
        applications.remove(application);
    }
}
