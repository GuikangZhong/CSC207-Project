package project.test;

import project.application.BasicRequirement;
import project.application.Company;
import project.application.Job;
import project.application.JobPosting;
import project.system.MainSystem;

public class Test {
    public static void main(String[] args) {
        MainSystem system = new MainSystem();
        Company company = new Company("UofT", system);
        JobPosting jobPosting = new JobPosting(new Job("a", company), null, null,
                new BasicRequirement(), 1);
        company.getJobPostingManager().addJobPosting(jobPosting);

    }
}
