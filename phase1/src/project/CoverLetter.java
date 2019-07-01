package project;

import java.io.*;

public class CoverLetter {
    private String applicantName;
    private String content;

    public static CoverLetter createCoverLetterByDirectInput(String applicantName, String input) {
        return new CoverLetter(applicantName, input);
    }

    public static CoverLetter createCoverLetterByFileName(String applicantName, String path) throws IOException {
        StringBuilder content = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(path))) {
            String s = in.readLine();
            while (s != null) {
                content.append(s);
                s = in.readLine();
            }
        }
        return new CoverLetter(applicantName, content.toString());
    }

    private CoverLetter(String applicantName, String content) {
        this.applicantName = applicantName;
        this.content = content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public String getApplicantName() {
        return applicantName;
    }
}
