package com.resources.cucumberclass;

import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import org.testng.annotations.Listeners;

@CucumberOptions(
        features = "src/test/java/com/resources/features",
        glue = "com.resources.stepdefinations" ,
        monochrome = true,
        tags = "@UserLogin",
        plugin = { "html:target/cucumber-html-report.html",
                "json:target/cucumber.json", "pretty:target/cucumber-pretty.txt",
                "usage:target/cucumber-usage.json", "junit:target/cucumber-results.xml"}
)
@Listeners({com.resources.base.Listeners.class})
public class TestRunner extends AbstractTestNGCucumberTests {

}
