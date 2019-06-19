package project;

public class CV {
    private String applicantName;
    private String content;

    public CV(String applicantName, String content){
        this.applicantName = applicantName;
        this.content = content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}
