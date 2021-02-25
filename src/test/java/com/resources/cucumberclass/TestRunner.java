package com.resources.cucumberclass;

import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;

@CucumberOptions(
        features = "src/test/java/com/resources/features",
        glue = "com.resources.stepdefinations" ,
        plugin = { "html:target/cucumber-html-report",
                "json:target/cucumber.json", "pretty:target/cucumber-pretty.txt",
                "usage:target/cucumber-usage.json", "junit:target/cucumber-results.xml" }
)
public class TestRunner extends AbstractTestNGCucumberTests {

}
