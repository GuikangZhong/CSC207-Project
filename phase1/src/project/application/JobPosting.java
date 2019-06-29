package project.application;

import project.user.Applicant;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

public class JobPosting implements Serializable{
    private Job job;
    private Status status;
    private Requirement requirement;
    private int nApplicantNeeded;
    private Collection<Application> applications;
    private LocalDateTime openDate, closeDate;
    private HireResult hireResult;

    JobPosting(Job job, LocalDateTime begin, LocalDateTime end, Requirement requirement, int nApplicantNeeded) {
        status = Status.OPEN;
        this.requirement = requirement;
        this.openDate = begin;
        this.closeDate = end;
        this.job = job;
        this.hireResult = new HireResult();
        this.nApplicantNeeded = nApplicantNeeded;
        this.applications = new ArrayList<>();
    }

    public LocalDateTime getOpenDate() {
        return openDate;
    }

    public LocalDateTime getCloseDate() {
        return closeDate;
    }


    public enum Status {
        OPEN,
        CLOSED,
        FILLED
    }

    public Job getJob() {
        return job;
    }

    public void setStatus(Status status){
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

    public void addApplication(Application application) {
        if (status == Status.OPEN){
            if (requirement.satisfies(application)) {
                applications.add(application);
            } else {
                throw new RuntimeException("Requirement not satisfied");
            }
        } else{
            throw new RuntimeException("The job posting is closed");
        }
    }

    public boolean removeApplication(Application application) {
        return applications.remove(application);
    }

    public void addHired(Applicant applicant) {
        hireResult.addHiredApplicant(applicant);
        if(hireResult.getHired().size() >= nApplicantNeeded){
            status = Status.FILLED;
        }
    }
    public Company getCompany(){
        return job.getCompany();
    }
}
