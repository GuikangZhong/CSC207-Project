package project.application;

import project.observer.JobPostingObserver;
import project.observer.SystemObserver;
import project.user.Applicant;
import project.user.HR;
import project.utils.Logging;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

public class JobPosting implements Serializable, SystemObserver {
    private static final long serialVersionUID = 726794651891649767L;
    private Job job;
    private Status status;
    private String description;
    private Requirement requirement;
    private int nApplicantNeeded;
    private Collection<Application> applications;
    private LocalDateTime openDate, closeDate;
    private List<Applicant> hiredApplicants;
    private List<JobPostingObserver> observers;
    static private Logger logger = Logging.getLogger();
    private HR hr;

    public JobPosting(HR hr, Job job, LocalDateTime begin, LocalDateTime end, Requirement requirement, int nApplicantNeeded,
                      String description) {
        status = Status.OPEN;
        this.requirement = requirement;
        this.openDate = begin;
        this.closeDate = end;
        this.job = job;
        this.hiredApplicants = new ArrayList<>();
        this.nApplicantNeeded = nApplicantNeeded;
        this.applications = new ArrayList<>();
        this.observers = new ArrayList<>();
        this.description = description;
        this.hr = hr;
    }

    public enum Status {
        OPEN,
        CLOSED,
        UNFILLED,
        FILLED
    }

    public HR getHr() {
        return hr;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void addObserver(JobPostingObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(JobPostingObserver observer) {
        observers.remove(observer);
    }

    public LocalDateTime getOpenDate() {
        return openDate;
    }

    public LocalDateTime getCloseDate() {
        return closeDate;
    }

    /**
     * @param now for checking if the time passes the closed date
     *            if so, set the job status to closed
     */
    @Override
    public void updateOnTime(LocalDateTime now) {
        if (now.isAfter(closeDate) && getStatus() == Status.OPEN) {
            setStatus(Status.CLOSED);
            for (JobPostingObserver observer : observers) {
                observer.updateOnJobPostingClosure(this);
            }
        }
    }

    public Job getJob() {
        return job;
    }

    private void setStatus(Status status) {
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }

    public List<Applicant> getHiredApplicants(){return this.hiredApplicants;}

    public int getnApplicantNeeded() {
        return nApplicantNeeded;
    }

    public Collection<Application> getApplications() {
        return applications;
    }

    public String getJobTitle() {
        return getJob().getTitle();
    }

    /**
     * @param application from an applicant
     * @return true if the application is added in the job posting
     * false if 1. the posting is closed. 2. same applicant sends application twice
     * 3. the application is not met the requirement
     */
    public boolean addApplication(Application application) {
        if (status == Status.OPEN) {
            if (requirement.satisfies(application)) {
                if (applications.stream().anyMatch(app -> app.getApplicant() == application.getApplicant())) {
                    return false;
                }
                applications.add(application);
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public void removeApplication(Application application) {
        applications.remove(application);
    }

    void addHired(Applicant applicant) {
        if (status == Status.FILLED) {
            throw new RuntimeException("Cannot hire more applicants");
        }
        if (applicant == null) {
            logger.info(getJobTitle() + " is unfilled");
            setStatus(Status.UNFILLED);
        } else {
            hiredApplicants.add(applicant);
            if (hiredApplicants.size() == nApplicantNeeded) {
                setStatus(Status.FILLED);
                logger.info(getJobTitle() + " is filled");
            }
            else {
                setStatus(Status.UNFILLED);
            }
        }
    }

    public Company getCompany() {
        return job.getCompany();
    }

    public String toString() {
        return getJobTitle();
    }
}
