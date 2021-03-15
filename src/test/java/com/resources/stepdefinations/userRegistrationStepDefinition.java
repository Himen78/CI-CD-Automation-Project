package com.resources.stepdefinations;


import com.resources.base.BaseClass;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.junit.Cucumber;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;

import java.io.FileInputStream;
import java.util.concurrent.TimeUnit;

@RunWith(Cucumber.class)
public class userRegistrationStepDefinition extends BaseClass {

    @Given("^User is on landing a home page$")
    public void user_is_on_landing_a_home_page() throws Throwable {

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

    @When("^User navigate to sign up page and enter the required details for sign up$")
    public void user_navigate_to_sign_up_page_and_enter_the_required_details_for_sign_up() throws Throwable {

        log.info("Inside login test!!!");
        webDriver.findElement(By.xpath(object.getProperty("signUpAndLoginButton"))).click();
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        webDriver.findElement(By.xpath(object.getProperty("createNewAccount"))).click();
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        webDriver.findElement(By.xpath(object.getProperty("firstName"))).sendKeys("Himen");
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        webDriver.findElement(By.xpath(object.getProperty("lastName"))).sendKeys("Patel");
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        webDriver.findElement(By.xpath(object.getProperty("userEmailAddress"))).sendKeys("himen@gmail.com");
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        webDriver.findElement(By.xpath(object.getProperty("createAPassword"))).sendKeys("himen@78");
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        webDriver.findElement(By.xpath(object.getProperty("signUpButton"))).click();
        log.info("User registration is successfully.!!!");
    }

    @Then("^User is successfully registered$")
    public void user_is_successfully_registered() throws Throwable {

        log.info("Home Page is populated.");
        String getFullUserName = webDriver.findElement(By.xpath(object.getProperty("userFullName"))).getText();
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Assert.assertTrue(getFullUserName.equalsIgnoreCase("Himen Patel"));
        log.info("User name is verified successfully..");

    }

    @And("^Navigate to Home page$")
    public void navigate_to_home_page() throws Throwable {
        if(webDriver!=null) {
            webDriver.quit();
        }
    }

}
