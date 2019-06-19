package project;

import java.io.Serializable;

public class Job implements Serializable {
    private String title;

    public String getTitle() {
        return title;
    }

    public Company getCompany() {
        return company;
    }

    private Company company;
}
