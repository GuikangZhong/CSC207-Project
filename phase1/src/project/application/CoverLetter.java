package project.application;

import java.io.*;
import java.time.LocalDateTime;

public class CoverLetter extends Document {

    public static CoverLetter createByDirectInput(String applicantName, String input, LocalDateTime dateTime) {
        return new CoverLetter(applicantName, input, dateTime);
    }

    public static CoverLetter createByFileName(String applicantName, String path, LocalDateTime dateTime) throws IOException {
        StringBuilder content = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(path))) {
            String s = in.readLine();
            while (s != null) {
                content.append(s);
                s = in.readLine();
            }
        }
        return new CoverLetter(applicantName, content.toString(), dateTime);
    }

    private CoverLetter(String applicantName, String content, LocalDateTime date) {
        super(applicantName, content, date);
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
