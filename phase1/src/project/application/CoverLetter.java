package project.application;

import java.io.*;
import java.time.LocalDateTime;

public class CoverLetter extends Document implements Serializable{

    private static final long serialVersionUID = 4089162745833731740L;

    @Override
    public final Type getType() {
        return Type.COVERLETTER;
    }

    public static CoverLetter createByDirectInput(String name, String input, LocalDateTime dateTime) {
        return new CoverLetter(name, input, dateTime);
    }

    public static CoverLetter createByFileName(String name, String path, LocalDateTime dateTime) throws IOException {
        StringBuilder content = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(path))) {
            String s = in.readLine();
            while (s != null) {
                content.append(s);
                content.append("\n");
                s = in.readLine();
            }
        }
        return new CoverLetter(name, content.toString(), dateTime);
    }

    private CoverLetter(String name, String content, LocalDateTime date) {
        super(name, content, date);
    }
}
