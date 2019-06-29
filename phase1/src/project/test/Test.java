package project.test;

import project.user.*;
import project.application.*;
import project.interview.*;
import project.observer.*;
import project.system.*;

public class Test {
    public static void main(String[] args) {
        MainSystem system = new MainSystem();
        Company company = new Company("UofT", system);
        JobPosting jobPosting = new JobPosting(new Job("a", company), null, null,
                new BasicRequirement(), 1);
        company.getJobPostingManager().addJobPosting(jobPosting);

        System.out.println("End");


    }
}
