package in.finology.stepDefns;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import in.finology.utils.Base;
import in.finology.utils.ObjectManager;
import in.finology.utils.driverFactory.DriverFactory;
import io.cucumber.java.*;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import java.io.File;

public class Hooks extends Base {
    private static final Logger log = LogManager.getLogger(Hooks.class);

    @BeforeAll
    public static void any_method_name1() {
        browserName = "Chrome";
        log.info("##### Test Cases Started #####");
        System.out.println("########### " + "inside Hooks beforeAll browser name : " + browserName);

        singleTimeStamp = getSingleTimeStamp();

        String filePath = System.getProperty("user.dir") + "/extent_reports/report_" + singleTimeStamp + ".html";
        File file = new File(filePath);
        if (!file.exists()) {
            sparkReporter = new ExtentSparkReporter(filePath);

            sparkReporter.config().setDocumentTitle("YouTube Automation Report");
            sparkReporter.config().setReportName("Functional Testing");
            sparkReporter.config().setTheme(Theme.DARK); //OR Theme.STANDARD

            reports = new ExtentReports();

            reports.attachReporter(sparkReporter);

            reports.setSystemInfo("Computer Name", "Lakesh-Laptop");
            reports.setSystemInfo("Environment Name", "Production");
            reports.setSystemInfo("Tester Name", "Lakesh Sahu");
            reports.setSystemInfo("OS", "Windows 11 Home Edition");
            reports.setSystemInfo("Browser", browserName);
        }
    }

    @AfterAll
    public static void any_method_name2() {
        System.out.println("####### afterAll");

        try {
            System.out.println("########### " + "inside Hooks afterAll flushReport");

            reports.flush();
        } catch (Exception e) {
            logWarningInExtentReport(e, "Unable to flush the extent report");
            logExceptionInLog(getCallerInfoFromStackTrace(Thread.currentThread().getStackTrace()), "Unable to flush the extent report", e, Level.WARN);
        }
    }

    @Before
    public void beforeScenario() {
        try {
        System.out.println("####### inside Hooks before");

//        String className = result.getTestClass().getName();
//        String methodName = result.getName();
//        String description = result.getMethod().getDescription();
//        System.out.println("classname " + className + " methodname " + methodName + " description " + description);

        System.out.println("browser : " + browserName);
        WebDriver driver = new DriverFactory().getDriver(browserName);
        System.out.println("isDriverNull : " + (driver == null));
        ObjectManager.init(driver, "classNameUndefined1", "methodNameUndefined2", "descriptionUndefined3");
        } catch (Exception e) {
            System.out.println("######## inside catch of Hooks before");
            e.printStackTrace();
            System.out.println("##########################");
        }
    }

    @After
    public void afterScenario(Scenario scenario) {
        try {
            System.out.println("########### " + "inside Hooks after");

            if (ObjectManager.getObject() != null && ObjectManager.getObject().getDriver() != null) {
                ObjectManager.getObject().getDriver().quit();
                ObjectManager.remove();
            }
        } catch (Exception e) {
            System.out.println("inside catch of Hooks after");
            logWarningInExtentReport(e, "Unable to tearDown");
            logExceptionInLog(getCallerInfoFromStackTrace(Thread.currentThread().getStackTrace()), "Unable to tearDown", e, Level.WARN);
        }
    }

    @BeforeStep
    public void beforeStep(Scenario scenario) {
        System.out.println("########### " + "inside beforeStep");
    }

    @AfterStep
    public void afterStep(Scenario scenario) {
        System.out.println("########### " + "inside afterStep");

    }
}