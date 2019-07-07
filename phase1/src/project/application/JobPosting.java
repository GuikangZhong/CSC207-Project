package project.application;

import project.observer.JobPostingObserver;
import project.observer.SystemObserver;
import project.user.Applicant;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class JobPosting implements Serializable, SystemObserver {
    private static final long serialVersionUID = 726794651891649767L;
    private Job job;
    private Status status;
    private String description;
    private Requirement requirement;
    private int nApplicantNeeded;
    private Collection<Application> applications;
    private LocalDateTime openDate, closeDate;
    private HireResult hireResult;
    private List<JobPostingObserver> observers;

    public JobPosting(Job job, LocalDateTime begin, LocalDateTime end, Requirement requirement, int nApplicantNeeded,
                      String description) {
        status = Status.OPEN;
        this.requirement = requirement;
        this.openDate = begin;
        this.closeDate = end;
        this.job = job;
        this.hireResult = new HireResult();
        this.nApplicantNeeded = nApplicantNeeded;
        this.applications = new ArrayList<>();
        this.observers = new ArrayList<>();
        this.description = description;
    }

    public enum Status {
        OPEN,
        CLOSED,
        FILLED
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void addObserver(JobPostingObserver observer){
        observers.add(observer);
    }

    public LocalDateTime getOpenDate() {
        return openDate;
    }

    public LocalDateTime getCloseDate() {
        return closeDate;
    }

    @Override
    public void updateOnTime(LocalDateTime now) {
        if (now.isAfter(closeDate) && getStatus() == Status.OPEN) {
            setStatus(Status.CLOSED);
            for(JobPostingObserver observer : observers){
                observer.updateOnJobPostingClosure(getJobTitle());
            }
        }
    }

    public Job getJob() {
        return job;
    }

    public void setStatus(Status status) {
        this.status = status;
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

    public HireResult getHireResult() {
        return hireResult;
    }

    public String getJobTitle() {
        return getJob().getTitle();
    }

    public void addApplication(Application application) {
        if (status == Status.OPEN) {
            if (requirement.satisfies(application)) {
                applications.add(application);
            } else {
                throw new RuntimeException("Requirement not satisfied");
            }
        } else {
            throw new RuntimeException("The job posting is closed");
        }
    }

    public boolean removeApplication(Application application) {
        return applications.remove(application);
    }

    public void addHired(Applicant applicant) {
        hireResult.addHiredApplicant(applicant);
        if (hireResult.getHired().size() >= nApplicantNeeded) {
            status = Status.FILLED;
        }
    }

    public Company getCompany() {
        return job.getCompany();
    }

    public String toString(){
        return getJobTitle();
    }
}
