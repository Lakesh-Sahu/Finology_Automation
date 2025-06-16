package in.finology.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import in.finology.screenshot.Screenshot;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import java.util.Arrays;
import java.util.Map;

public class Base {
    static {
        Base.singleTimeStamp = String.valueOf(java.time.LocalDateTime.now()).replaceAll("[.:]", "").substring(0, 20);
        System.setProperty("finologySingleTimeStamp", Base.singleTimeStamp);
    }

    public static String singleTimeStamp;
    private static final Logger log = LogManager.getLogger(Base.class);
    protected static String browserName;

    protected static ExtentSparkReporter sparkReporter;
    protected static ExtentReports reports;

    protected static int maxParallelThreadAllowed;
    protected static boolean fair;


    @BeforeSuite(alwaysRun = true)
    @Parameters({"browser", "maxParallelThreadAllowed", "isFair"})
    public void beforeSuite(String browser, String threads, String isFair) {
        try {
            browserName = browser;
            maxParallelThreadAllowed = Integer.parseInt(threads);
            fair = Boolean.parseBoolean(isFair);
        } catch (Exception e) {
            logWarningInLogFile(log, e, "Exception while calling beforeSuite in Base");
        }
    }

    public static String getExamplesKeyValueFromHashMap() {
        StringBuilder sb = new StringBuilder();
        try {
            sb.append(" Examples  :: ");
            for (Map.Entry<String, String> curr : ObjectManager.getObject().examplesKeyValue.entrySet()) {
                sb.append(curr.getKey()).append(" = ").append(curr.getValue()).append(", ");
            }
            return sb.toString();
        } catch (Exception e) {
            logWarningInLogFileAndExtentReport(log, e, "Unable to getExamplesKeyValueFromHahMap in Base class");
            return "";
        }
    }

    // Used to get the class, and line number from which any other method is called, used for logging
    public static String getCallerInfoFromThrowableForScreenshot(Throwable e) {
        try {
            StringBuilder sb = new StringBuilder();
            for (StackTraceElement currElement : e.getStackTrace()) {
                if (currElement.getClassName().startsWith("in.finology")) {
                    sb.append(currElement.getClassName()).append(" ").append(currElement.getLineNumber());
                    break;
                }
            }
            return sb.toString();
        } catch (Exception ee) {
            logWarningInLogFileAndExtentReport(log, ee, "Unable to getCallerInfoFromThrowableForScreenshot in Base class");
            return "Unable to getCallerInfoFromThrowableForScreenshot on Base class";
        }
    }

    // Used to get the class, method name, and line number from which any other method is called, used for logging
    public static String getCallerInfoFromThrowable(Throwable e) {
        try {
            StringBuilder sb = new StringBuilder();
            for (StackTraceElement currElement : e.getStackTrace()) {
                if (currElement.getClassName().startsWith("in.finology")) {
                    sb.append(currElement.getClassName()).append(" ").append(currElement.getMethodName()).append(" ").append(currElement.getLineNumber());
                    break;
                }
            }
            return sb.toString();
        } catch (Exception ee) {
            logWarningInLogFileAndExtentReport(log, ee, "Unable to getCallerInfoFromThrowable in Base class");
            return "Unable to getCallerInfoFromThrowable on Base class";
        }
    }

    // Used to get the class, method name, and line number from which any other method is called, used for logging
    public static String getCallerInfoFromStackTrace(StackTraceElement[] stackTraceElement) {
        try {
            StringBuilder sb = new StringBuilder();
            for (StackTraceElement currElement : stackTraceElement) {
                if (currElement.getClassName().startsWith("in.finology")) {
                    sb.append(currElement.getClassName()).append(" ").append(currElement.getMethodName()).append(" ").append(currElement.getLineNumber());
                    break;
                }
            }
            return sb.toString();
        } catch (Exception e) {
            logWarningInLogFileAndExtentReport(log, e, "Unable to getCallerInfoFromStackTrace in Base class");
            return "";
        }
    }

    // Used to get the class, method name, and line number from which any other method is called, used for logging
    // Index 0 = getStackTrace, 1 = logCallerInfo, 2 = actual caller
    public static String getCallerInfoFromStackTraceAtIndex(StackTraceElement[] stackTraceArr, int index) {
        try {
            StackTraceElement caller = stackTraceArr[index];

            String callerClassName = caller.getClassName();
            String callerMethodName = caller.getMethodName();
            int callerLine = caller.getLineNumber();

            return (callerClassName + " " + callerMethodName + " " + callerLine);
        } catch (Exception e) {
            logWarningInLogFileAndExtentReport(log, e, "Unable to getCallerInfoFromStackTraceAtIndex in Base class");
            return "";
        }
    }

    public static String getMessageFromThrowable(Throwable e) {
        try {
            return e.getMessage().split("\n")[0];
        } catch (Exception ee) {
            logWarningInLogFileAndExtentReport(log, ee, "Unable to getMessageFromThrowable in Base class");
            return "";
        }
    }

    // log the steps or result of a test case
    public static void logLevelInLogFile(Logger currLog, Level level, String message) {
        try {
            currLog.log(level, message);
        } catch (Exception e) {
            logWarningInLogFile(currLog, e, message + "###############\nFailed to write the test log level to the Log File within the logLevelInLogFile method of the Base class " + getStackTraceFromThrowable(e, 10) + "\n###############");
            System.out.println("###############\nFailed to write the test log level to the Log File within the logLevelInLogFile method of the Base class " + Arrays.toString(e.getStackTrace()) + "\n###############");
        }
    }

    // log the steps of test case if it is FAILED
    public static void logLevelInLogFileWithException(Logger currLog, Level level, Throwable e, String message) {
        try {
            String childSelfParentString = getChildSelfParentFromThrowable(e, message);
            currLog.log(level, childSelfParentString);
        } catch (Exception ee) {
            logWarningInLogFile(currLog, e, message + "###############\nFailed to write the test log level to the Log File within the logLevelInLogFileWithException method of the Base class " + getStackTraceFromThrowable(ee, 10) + "\n###############");
            System.out.println("###############\nFailed to write the test log level to the Log File within the logLevelInLogFileWithException method of the Base class " + Arrays.toString(ee.getStackTrace()) + "\n###############");
//            logWarningInLogFileAndExtentReport(currLog, ee, "Unable to logLevelInLogFile in Base class");
        }
    }

    public static void logWarningInLogFile(Logger currLog, Throwable e, String message) {
        try {
            String childSelfParentString = getChildSelfParentFromThrowable(e, message);
            currLog.warn(childSelfParentString);
        } catch (Exception ee) {
//            logWarningInExtentReport(currLog,  e,  "##### Could not write test log warning in Log File for logWarningInLogFile : " + getStackTraceFromThrowable(ee, 10));
//                ObjectManager.getObject().test.warning("Could not write Base class log warning in log file for logWarningInLogFile : " + getStackTraceFromThrowable(ee, 10));
            System.out.println("###############\nFailed to write the test log warning to the Log File within the logWarningInLogFile method of the Base class " + Arrays.toString(ee.getStackTrace()) + "\n###############");
        }
    }


    public static void logStepInExtentReport(Status status, String details, String callerForScreenshot) {
        try {
            ObjectManager.getObject().test.log(status, details, MediaEntityBuilder.createScreenCaptureFromPath(Screenshot.capture(callerForScreenshot)).build());
        } catch (Exception e) {
            logStepInExtentReportWithoutScreenshot(status, details + "###############\nFailed to write the test log step to the Extent Report within the logStepInExtentReport method of the Base class " + getStackTraceFromThrowable(e, 10) + "\n###############");
            System.out.println("###############\nFailed to write the test log step to the Extent Report within the logStepInExtentReport method of the Base class " + Arrays.toString(e.getStackTrace()) + "\n###############");
        }
    }

    public static void logStepInExtentReportWithoutScreenshot(Status status, String details) {
        try {
            ObjectManager.getObject().test.log(status, details);
        } catch (Exception e) {
            System.out.println("###############\nFailed to write the test log step to the Extent Report within the logStepInExtentReportWithoutScreenshot method of the Base class " + Arrays.toString(e.getStackTrace()) + "\n###############");
        }
    }

    public static void logStatusInExtentReport(Logger currLog, Status status, Throwable e, String message) {
        try {
            String childSelfParentString = getChildSelfParentFromThrowable(e, message);
            ObjectManager.getObject().test.log(status, childSelfParentString, MediaEntityBuilder.createScreenCaptureFromPath(Screenshot.capture(getCallerInfoFromThrowableForScreenshot(e) + " - " + status.getName())).build());
        } catch (Exception ee) {
            logStatusInExtentReportWithoutScreenshot(currLog, status, e, message + "###############\nFailed to write the test log status to the Extent Report within the logStatusInExtentReport method of the Base class " + getStackTraceFromThrowable(ee, 10) + "\n###############");
            System.out.println("###############\nFailed to write the test log status to the Extent Report within the logStatusInExtentReport method of the Base class " + Arrays.toString(ee.getStackTrace()) + "\n###############");
//            logWarningInLogFileAndExtentReport(currLog, ee, "Unable to logStatusInExtentReport in Base class");
        }
    }

    public static void logStatusInExtentReportWithoutScreenshot(Logger currLog, Status status, Throwable e, String message) {
        try {
            String childSelfParentString = getChildSelfParentFromThrowable(e, message);
            ObjectManager.getObject().test.log(status, childSelfParentString);
        } catch (Exception ee) {
            System.out.println("###############\nFailed to write the test log status to the Extent Report within the logStatusInExtentReportWithoutScreenshot method of the Base class " + Arrays.toString(ee.getStackTrace()) + "\n###############");
//            logWarningInLogFileAndExtentReport(currLog, ee, "Unable to logStatusInExtentReportWithoutScreenshot in Base class");
        }
    }

    public static void logInfoInExtentReport(String message) {
        try {
            ObjectCreator oc = ObjectManager.getObject();

            String className = oc.getScenarioURIByUser();
            String methodName = oc.getScenarioNameByUser();
            String lineNumber = oc.getScenarioLineByUser();
            String tags = oc.getScenarioTagsByUser();

            String callerInfoForScreenshot = className + " " + lineNumber;
            String callerInfoMessageForExtent = className + " " + methodName + " " + lineNumber + " " + tags + " " + message + " - INFO";
            ObjectManager.getObject().test.info(callerInfoMessageForExtent, MediaEntityBuilder.createScreenCaptureFromPath(Screenshot.capture(callerInfoForScreenshot + " - INFO")).build());
        } catch (Exception e) {
            logInfoInExtentReportWithoutScreenshot(message);
            System.out.println("###############\nFailed to write the test log info to the Extent Report within the logInfoInExtentReport method of the Base class " + Arrays.toString(e.getStackTrace()) + "\n###############");
//            logWarningInLogFileAndExtentReport(log, e, "Unable to logInfoInExtentReport in Base class");
        }
    }

    public static void logInfoInExtentReportWithoutScreenshot(String message) {
        try {
            ObjectCreator oc = ObjectManager.getObject();

            String className = oc.getScenarioURIByUser();
            String methodName = oc.getScenarioNameByUser();
            String lineNumber = oc.getScenarioLineByUser();
            String tags = oc.getScenarioTagsByUser();

            String callerInfoMessageForExtent = className + " " + methodName + " " + lineNumber + " " + tags + " " + message + " - INFO";
            ObjectManager.getObject().test.info(callerInfoMessageForExtent);
        } catch (Exception e) {
            System.out.println("###############\nFailed to write the test log info to the Extent Report within the logInfoInExtentReportWithoutScreenshot method of the Base class " + Arrays.toString(e.getStackTrace()) + "\n###############");
//            logWarningInLogFileAndExtentReport(log, e, "Unable to logInfoInExtentReport in Base class");
        }
    }

    public static void logWarningInExtentReport(Logger currLog, Throwable e, String message) {
        try {
            String childSelfParentString = getChildSelfParentFromThrowable(e, message);
            ObjectManager.getObject().test.warning(childSelfParentString, MediaEntityBuilder.createScreenCaptureFromPath(Screenshot.capture(getCallerInfoFromThrowableForScreenshot(e) + " - WARN")).build());
        } catch (Exception ee) {
            logWarningInLogFile(log, e,"###############\nFailed to write the test log warning to the Extent Report within the logWarningInExtentReport method of the Base class " + getStackTraceFromThrowable(ee, 10) + "\n###############");
            logWarningInExtentReportWithoutScreenshot(e, message + "###############\nFailed to write the test log warning to the Extent Report within the logWarningInExtentReport method of the Base class " + getStackTraceFromThrowable(ee, 10) + "\n###############");
            System.out.println("###############\nException in the logWarningInExtentReport method in the Base class " + Arrays.toString(ee.getStackTrace()) + "\n###############");
//            currLog.fatal("Could not write test log warning in Extent Report for logWarningInExtentReport : {}", getStackTraceFromThrowable(e, 10));
//            ObjectManager.getObject().test.warning("Could not write test log warning in Extent Report for logWarningInExtentReport : " + getStackTraceFromThrowable(e, 10));
        }
    }

    public static void logWarningInExtentReportWithoutScreenshot(Throwable e, String message) {
        try {
            String childSelfParentString = getChildSelfParentFromThrowable(e, message);
            ObjectManager.getObject().test.warning(childSelfParentString);
        } catch (Exception ee) {
            logWarningInLogFile(log, ee,"###############\nFailed to write the test log warning to the Extent Report within the logWarningInExtentReportWithoutScreenshot method of the Base class " + getStackTraceFromThrowable(ee, 10) + "\n###############");
            System.out.println("###############\nFailed to write the test log warning to the Extent Report within the logWarningInExtentReportWithoutScreenshot method of the Base class " + Arrays.toString(ee.getStackTrace()) + "\n###############");
        }
    }

    public static void logWarningInLogFileAndExtentReport(Logger currLog, Throwable e, String message) {
//        try {
        logWarningInLogFile(currLog, e, message);
        logWarningInExtentReport(currLog, e, message);
//        } catch (Exception ei) {
//            logStepInLogFile(currLog, Level.FATAL, "Could not write test log warning in log file or in Extent Report for logWarningInLogFileAndExtentReport : " + getStackTraceFromThrowable(e, 10));
//            logStepInExtentReportWithoutScreenshot(Status.WARNING, "Could not write test log warning in log file or in Extent Report for logWarningInLogFileAndExtentReport : " + getStackTraceFromThrowable(e, 10));
//        }


//        try {
//            String childSelfParentString = getChildSelfParentFromThrowable(e, message);
//
//            try {
//                currLog.warn(childSelfParentString);
//            } catch (Exception ee) {
//                ObjectManager.getObject().test.warning("Could not write test log warning in log file for logWarningInLogFileAndExtentReport : " + getStackTraceFromThrowable(e, 10));
//                currLog.fatal("Could not write test log warning in log file for logWarningInLogFileAndExtentReport : {}", getStackTraceFromThrowable(e, 10));
//            }
//
//            try {
//                ObjectManager.getObject().test.warning(childSelfParentString, MediaEntityBuilder.createScreenCaptureFromPath(Screenshot.capture(getCallerInfoFromThrowableForScreenshot(e) + " - WARN")).build());
//            } catch (Exception ee) {
//                currLog.fatal("Could not write test log warning in Extent Report for logWarningInLogFileAndExtentReport : {}", getStackTraceFromThrowable(e, 10));
//                ObjectManager.getObject().test.warning("Could not write test log warning in Extent Report for logWarningInLogFileAndExtentReport : " + getStackTraceFromThrowable(e, 10));
//            }
//
//        } catch (Exception ei) {
//            currLog.fatal("Could not write test log warning in log file or in Extent Report for logWarningInLogFileAndExtentReport : {}", getStackTraceFromThrowable(e, 10));
//            ObjectManager.getObject().test.warning("Could not write test log warning in log file or in Extent Report for logWarningInLogFileAndExtentReport : " + getStackTraceFromThrowable(e, 10));
//        }
    }

    public static void logWarningInLogAndExtentReportWithoutScreenshot(Logger currLog, Throwable e, String message) {
        logWarningInLogFile(currLog, e, message);
        logWarningInExtentReportWithoutScreenshot(e, message);
    }

    public static String getChildSelfParentFromThrowable(Throwable e, String message) {
        try {
            StringBuilder sb = new StringBuilder();
            String throwableClassName = e.getClass().getName();
            String throwableMessage = getMessageFromThrowable(e);
            StackTraceElement child;
            StackTraceElement self;
            StackTraceElement parent;
            StackTraceElement[] stackTraceElements = e.getStackTrace();

            sb.append(throwableClassName).append(" Throwable Message : ").append(throwableMessage).append("\n");

            for (int i = 0; i < stackTraceElements.length; i++) {
                self = stackTraceElements[i];

                if (self.getClassName().startsWith("in.finology")) {

                    if (i > 0) {
                        child = stackTraceElements[i - 1];
                        sb.append(" #1 ").append(child.getClassName()).append(" ").append(child.getMethodName()).append(" ").append(child.getLineNumber());
                    }

                    sb.append(" #2 ").append(self.getClassName()).append(" ").append(self.getMethodName()).append(" ").append(self.getLineNumber()).append(" ").append(message);

                    if (i < stackTraceElements.length - 1) {
                        parent = stackTraceElements[i + 1];
                        sb.append(" #3 ").append(parent.getClassName()).append(" ").append(parent.getMethodName()).append(" ").append(parent.getLineNumber());
                    }
                    break;
                }
            }

            sb.append(" - WARNING");
            return sb.toString();
        } catch (Exception ee) {
            return "Unable to getChildSelfParentFromThrowable in Base class " + getMessageFromThrowable(ee);
        }
    }

    public static String getStackTraceFromThrowable(Throwable e, int numberOfStackTrace) {
        try {
            StringBuilder sb = new StringBuilder();
            StackTraceElement[] stackTraceElements = e.getStackTrace();
            sb.append(e.getClass().getName()).append(" Throwable Message : ").append(getMessageFromThrowable(e)).append("\n");
            aa:
            for (int i = 0; i < stackTraceElements.length; i++) {
                StackTraceElement currStackTraceElements = stackTraceElements[i];

                if (i == numberOfStackTrace) {
                    break;
                }

                sb.append(" #").append(i + 1).append(" ").append(currStackTraceElements.getClassName()).append(" ").append(currStackTraceElements.getMethodName()).append(" ").append(currStackTraceElements.getLineNumber()).append(",\n");

                if (currStackTraceElements.getClassName().startsWith("in.finology")) {
                    for (int j = i + 1; j < stackTraceElements.length; j++) {
                        currStackTraceElements = stackTraceElements[j];
                        if (j == numberOfStackTrace) {
                            break aa;
                        } else if (currStackTraceElements.getClassName().startsWith("in.finology")) {
                            sb.append(" #").append(j + 1).append(" ").append(currStackTraceElements.getClassName()).append(" ").append(currStackTraceElements.getMethodName()).append(" ").append(currStackTraceElements.getLineNumber()).append(",\n");
                        } else {
                            break aa;
                        }
                    }
                }
            }

            return sb.toString();
        } catch (Exception ee) {
            return "Unable to getStackTraceFromThrowable in Base class : " + getMessageFromThrowable(ee);
        }
    }
}