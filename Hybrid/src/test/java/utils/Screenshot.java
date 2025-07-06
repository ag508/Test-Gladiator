package utils;

import java.io.File;

import java.io.IOException;

import java.text.SimpleDateFormat;

import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Screenshot{
    public static WebDriver driver;
    public Screenshot(WebDriver driver){
        Screenshot.driver = driver;
    }
    

    public void takeScreenshot(String filename) {
        try{
            File screenshot = ((TakesScreenshot ) driver).getScreenshotAs(OutputType.FILE);
            File path = new File("./screenshots/"+filename+".png");
            File parent = new File(path.getParent());
            if(!parent.exists()){
                parent.mkdirs();
            }
            FileUtils.copyFile(screenshot, path);

        }catch(IOException e){
            e.printStackTrace();
            LoggerHandler.error(e.getMessage());
        }
    }

    public void captureScreenshotWithTimeStamp(String filename){
        
        try{
            String date = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
            File screenshot = ((TakesScreenshot ) driver).getScreenshotAs(OutputType.FILE);
            File path = new File(System.getProperty("user.dir")+"/screenshots/"+filename+"_"+date+".png");
            File parent = new File(path.getParent());
            if(!parent.exists()){
                parent.mkdirs();
            }
            FileUtils.copyFile(screenshot, path);

        }catch(IOException e){
            e.printStackTrace();
            LoggerHandler.error(e.getMessage());
        }

    }

    
}
