package com.resources.base;



import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;


public class BaseClass {

    public static WebDriver webDriver;
    public static Properties config = new Properties();
    public static Properties object = new Properties();
    public static FileInputStream fis;
    public static Logger log = LogManager.getLogger(BaseClass.class.getName());

    @BeforeSuite
    public void setUp() throws IOException {

        if(webDriver==null){
            fis = new FileInputStream(System.getProperty("user.dir")+"/src/main/java/com/resources/properties/config.properties");
            config.load(fis);
            log.info("Config file is loaded!!!");

            fis = new FileInputStream(System.getProperty("user.dir")+"/src/main/java/com/resources/properties/obj.properties");
            object.load(fis);
            log.info("Object file is loaded!!!");
        }

        if(config.getProperty("browser").equals("chrome")){

            System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"/Drivers/chromedriver.exe");
            webDriver = new ChromeDriver();
            log.info("Chrome is launched!!!");

        }else if(config.getProperty("browser").equals("firefox")){

            System.setProperty("webdriver.gecko.driver",System.getProperty("user.dir")+"/Drivers/geckodriver.exe");
            webDriver = new FirefoxDriver();
            log.info("Firefox is launched!!!");

        }
        webDriver.get(config.getProperty("browserURL"));
        log.info("Navigated to:"+config.getProperty("browserURL"));
        webDriver.manage().window().maximize();
        log.info("Browser is maximized!!!");
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }


    public boolean isElementPresent(By by){

        try {
            webDriver.findElement(by);
            return true;
        }catch (NoSuchElementException e){
            return false;
        }
    }

    @AfterSuite
    public void tearDown(){

        if(webDriver!=null) {
            webDriver.quit();
        }

        log.info("Test execution is completed!!!");
    }
}
