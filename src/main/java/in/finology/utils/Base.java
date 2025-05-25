package in.finology.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import in.finology.screenshot.Screenshot;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import java.util.Arrays;

public class Base {

    private static final Logger log = LogManager.getLogger(Base.class);
    protected static String browserName;

    protected static ExtentSparkReporter sparkReporter;
    protected static ExtentReports reports;

    protected static String singleTimeStamp;

    @BeforeSuite
    @Parameters("browser")
    public void getTheBrowser(String browser) {
        System.out.println("############# Inside Hooks BeforeSuit getTheBrowser " + browser);
    }

//    @BeforeSuite(alwaysRun = true)
//    @Parameters("browser")
//    public void extentReportInitializer(String browser) {
//        System.out.println("########### " + "inside Base BeforeSuit extentReportInitializer");
//        browserName = browser;
//
//        singleTimeStamp = String.valueOf(java.time.LocalDateTime.now()).replaceAll("[.:]", "");
//
//        String filePath = System.getProperty("user.dir") + "/extent_reports/report_" + singleTimeStamp + ".html";
//        File file = new File(filePath);
//        if (!file.exists()) {
//            sparkReporter = new ExtentSparkReporter(filePath);
//
//            sparkReporter.config().setDocumentTitle("YouTube Automation Report");
//            sparkReporter.config().setReportName("Functional Testing");
//            sparkReporter.config().setTheme(Theme.DARK); //OR Theme.STANDARD
//
//            reports = new ExtentReports();
//
//            reports.attachReporter(sparkReporter);
//
//            reports.setSystemInfo("Computer Name", "Lakesh-Laptop");
//            reports.setSystemInfo("Environment Name", "Production");
//            reports.setSystemInfo("Tester Name", "Lakesh Sahu");
//            reports.setSystemInfo("OS", "Windows 11 Home Edition");
//            reports.setSystemInfo("Browser", browser);
//        }
//    }

//    @AfterMethod(alwaysRun = true)
//    public void tearDown() {
//        try {
//            System.out.println("########### " + "inside Base AfterMethod tearDown");
//
//            if (ContextManager.getContext() != null && ContextManager.getContext().getDriver() != null) {
//                ContextManager.getContext().getDriver().quit();
//                ContextManager.remove();
//            }
//        } catch (Exception e) {
//            logWarningInExtentReport(e, "Unable to tearDown");
//            logExceptionInLog(getCallerInfoFromStackTrace(Thread.currentThread().getStackTrace()), "Unable to tearDown", e, Level.WARN);
//        }
//    }

//    @AfterSuite(alwaysRun = true)
//    public void flushReport() {
//        try {
//            System.out.println("########### " + "inside Base AfterSuit flushReport");
//
//            reports.flush();
//        } catch (Exception e) {
//            logWarningInExtentReport(e, "Unable to flush the extent report");
//            logExceptionInLog(getCallerInfoFromStackTrace(Thread.currentThread().getStackTrace()), "Unable to flush the extent report", e, Level.WARN);
//        }
//    }


    public static String getSingleTimeStamp() {
        try {
            if (singleTimeStamp == null) {
                singleTimeStamp = String.valueOf(java.time.LocalDateTime.now()).replaceAll("[.:]", "");
            }
            return singleTimeStamp;
        } catch (Exception e) {
            return "";
        }
    }

    // Used to get the class, method name, and line number from which any other method is called, used for logging
    public static String getCallerInfoFromStackTrace(StackTraceElement[] stackTraceArr) {
        try {
            // Index 0 = getStackTrace, 1 = logCallerInfo, 2 = actual caller
            StackTraceElement caller = stackTraceArr[2];

            String callerClassName = caller.getClassName();
            String callerMethodName = caller.getMethodName();
            int callerLine = caller.getLineNumber();

            return (callerClassName + " " + callerMethodName + " " + callerLine);
        } catch (Exception e) {
            logWarningInExtentReport(e, "Unable to get the caller info from StackTraceElement[]");
            logExceptionInLog(Arrays.toString(e.getStackTrace()), "Unable to get the caller info from StackTraceElement[]", e, Level.WARN);
            return "";
        }
    }

    // Used to get the class, method name, and line number from which any other method is called, used for logging
    // Index 0 = getStackTrace, 1 = logCallerInfo, 2 = actual caller
    public static String getCallerInfo(StackTraceElement[] stackTraceArr, int index) {
        try {
            StackTraceElement caller = stackTraceArr[index];

            String callerClassName = caller.getClassName();
            String callerMethodName = caller.getMethodName();
            int callerLine = caller.getLineNumber();

            return (callerClassName + " " + callerMethodName + " " + callerLine);
        } catch (Exception e) {
            logWarningInExtentReport(e, "Unable to get the caller info from StackTraceElement[]");
            logExceptionInLog(Arrays.toString(e.getStackTrace()), "Unable to tearDown", e, Level.WARN);
            return "";
        }
    }

    public static void logWarningInExtentReport(Exception e, String message) {
        try {
            ObjectCreator oc = ObjectManager.getObject();
            String className = oc.getClassNameByUser();
            StringBuilder sb = new StringBuilder();
            StackTraceElement parent = null;

            for (StackTraceElement current : e.getStackTrace()) {
                if (current.getClassName().equals(className)) {
                    sb.append(current.getClassName()).append(" ").append(current.getMethodName()).append(" ").append(current.getLineNumber());
                    break;
                }
                parent = current;
            }
            if (parent != null) {
                sb.insert(0, " ").insert(0, parent.getLineNumber()).insert(0, " ").insert(0, parent.getMethodName()).insert(0, " ").insert(0, parent.getClassName());
            }
            String callerInfo = sb.toString();
            sb.append(" ").append(message).append(" : ").append(getMessageFromException(e)).append(" - WARNING");
            ObjectManager.getObject().test.warning(sb.toString(), MediaEntityBuilder.createScreenCaptureFromPath(Screenshot.capture(callerInfo + " - WARN")).build());

        } catch (Exception ei) {
            String callerInfo = getCallerInfoFromStackTrace(ei.getStackTrace());
            ObjectManager.getObject().test.warning("Unable to log Warning in extent report", MediaEntityBuilder.createScreenCaptureFromPath(Screenshot.capture(callerInfo + " - WARN")).build());
            logExceptionInLog(Arrays.toString(e.getStackTrace()), "Unable to log Warning in extent report", e, Level.WARN);
        }
    }

    public static String getMessageFromException(Throwable e) {
        try {
            return e.getMessage().split("\n")[0];
        } catch (Exception i) {
            return "";
        }
    }

    // log the result of test case if it is FAILED or SKIPPED
    public static void logExceptionInLog(String callerInfo, String message, Exception e, Level level) {
        try {
            StringBuilder sb = new StringBuilder();
            log.log(level, sb.append(callerInfo).append(" : ").append(message).append(" : ").append(getMessageFromException(e)).toString());
        } catch (Exception ei) {
            log.warn("Could not write test log for {} : {}", callerInfo, ei.getMessage().split("\n")[0]);
        }
    }

    public static void logInfoInExtentReport(String message) {
        try {
            ObjectCreator oc = ObjectManager.getObject();
            String className = oc.getClassNameByUser();
            String methodName = oc.getMethodNameByUser();

            StringBuilder sb = new StringBuilder();
            String callerInfo = sb.append(className).append(" ").append(methodName).toString();
            sb.append(" ").append(message).append(" - INFO");
            ObjectManager.getObject().test.info(sb.toString(), MediaEntityBuilder.createScreenCaptureFromPath(Screenshot.capture(callerInfo + " - INFO")).build());
        } catch (Exception e) {
            String callerInfo = getCallerInfoFromStackTrace(e.getStackTrace());
            ObjectManager.getObject().test.warning("Unable to log Info in extent report", MediaEntityBuilder.createScreenCaptureFromPath(Screenshot.capture(callerInfo + " - WARN")).build());
            logExceptionInLog(Arrays.toString(e.getStackTrace()), "Unable to log Info in extent report", e, Level.WARN);
        }
    }
}