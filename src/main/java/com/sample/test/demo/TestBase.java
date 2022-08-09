package com.sample.test.demo;

import static org.testng.Assert.fail;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import com.sample.test.demo.pages.PizzaOrderPage;

import org.testng.annotations.AfterMethod;

public class TestBase {

    private Configuration config;
    protected PizzaOrderPage pizzaOrderPage;
    protected WebDriver driver;

    @BeforeClass(alwaysRun = true)
    public void initialize() throws Throwable {
        config = new Configuration();
        setChromeDriverPath();
    }

    @BeforeMethod(alwaysRun = true) 
    public void setup() throws Throwable {
        // Initializing new Chrome Driver for every test to ensure we start at a clean slate.
        driver = new ChromeDriver();
        pizzaOrderPage = new PizzaOrderPage(driver);
        driver.get(config.getUrl());
    }    

    @AfterMethod(alwaysRun = true)
    public void cleanup() {
        try {
            if (driver != null) {
                driver.quit();
            }   
        } catch (Exception e) {
            System.out.println("Exception occured during cleanup.");       
            System.out.println(e.toString());
        }
    }

    private void setChromeDriverPath() {        
        if (config.getBrowser().equalsIgnoreCase("chrome")) {
            if (config.getPlatform().equalsIgnoreCase("mac")) {
                System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver/mac/chromedriver");
            } else if (config.getPlatform().equalsIgnoreCase("windows")) {
                System.setProperty("webdriver.chrome.driver",
                        "src/test/resources/chromedriver/windows/104.0.5112.79/chromedriver.exe");
            } else {
                fail("Unsupported Platform" +  config.getPlatform());
            }
        }
        else {
            fail("Unsupported browser " + config.getBrowser());
        }
    }
}
