package testRunner;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = {"src/test/resources/features/"},
        glue = {"in.finology.stepDefns", "in.finology.hooks"}, monochrome = true,
        dryRun = false,
        plugin = {"pretty", "html:cucumberReports/html_report.html", "json:cucumberReports/json_report.json"}, publish = true)
public class TestRunner extends AbstractTestNGCucumberTests {

    @Override
    @Test
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}