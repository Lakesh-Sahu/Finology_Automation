package in.finology.stepDefns;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import in.finology.screenshot.Screenshot;
import in.finology.utils.Base;
import in.finology.utils.DataToExcel;
import in.finology.utils.ObjectManager;
import in.finology.utils.driverFactory.DriverFactory;
import io.cucumber.java.*;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Hooks extends Base {
    private static final Logger log = LogManager.getLogger(Hooks.class);
    public static List<Object[]> data = new ArrayList<>();
    public static int SNo = 1;


    @BeforeAll
    public static void setup() {
        try {
            String filePath = System.getProperty("user.dir") + "/extentReports/extent_report_" + singleTimeStamp + ".html";
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

            data.add(new Object[] {"SNo", "Scenario", "Examples", "Status", "URI"});
        } catch (Exception e) {
            logWarningInLogFileAndExtentReport(log, e, "Exception while doing setup on setup of Hooks");
        }
    }

    @AfterAll
    public static void afterAll() {
        try {
            DataToExcel.writeReportDataInExcel(data);
            reports.flush();
        } catch (Exception e) {
            logWarningInLogFileAndExtentReport(log, e, "Exception while calling writeReportDataInExcel or flushing Extent Report on afterAll of Hooks");
        }
    }

    @Before
    public void beforeScenario(Scenario scenario) {
        try {
            ObjectManager.acquire();

            String scenarioName = scenario.getName();
            String scenarioLine = String.valueOf(scenario.getLine());
            String featureURI = String.valueOf(scenario.getUri());
            String folderFileName = featureURI.substring(featureURI.lastIndexOf("features"));
            String scenarioTags = String.join(", ", scenario.getSourceTagNames());

            WebDriver driver = new DriverFactory().getDriver(browserName);

            ObjectManager.init(driver, folderFileName, scenarioName, scenarioLine, scenarioTags);
        } catch (Exception e) {
            logWarningInLogFileAndExtentReport(log, e, "Exception while getting driver, creating Objects, acquiring thread on beforeScenario of Hooks");
        }
    }

    @After
    public void afterScenario(Scenario scenario) {
//        try {
        addDataInList(scenario);

        String callerInfoForScreenshot = getCallerInfoForScreenshotFromScenario(scenario);
        String callerInfoTagStatusFromScenarioAndExamplesKeyValue = getCallerInfoTagStatusFromScenario(scenario) + getExamplesKeyValueFromHashMap();
        Status status = scenario.getStatus();

        logAndAttachInCucumberReport(scenario, "Test Case Finished " + callerInfoTagStatusFromScenarioAndExamplesKeyValue, "image/png", callerInfoForScreenshot);
        logLevelInLogFile(log, Level.INFO, "Test Case Finished " + callerInfoTagStatusFromScenarioAndExamplesKeyValue);

        if (status == Status.PASSED) {
            try {
                ObjectManager.getObject().test.pass(callerInfoTagStatusFromScenarioAndExamplesKeyValue, MediaEntityBuilder.createScreenCaptureFromPath(Screenshot.capture(callerInfoForScreenshot)).build());
            } catch (Exception e) {
                try {
                    ObjectManager.getObject().test.pass(callerInfoTagStatusFromScenarioAndExamplesKeyValue);
                } catch (Exception ee) {
                    logWarningInLogAndExtentReportWithoutScreenshot(log, ee, "Exception while marking Extent Test as Pass in the afterScenario method of the Hooks class");
                    logAndAttachInCucumberReport(scenario, "Exception while marking Extent Test as Pass in the afterScenario method of the Hooks class", "image/png", "Test Case Finished " + callerInfoForScreenshot);
                }
            }
        } else if (status == Status.SKIPPED) {
            try {
                ObjectManager.getObject().test.skip(callerInfoTagStatusFromScenarioAndExamplesKeyValue);
            } catch (Exception e) {
                logWarningInLogAndExtentReportWithoutScreenshot(log, e, "Exception while marking Extent Test as Skip in the afterScenario method of the Hooks class");
                logAndAttachInCucumberReport(scenario, "Exception while marking Extent Test as Skip in the afterScenario method of the Hooks class", "image/png", "Test Case Finished " + callerInfoForScreenshot);
            }
        } else {
            try {
                ObjectManager.getObject().test.fail(callerInfoTagStatusFromScenarioAndExamplesKeyValue, MediaEntityBuilder.createScreenCaptureFromPath(Screenshot.capture(callerInfoForScreenshot)).build());
            } catch (Exception e) {
                try {
                    ObjectManager.getObject().test.fail(callerInfoTagStatusFromScenarioAndExamplesKeyValue);
                } catch (Exception ee) {
                    logWarningInLogAndExtentReportWithoutScreenshot(log, ee, "Exception while marking Extent Test as Fail in the afterScenario method of Hooks class");
                    logAndAttachInCucumberReport(scenario, "Exception while marking Extent Test as Fail in the afterScenario method of Hooks class", "image/png", "Test Case Finished " + callerInfoForScreenshot);
                }
            }
        }
//        } catch (Exception e) {
//            logWarningInLogFileAndExtentReport(log, e, "Exception while checking the scenarios and quiting driver in the afterScenario method of Hooks class");
//            logAndAttachInCucumberReport(scenario, "Exception while marking Extent Test as Fail in the afterScenario method of Hooks class", "image/png", "Test Case Finished " + callerInfoForScreenshot);
//        } finally {
        try {
            if (ObjectManager.getObject() != null && ObjectManager.getObject().getDriver() != null) {
                ObjectManager.getObject().getDriver().quit();
                ObjectManager.remove();
            }
        } catch (Exception e) {
            logWarningInLogFileAndExtentReport(log, e, "Exception while quiting the driver and removing the Object in the afterScenario method of Hooks class");
            logAndAttachInCucumberReport(scenario, "Exception while quiting the driver and removing the Object in the afterScenario method of Hooks class", "image/png", "Test Case Finished " + callerInfoForScreenshot);
        }

        try {
            ObjectManager.release();
        } catch (Exception e) {
            logWarningInLogFileAndExtentReport(log, e, "Exception while releasing thread in the afterScenario method of Hooks class");
            logAndAttachInCucumberReport(scenario, "Exception while releasing thread in the afterScenario method of Hooks class", "image/png", "Test Case Finished " + callerInfoForScreenshot);
        }
//        }
    }

//    @BeforeStep
//    public void beforeStep(Scenario scenario) {
//        try {
//        } catch (Exception e) {
//            logWarningInLogAndExtentReport(log, e, "Exception while  on beforeStep of Hooks");
//        }
//    }

//    @AfterStep
//    public void afterStep(Scenario scenario) {
//        try {
//        } catch (Exception e) {
//            logWarningInLogAndExtentReport(log, e, "Exception while  on afterStep of Hooks");
//        }
//    }

    public String getCallerInfoForScreenshotFromScenario(Scenario scenario) {
        try {
            String featureUri = String.valueOf(scenario.getUri());
            String folderAndClassName = featureUri.substring(featureUri.lastIndexOf("features")).replaceAll("/", ".");
            int scenarioLine = scenario.getLine();
            String scenarioStatus = scenario.getStatus().name();

            return folderAndClassName + "_" + scenarioLine + "_" + scenarioStatus;
        } catch (Throwable e) {
            logWarningInLogFileAndExtentReport(log, e, "Exception while getting caller Info for Screenshot for Extent Report in the getCallerInfoForScreenshotFromScenario method of Hooks class");
            return "Error getCallerInfoForScreenshotFromScenario";
        }
    }

    public String getCallerInfoTagStatusFromScenario(Scenario scenario) {
        try {
            String featureUri = String.valueOf(scenario.getUri());
            String folderAndClassName = featureUri.substring(featureUri.lastIndexOf("features"));
            String scenarioName = scenario.getName();
            int scenarioLine = scenario.getLine();
            String scenarioStatus = scenario.getStatus().name();
            String tagName = String.join(", ", scenario.getSourceTagNames());

            return folderAndClassName + " : " + scenarioName + " : " + scenarioLine + " : " + tagName + " : " + scenarioStatus;
        } catch (Throwable e) {
            logWarningInLogFileAndExtentReport(log, e, "Error while getting caller Info in the getCallerInfoTagStatusFromScenario method of Hooks class");
            return "Error while getting caller Info in the getCallerInfoTagStatusFromScenario method of Hooks class : " + e.getMessage();
        }
    }

    public void logInCucumberReport(Scenario scenario, String message) {
        try {
            scenario.log(message);
        } catch (Exception e) {
            logWarningInLogAndExtentReportWithoutScreenshot(log, e, "Exception while logging in the logInCucumberReport method of Hooks class");
            try {
                scenario.log("Exception while logging in the logInCucumberReport method of Hooks class");
            } catch (Exception ignore) {
            }
        }
    }

    public void attachInCucumberReport(Scenario scenario, String mediaType, String name) {
        try {
            scenario.attach(Screenshot.captureAsBYTE(), mediaType, name);
        } catch (Exception e) {
            logWarningInLogAndExtentReportWithoutScreenshot(log, e, "Exception while attaching screenshot in the attachInCucumberReport method of Hooks class");
            try {
                scenario.log("Exception while attaching screenshot in the attachInCucumberReport method of Hooks class");
            } catch (Exception ignore) {
            }
        }
    }

    public void logAndAttachInCucumberReport(Scenario scenario, String message, String mediaType, String name) {
        logInCucumberReport(scenario, message);
        attachInCucumberReport(scenario, mediaType, name);
    }

    public void addDataInList(Scenario scenario) {
        try {
            String featureFilePath = String.valueOf(scenario.getUri()).replace(System.getProperty("user.dir"), "");
            int lastIdxOfSrc = featureFilePath.lastIndexOf("src");
            String featureFileShortPath = featureFilePath.substring(lastIdxOfSrc);

            Object[] singleData = new Object[]{SNo++, scenario.getName(), scenario.getStatus(), getExamplesKeyValueFromHashMap(), featureFileShortPath, scenario.getSourceTagNames()};
            data.add(singleData);
        } catch (Exception e) {
            logWarningInLogFileAndExtentReport(log, e, "Exception while adding data in Object[] in the addDataInList method of Hooks class");
        }
    }
}
