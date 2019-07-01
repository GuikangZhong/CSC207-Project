package project.application;

import java.io.*;
import java.time.LocalDateTime;

public class CV extends Document implements Serializable{

    private static final long serialVersionUID = 6736238269987565017L;

    public static CV createByDirectInput(String applicantName, String input, LocalDateTime dateTime) {
        return new CV(applicantName, input, dateTime);
    }

    public static CV createByFileName(String applicantName, String path, LocalDateTime dateTime) throws IOException {
        StringBuilder content = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(path))) {
            String s = in.readLine();
            while (s != null) {
                content.append(s);
                content.append("\n");
                s = in.readLine();
            }
        }
        return new CV(applicantName, content.toString(), dateTime);
    }

    private CV(String applicantName, String content, LocalDateTime date) {
        super(applicantName, content, date);
    }
}
