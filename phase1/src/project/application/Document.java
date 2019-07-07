package project.application;

import java.io.Serializable;
import java.time.LocalDateTime;

public abstract class Document implements Serializable {
    private static final long serialVersionUID = -5700647472647007254L;
    protected String name;
    protected String content;
    protected LocalDateTime createdDate;

    Document(String name, String content, LocalDateTime date) {
        this.name = name;
        this.content = content;
        this.createdDate = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public String toString(){
        return name;
    }

    public abstract String type();

    public abstract int maxNumber();
}
