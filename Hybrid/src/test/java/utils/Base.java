package utils;


import java.time.Duration;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


public class Base {
    public static WebDriver driver;
    public static Properties prop;
    
    public void openBrowser(){
        try{
        prop = new Properties();
        ChromeOptions opt = new ChromeOptions();
        opt.addArguments("--disable-notifications");
        opt.addArguments("--start-maximized");
        PropertiesReader.loadProps();

        driver = new ChromeDriver(opt);
        driver.manage().window().maximize();

        
        driver.get(PropertiesReader.appURL);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        }catch(Exception e){
            e.printStackTrace();
        	LoggerHandler.error(e.getMessage());
        }
    }
}
