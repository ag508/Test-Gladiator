package utils;

import java.io.FileInputStream;

import java.util.Properties;

public class PropertiesReader {
    public static Properties prop = new Properties();
    public static String appURL;
    public static String remURL;

    public static void loadProps(){
        try{
            FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"/config/browser.properties");
            prop.load(fis);
            appURL = prop.getProperty("applicationURL");
            remURL = prop.getProperty("remoteURL");
        }catch(Exception e){
            e.printStackTrace();
            LoggerHandler.error(e.getMessage());
        }
    }
}
