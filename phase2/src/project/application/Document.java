package project.application;

import java.io.Serializable;
import java.time.LocalDateTime;

public abstract class Document implements Serializable, Cloneable {
    private static final long serialVersionUID = -5700647472647007254L;
    protected String name;
    protected String content;
    protected LocalDateTime createdDate;

    public Document(){}

    public Document(String name, String content, LocalDateTime date) {
        this.name = name;
        this.content = content;
        this.createdDate = date;
    }

    public String getContent() {
        return content;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public String toString() {
        return name;
    }

    public abstract String getDocumentType();

    public abstract int maxNumber();

    @Override
    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }
}
