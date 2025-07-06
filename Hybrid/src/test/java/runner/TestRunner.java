package runner;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import utils.Base;


@Test
public class TestRunner extends Base {

    @BeforeMethod
    public void setup() {
    try {
        openBrowser();
        // Add your test logic here
        // For example, you can call methods to interact with the web application
        } catch (Exception e) {
        e.printStackTrace();
        }
    }


    @Test
    public void runTest() {
        // This is where you would implement your test logic
        // For example, you can navigate to a page, perform actions, and assert results
        System.out.println("Running test...");
    }
    
    
    @AfterMethod
    public void tearDown() {
        try {
            if (driver != null) {
                driver.quit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
