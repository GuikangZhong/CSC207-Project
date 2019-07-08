package project.utils;

import java.util.logging.Handler;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

public class Logging {
    private static Logger logger = null;
    public  static Logger getLogger(){
        if(logger == null){
            logger = Logger.getLogger("project logger");
//            logger.addHandler(new Handler() {
//                @Override
//                public void publish(LogRecord record) {
//                    System.out.println(record.getMessage());
//                }
//
//                @Override
//                public void flush() {
//
//                }
//
//                @Override
//                public void close() throws SecurityException {
//
//                }
//            });
        }
        return logger;
    }
}
