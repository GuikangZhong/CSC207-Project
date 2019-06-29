package project.application;

import java.io.Serializable;

public class Job implements Serializable {
    private String title;
    private Company company;

    public Job(String title, Company company) {
        this.title = title;
        this.company = company;
    }

    public String getTitle() {
        return title;
    }

    public Company getCompany() {
        return company;
    }
}
