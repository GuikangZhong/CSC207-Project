package project;

import java.io.Serializable;
import java.util.List;

public class Company implements Serializable {
    private String name;
    private List<JobPosting> jobPostingList;
}
