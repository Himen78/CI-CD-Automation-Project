package com.resources.stepdefinations;

import com.resources.base.BaseClass;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.junit.Cucumber;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeSuite;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

@RunWith(Cucumber.class)
public class stepDefinition {

    public static WebDriver webDriver;
    public static Properties config = new Properties();
    public static Properties object = new Properties();
    public static FileInputStream fis;
    public static Logger log = LogManager.getLogger(BaseClass.class.getName());


    @Before
    public void setUP() throws IOException {

        if(webDriver==null){
            fis = new FileInputStream(System.getProperty("user.dir")+"/src/main/java/com/resources/properties/config.properties");
            config.load(fis);
            log.info("Config file is loaded!!!");

            fis = new FileInputStream(System.getProperty("user.dir")+"/src/main/java/com/resources/properties/obj.properties");
            object.load(fis);
            log.info("Object file is loaded!!!");
        }

        if(config.getProperty("browser").equals("chrome")){

            WebDriverManager.chromedriver().setup();
            ChromeOptions opt = new ChromeOptions();
            opt.addArguments("--window-size=1920,1080");
            opt.addArguments("--start-maximized");
            opt.addArguments("--headless");
            opt.addArguments("--no-sandbox");
            opt.addArguments("--disable-dev-shm-usage");
//            opt.setHeadless(true);
            webDriver = new ChromeDriver(opt);
        }
        System.out.println("Setup method");
    }

    @After
    public void tearDown(){

        if(webDriver!=null) {
            webDriver.quit();
        }
        System.out.println("Teardown method");
    }


    @Given("User is on landing page")
    public void user_is_on_landing_page() throws IOException {

        webDriver.get(config.getProperty("browserURL"));
        log.info("Navigated to:"+config.getProperty("browserURL"));
        webDriver.manage().window().maximize();
        log.info("Browser is maximized!!!");
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }

    @When("User login into application with username and password")
    public void user_login_into_application_with_username_and_password() {

        log.info("Inside login test!!!");
        webDriver.findElement(By.xpath(object.getProperty("signUpAndLoginButton"))).click();
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        webDriver.findElement(By.xpath(object.getProperty("userName"))).sendKeys(object.getProperty("userEmail"));
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        webDriver.findElement(By.xpath(object.getProperty("password"))).sendKeys(object.getProperty("userPassword"));
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        webDriver.findElement(By.xpath(object.getProperty("submitButton"))).click();
        Reporter.log("Login successfully!!");
    }

    @Then("Home page is populated")
    public void home_page_is_populated() throws InterruptedException {

        log.info("Home Page is populated.");
        Thread.sleep(5000);
        String getFullUserName = webDriver.findElement(By.xpath(object.getProperty("userFullName"))).getText();
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Assert.assertTrue(getFullUserName.equalsIgnoreCase("Himen Patel"));
        log.info("User name is verified successfully..");
    }

    @And("User is logout successfully")
    public void user_is_logout_successfully() throws InterruptedException {




    }

}
