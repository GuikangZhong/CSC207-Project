package project.application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDateTime;

public class DocumentFactory implements Serializable {
    private static final long serialVersionUID = -5864681886578805239L;

    public static Document createByFileName(String name, String path, LocalDateTime dateTime, String type) throws IOException {
        StringBuilder content = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(path))) {
            String s = in.readLine();
            while (s != null) {
                content.append(s);
                content.append("\n");
                s = in.readLine();
            }
        }
        if (type.equals(CoverLetter.documentType())) {
            return new CoverLetter(name, content.toString(), dateTime);
        } else {
            return new CV(name, content.toString(), dateTime);
        }
    }
}
