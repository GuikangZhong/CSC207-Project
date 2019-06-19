package project;

public class CoverLetter {
    public String getApplicantName() {
        return applicantName;
    }

    private String applicantName;
    private String content;

    public CoverLetter(String applicantName, String content){
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
