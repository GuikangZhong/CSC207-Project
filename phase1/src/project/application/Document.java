package project.application;

import java.time.LocalDateTime;

public abstract class Document {
    protected String applicantName;
    protected String content;
    protected LocalDateTime createdDate;

    Document(String applicantName, String content, LocalDateTime date){
        this.applicantName = applicantName;
        this.content = content;
        this.createdDate = date;
    }

}
