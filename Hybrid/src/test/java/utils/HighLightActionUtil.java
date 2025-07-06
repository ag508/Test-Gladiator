package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;


public class HighLightActionUtil {
	public WebDriver driver;
	
	public HighLightActionUtil(WebDriver driver) {
		this.driver = driver;
		}
	
    public void highlightElement(By locators) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute('style', 'border: 2px solid blue;');", driver.findElement(locators));
        try {
            Thread.sleep(1000); // Highlight for 1 second
        } catch (InterruptedException e) {
            e.printStackTrace();
            LoggerHandler.error(e.getMessage());
        }
        js.executeScript("arguments[0].setAttribute('style', '');", driver.findElement(locators));
    }
}
