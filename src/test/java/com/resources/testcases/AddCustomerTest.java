package com.resources.testcases;


import com.resources.base.BaseClass;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

@Test
public class AddCustomerTest extends BaseClass {

    public void addCustomer(){

        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        webDriver.findElement(By.cssSelector(object.getProperty("addCustomerButton"))).click();
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        webDriver.findElement(By.cssSelector(object.getProperty("firstName"))).sendKeys("Harry");
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        webDriver.findElement(By.cssSelector(object.getProperty("lastName"))).sendKeys("Jackson");
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        webDriver.findElement(By.cssSelector(object.getProperty("postCode"))).sendKeys("965500");
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        webDriver.findElement(By.cssSelector(object.getProperty("addButton"))).click();
    }

}
