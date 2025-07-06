package utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.FileAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

public class LoggerHandler {
    private static final Logger logger = Logger.getLogger(LoggerHandler.class);
    static{
        try{
            String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
            String logDirPath = "logs";
            File logDir = new File(logDirPath);
            if(!logDir.exists()){
                logDir.mkdirs();
            }
            String logFileName = logDirPath + "/logfile_" + timeStamp+".log";
            FileAppender fileAppender = new FileAppender(new PatternLayout("%d{ISO8601} %-5p %c - %m%n"), logFileName, true);
            logger.addAppender(fileAppender);


        }catch(Exception e){
            logger.error("Failed to initialize logger file appender", e);
        }
    }
    public static void trace(String message){
        logger.trace(message);
    }
    public static void warn(String message){
        logger.warn(message);
    }
    public static void fatal(String message){
        logger.fatal(message);
    }
    public static void debug(String message){
        logger.debug(message);
    }
    public static void info(String message){
        logger.info(message);
    }
    
    public static void error(String message) {
		logger.error(message);
	}
}
