package utils;

import java.util.Set;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class WebDriverHelper {
    WebDriver driver;
    Actions action;

    public WebDriverHelper(WebDriver driver){
        this.driver = driver;
        action = new Actions(driver);
    }

    public void clickOn(By loc){
        driver.findElement(loc).click();
    }

    public void sendText(By loc, String text){
        driver.findElement(loc).sendKeys(text);
    }

    public void switchWindow(){
        String parent = driver.getWindowHandle();
        Set<String> allWindowSet = driver.getWindowHandles();
        for(String child: allWindowSet){
            if(!parent.equalsIgnoreCase(child)){
                driver.switchTo().window(child);
                break;
            }
        }
    }

    public void hoverOverElement(By loc){
        WebElement elem = driver.findElement(loc);
        action.moveToElement(elem).perform();
    }

    public void enterKey(By loc){
        driver.findElement(loc).sendKeys(Keys.ENTER);
    }
    
    public void enterAction(By locator) {
    	driver.findElement(locator).sendKeys(Keys.ENTER);
    }
    
    public void highlightElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute('style', 'border: 2px solid red;');", element);
        try {
            Thread.sleep(1000); // Highlight for 1 second
        } catch (InterruptedException e) {
            e.printStackTrace();
            LoggerHandler.error(e.getMessage());
            
        }
        js.executeScript("arguments[0].setAttribute('style', '');", element);
    }
    
    public void scrollToElement(By locator) {
    	
    	JavascriptExecutor js = (JavascriptExecutor) driver;
    	js.executeScript("arguments[0].scrollIntoView(true)", driver.findElement(locator));
    	
     }
    

}
