package project;
import java.io.*;

public class CoverLetter {
    public String getApplicantName() {
        return applicantName;
    }

    private String applicantName;
    private String content;

    public static CoverLetter createCoverLetterByDirectInput(String applicantName, String input){
        return new CoverLetter(applicantName, input);
    }

    public static CoverLetter createCoverLetterByFileName(String applicantName, String path) throws IOException{
        BufferedReader in = null;
        String content = "";
        try {
            in = new BufferedReader(new FileReader(path));
            String s = in.readLine();
            while(s  != null){
                content  = content + s;
                s = in.readLine();
            }
        } finally {
            if (in != null) {
                in.close();
            }
        }
        return new CoverLetter(applicantName, content);
    }

    private CoverLetter(String applicantName, String content){
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
