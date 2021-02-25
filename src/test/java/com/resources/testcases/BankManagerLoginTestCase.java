package com.resources.testcases;

import com.resources.base.BaseClass;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

@Test
public class BankManagerLoginTestCase extends BaseClass {

    public void loginAsBankManager() throws InterruptedException {

        log.info("Inside login test!!!");
        webDriver.findElement(By.cssSelector(object.getProperty("bankManagerLogin"))).click();
        Thread.sleep(10000);
        Assert.assertTrue(isElementPresent(By.cssSelector(object.getProperty("addCustomerButton"))),"Login not successful!!");
        log.info("Login successfully executed!!");
        Reporter.log("Login successfully!!");
    }
}
