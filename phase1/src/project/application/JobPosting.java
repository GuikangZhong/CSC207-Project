package project.application;

import project.user.Applicant;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class JobPosting implements Serializable {
    private Job job;
    private Status status;
    private Requirement requirement;
    private int nApplicantNeeded;
    private Collection<Application> applications;

    public LocalDateTime getOpenDate() {
        return openDate;
    }

    public LocalDateTime getCloseDate() {
        return closeDate;
    }

    private LocalDateTime openDate, closeDate;

    public enum Status {
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

    public int getnApplicantNeeded() {
        return nApplicantNeeded;
    }

    public Collection<Application> getApplications() {
        return applications;
    }

    public void addApplication(Application application) {
        if (requirement.satisfies(application)) {
            applications.add(application);
        } else {
            throw new RuntimeException("Requirement not satisfied");
        }
    }

    public void removeApplication(Application application) {
        applications.remove(application);
    }

    private HireResult hireResult;

    JobPosting(Job job, LocalDateTime begin, LocalDateTime end, Requirement requirement) {
        status = Status.OPEN;
        this.requirement = requirement;
        this.openDate = begin;
        this.closeDate = end;
        this.job = job;
        hireResult = null;
    }


}
