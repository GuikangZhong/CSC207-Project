package project.application;

import java.io.Serializable;
import java.time.LocalDateTime;

public abstract class Document implements Serializable {
    private static final long serialVersionUID = -5700647472647007254L;
    protected String applicantName;
    protected String content;
    protected LocalDateTime createdDate;

    Document(String applicantName, String content, LocalDateTime date) {
        this.applicantName = applicantName;
        this.content = content;
        this.createdDate = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getApplicantName() {
        return applicantName;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }
}
