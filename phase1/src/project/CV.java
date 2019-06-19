package project;

import java.io.*;

public class CV {
    public String getApplicantName() {
        return applicantName;
    }

    private String applicantName;
    private String content;

    public static CV createCVByDirectInput(String applicantName, String input){
        return new CV(applicantName, input);
    }

    public static CV createCVByFileName(String applicantName, String path) throws IOException {
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
        return new CV(applicantName, content);
    }

    private CV(String applicantName, String content){
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
