package project.utils;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.Clock;
import java.time.LocalDateTime;
import java.util.logging.Handler;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

public class Logging {
    private static Logger logger = null;
    private static FileWriter writer = null;
    private static PrintWriter printWriter = null;

    public static Logger getLogger() {
        if (logger == null) {
            try {
                writer = new FileWriter("log.txt", true);
                printWriter = new PrintWriter(writer);
                printWriter.println("======================================================================");
                printWriter.println("Application session on "+ LocalDateTime.now(Clock.systemDefaultZone()));
                printWriter.println("======================================================================");
            } catch (IOException e) {
                ;
            }
            logger = Logger.getLogger("project logger");
            logger.addHandler(new Handler() {
                @Override
                public void publish(LogRecord record) {
                    if (printWriter != null) {
                        printWriter.println(record.getMessage());
                    }
                }

                @Override
                public void flush() {
                    if (printWriter != null) {
                        printWriter.flush();
                    }
                }

                @Override
                public void close() throws SecurityException {
                    if (printWriter != null) {
                        printWriter.close();
                    }
                }
            });
        }
        return logger;
    }
}
