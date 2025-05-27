package in.finology.stepDefns;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import in.finology.screenshot.Screenshot;
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
    public void beforeScenario(Scenario scenario) {
        try {
            String scenarioName = scenario.getName();
            String scenarioLine = String.valueOf(scenario.getLine());
            String featureURI = String.valueOf(scenario.getUri());
            String folderFileName = featureURI.substring(featureURI.lastIndexOf("features"));
            String scenarioTags = String.join(", ", scenario.getSourceTagNames());
//            String scenarioStatus = String.valueOf(scenario.getStatus());
//            String scenarioTagsUsingToString = scenario.getSourceTagNames().toString();
//            int scenarioHashCode = scenario.hashCode();
//            String scenarioId = scenario.getId();
//            boolean isFailed = scenario.isFailed();
//
//            System.out.println("result getName : " + scenarioName);
//            System.out.println("result getLine : " + scenarioLine);
//            System.out.println("result scenarioStatus : " + scenarioStatus);
//            System.out.println("result featureURI : " + featureURI);
//            System.out.println("result folderFileName : " + folderFileName);
//            System.out.println("result scenarioTags : " + scenarioTags);
//            System.out.println("result scenarioTagsUsingToString : " + scenarioTagsUsingToString);
//            System.out.println("result scenarioHashCode : " + scenarioHashCode);
//            System.out.println("result scenarioId : " + scenarioId);
//            System.out.println("result isFailed : " + isFailed);
//            scenario.log("log some text in terminal and in cucumber in-built report");
//            scenario.attach(Screenshot.captureAsBYTE(getCallerInfoForScreenshotFromScenario(scenario)), "image/png", getCallerInfoForScreenshotFromScenario(scenario));
//            scenario.attach("have some logging text for cucumber in-built report", "text/plain", "Also logging here");


            ObjectManager.acquire();
            WebDriver driver = new DriverFactory().getDriver(browserName);
            System.out.println("isDriverNull : " + (driver == null));

            ObjectManager.init(driver, folderFileName, scenarioName, scenarioLine, scenarioTags);

            scenario.log("logging using the scenario methods beforeScenario");
            System.out.println("Class : " + scenario.getClass());

            System.out.println("#####$$$$$ Inside beforeScenario Thread ID: " + Thread.currentThread().threadId());


            System.out.println("####### inside Hooks before complete");
        } catch (Exception e) {
            System.out.println("######## Error inside catch of Hooks before");
            e.printStackTrace();
            System.out.println("##########################");
        }
    }

    @After
    public void afterScenario(Scenario scenario) {
        try {
//            String scenarioName = scenario.getName();
//            String scenarioLine = String.valueOf(scenario.getLine());
//            String scenarioStatus = String.valueOf(scenario.getStatus());
//            String featureURI = String.valueOf(scenario.getUri());
//            String folderFileName = featureURI.substring(featureURI.lastIndexOf("features"));
//            String scenarioTags = String.join(", ", scenario.getSourceTagNames());
//            String scenarioTagsUsingToString = scenario.getSourceTagNames().toString();
//            int scenarioHashCode = scenario.hashCode();
//            String scenarioId = scenario.getId();
//            boolean isFailed = scenario.isFailed();
//
//            System.out.println("result getName : " + scenarioName);
//            System.out.println("result getLine : " + scenarioLine);
//            System.out.println("result scenarioStatus : " + scenarioStatus);
//            System.out.println("result featureURI : " + featureURI);
//            System.out.println("result folderFileName : " + folderFileName);
//            System.out.println("result scenarioTags : " + scenarioTags);
//            System.out.println("result scenarioTagsUsingToString : " + scenarioTagsUsingToString);
//            System.out.println("result scenarioHashCode : " + scenarioHashCode);
//            System.out.println("result scenarioId : " + scenarioId);
//            System.out.println("result isFailed : " + isFailed);
//            scenario.log("log some text in terminal and in cucumber in-built report");
//            scenario.attach(Screenshot.captureAsBYTE( getCallerInfoForScreenshotFromScenario(scenario)), "image/png",  getCallerInfoForScreenshotFromScenario(scenario));
//            scenario.attach("have some logging text for cucumber in-built report", "text/plain", "Also logging here");


            String callerInfoForScreenshot = getCallerInfoForScreenshotFromScenario(scenario);
            String callerInfoForExtentReport = getCallerInfoForExtentFromScenario(scenario);

            scenario.log("scenario.isFailed : " + scenario.isFailed());
            if (scenario.isFailed()) {
                ObjectManager.getObject().test.fail(callerInfoForExtentReport, MediaEntityBuilder.createScreenCaptureFromPath(Screenshot.capture(callerInfoForScreenshot)).build());
                scenario.log("taking screenshot isFailed = true");
                scenario.attach(Screenshot.captureAsBYTE(callerInfoForScreenshot), "image/png", callerInfoForScreenshot);
            } else {
                ObjectManager.getObject().test.pass(callerInfoForExtentReport, MediaEntityBuilder.createScreenCaptureFromPath(Screenshot.capture(callerInfoForScreenshot)).build());
                scenario.log("taking screenshot isFailed = false");
                scenario.attach(Screenshot.captureAsBYTE(callerInfoForScreenshot), "image/png", callerInfoForScreenshot);
            }
            scenario.log("taking screenshot isFailed = true");

            if (ObjectManager.getObject() != null && ObjectManager.getObject().getDriver() != null) {
                ObjectManager.getObject().getDriver().quit();
                ObjectManager.remove();
                ObjectManager.release();
            }
        } catch (Exception e) {
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


    public String getCallerInfoForScreenshotFromScenario(Scenario scenario) {
        try {
            String scenarioName = scenario.getName();
            int scenarioLine = scenario.getLine();
            String scenarioStatus = String.valueOf(scenario.getStatus());
            String featureUri = String.valueOf(scenario.getUri());
            String folderAndClassName = featureUri.substring(featureUri.lastIndexOf("features")).replaceAll("/", ".");

            return folderAndClassName + "_" + scenarioLine + "_" + scenarioStatus;
        } catch (Throwable e) {
            return "Error while getting caller Info for Screenshot for Extent Report in Hooks getCallerInfoForScreenshotFromScenario : " + e.getMessage();
        }
    }

    public String getCallerInfoForExtentFromScenario(Scenario scenario) {
        try {
            String featureUri = String.valueOf(scenario.getUri());
            String folderAndClassName = featureUri.substring(featureUri.lastIndexOf("features"));
            String scenarioName = scenario.getName();
            int scenarioLine = scenario.getLine();
            String scenarioStatus = String.valueOf(scenario.getStatus());
            String tagName = String.join(", ", scenario.getSourceTagNames());

            return folderAndClassName + " : " + scenarioName + " : " + scenarioLine + " : " + tagName + " : " + scenarioStatus;
        } catch (Throwable e) {
            return "Error while getting caller Info for Extent Report in Hooks getCallerInfoForExtentFromScenario : " + e.getMessage();
        }
    }

//    public String getCallerInfoFromScenario(Scenario scenario) {
//        try {
////            System.out.println("############ start inside CustomAsserts getCallerInfoForExtent ################");
////            System.out.println("########### 1");
////            System.out.println(e.getStackTrace()[0] + "#$#$#$");
////            System.out.println("########### 2");
////            for (StackTraceElement ee : e.getStackTrace()) {
////                System.out.println(ee + "###");
////            }
////            System.out.println("########### 3");
////            System.out.println("getMessage : " + e.getMessage());
////            System.out.println("########### 4");
////            System.out.println("getLocalizedMessage : " + e.getLocalizedMessage());
//
//
//            String scenarioName = scenario.getName();
//            int scenarioLine = scenario.getLine();
//            String scenarioStatus = String.valueOf(scenario.getStatus());
//            String featureUri = String.valueOf(scenario.getUri());
//            String folderAndClassName = featureUri.substring(featureUri.lastIndexOf("features"));
//
//            for (StackTraceElement element : e.getStackTrace()) {
//                if (element.getClassName().startsWith("in.finology") && !element.getClassName().contains("CustomAsserts")) {
//                    System.out.println("First application-level stack trace: " + element);
//                    System.out.println("getFileName : " + element.getFileName());
//                    System.out.println("getClassName : " + element.getClassName());
//                    System.out.println("getMethodName : " + element.getMethodName());
//                    System.out.println("getLineNumber : " + element.getLineNumber());
//
//                    return element.getClassName() + " : " + element.getMethodName() + " : " + element.getLineNumber() + " :: " + throwableClassName + " : " + throwableMessage;
//                }
//            }
//            return "Unable to find the package name starts with with in.finology in Throwable of CustomAsserts for getCallerInfoMessageForExtent";
//        } catch (Throwable ee) {
//            return "Error while getting caller Info and Message for Extent Report in CustomAsserts getCallerInfoMessageForExtent";
//        }
//    }
}
