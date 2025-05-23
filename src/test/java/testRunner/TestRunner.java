package testRunner;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = {"src/test/resources/features/"},
        glue = {"stepDefns"}, monochrome = true,
        dryRun = false,
        tags = "@successful_login",
        plugin = {"pretty", "html:reports/html/testReport.html", "json:reports/json/testReport.json"}, publish = true)
public class TestRunner extends AbstractTestNGCucumberTests {

    @Override
    @Test
    @DataProvider(parallel = false)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}