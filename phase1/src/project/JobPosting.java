package project;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

public class JobPosting implements Serializable {
    private Job job;
    Status status;
    private Requirement requirement;
    private int numberNeeded;
    private Collection<Application> applications ;
    private LocalDateTime begin, end;

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

    void addApplication(Application application){
        if(requirement.satisfies(application)){
            applications.add(application);
        }else{
            throw new RuntimeException("Requirement not satisfied");
        }
    }
    void removeApplication(Application application){
        applications.remove(application);
    }

    JobPosting(Job job, LocalDateTime begin, LocalDateTime end){
        status = Status.OPEN;
        this.begin = begin;
        this.end = end;
        this.job = job;
    }
}
