package project;

import java.io.Serializable;
import java.util.Collection;

public class Company implements Serializable {
    private String name;
    private Collection<JobPosting> jobPostings;
}
