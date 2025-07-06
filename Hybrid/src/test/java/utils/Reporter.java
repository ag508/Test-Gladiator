package utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.TimeZone;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.google.common.io.Files;

public class Reporter {
    public static ExtentReports reports;
    public static ExtentTest test;
    
    public static ExtentReports generateReport(){
    File reportFile = new File(System.getProperty("user.dir")+"/reports/execution-report.html");
    ExtentSparkReporter spark = new ExtentSparkReporter(reportFile);
    reports = new ExtentReports();
    reports.attachReporter(spark);
    return reports;
    }


    public void flushReport(){
        reports.flush();
    }

    public void testReturn(String testMessage){
        test = reports.createTest(testMessage);
    }

    public static String captureScreenshotWithTimeStamp(String fileName){
        try{
            String date = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
            File screenshot = ((TakesScreenshot) Base.driver).getScreenshotAs(OutputType.FILE);
            File path = new File("./reports" + fileName + "_" + date + ".png");
            File parent = new File(path.getParent());
            if(!parent.exists()){
                parent.mkdirs();
            }
            FileUtils.copyFile(screenshot, path);

            return path.getAbsolutePath();

        }catch(Exception e){
            e.printStackTrace();
            LoggerHandler.error(e.getMessage());
        }
        return null;
    }

    public static String takeScreenshot(String fileName){
        String des = "./reports" + fileName + ".png";
        try{

            File screenshot = ((TakesScreenshot) Base.driver).getScreenshotAs(OutputType.FILE);
            File path = new File(des);
            File parent = new File(path.getParent());
            if(!parent.exists()){
                parent.mkdirs();
            }
            FileUtils.copyFile(screenshot, path);
        }catch(Exception e){
            e.printStackTrace();
            LoggerHandler.error(e.getMessage());
        }
        return des;
    }
    
    public static String captureScreenShot(String filename){
        String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        String name = filename + timestamp + ".png";
        String desPath = "./" + name;
        File file = ((TakesScreenshot) Base.driver).getScreenshotAs(OutputType.FILE);

        File screenshotDir = new File("./reports");

        if(!screenshotDir.exists()){
            screenshotDir.mkdirs();
        }

        File target = new File(screenshotDir, name);
        try{
            Files.copy(file, target);

        }catch(Exception e){
            e.printStackTrace();
            LoggerHandler.error(e.getMessage());
        }
        return desPath;

    }

    public static void attachScreenshotToReport(String filename, ExtentTest test, String description){
        try{
            test.log(Status.INFO, MediaEntityBuilder.createScreenCaptureFromPath(filename).build());
        }catch(Exception e){
            e.printStackTrace();
            LoggerHandler.error(e.getMessage());
        }
    }
    
    public static String captureScreenshotAsBase64(WebDriver driver, String screenshotName) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
        TimeZone istTimeZone = TimeZone.getTimeZone("Asia/Kolkata"); // IST timezone
        dateFormat.setTimeZone(istTimeZone);
        String timestamp = dateFormat.format(new Date());
 
        TakesScreenshot screenshotDriver = (TakesScreenshot) driver;
        byte[] screenshotBytes = screenshotDriver.getScreenshotAs(OutputType.BYTES);
 
        String base64Screenshot = "";
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            baos.write(screenshotBytes);
            base64Screenshot = Base64.getEncoder().encodeToString(baos.toByteArray());
 
            // Save the screenshot to a file for reference
            saveScreenshotToFile(screenshotBytes, screenshotName + "_" + timestamp + ".png");
        } catch (IOException e) {
            e.printStackTrace();
            LoggerHandler.error(e.getMessage());
        }
 
        return base64Screenshot;
    }
    
    private static String saveScreenshotToFile(byte[] screenshotBytes, String fileName) {
        String screenshotsDirPath = System.getProperty("user.dir") + "/reports/errorScreenshots/";
 
        try {
            File screenshotsDir = new File(screenshotsDirPath);
            if (!screenshotsDir.exists())
             {
                screenshotsDir.mkdirs();
            }
 
            String destinationScreenshotPath = screenshotsDirPath + fileName;
            FileOutputStream outputStream = new FileOutputStream(destinationScreenshotPath);
            outputStream.write(screenshotBytes);
            outputStream.close();
        }
 
        catch (IOException e) {
            e.printStackTrace();
            LoggerHandler.error(e.getMessage());
        }
        String destinationScreenshotPath = screenshotsDirPath + fileName;
 
        return destinationScreenshotPath;
 
 
    }

}
