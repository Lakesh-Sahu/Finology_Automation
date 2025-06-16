package in.finology.utils;

import com.aventstack.extentreports.MediaEntityBuilder;
import in.finology.screenshot.Screenshot;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

public class CustomAsserts extends Base {
    private static final Logger log = LogManager.getLogger(CustomAsserts.class);

    public static void assertTrue(boolean actual, String message) {
        try {
            Assert.assertTrue(actual);
        } catch (Throwable e) {
            logWarningInLogFileAndExtentReportForCustomAssertClass(e, message);
            throw e;
        }
    }

    public static void assertTrue(Boolean actual, String message) {
        try {
            Assert.assertTrue(actual);
        } catch (Throwable e) {
            logWarningInLogFileAndExtentReportForCustomAssertClass(e, message);
            throw e;
        }
    }

    public static void assertFalse(Boolean actual, String message) {
        try {
            Assert.assertFalse(actual);
        } catch (Throwable e) {
            logWarningInLogFileAndExtentReportForCustomAssertClass(e, message);
            throw e;
        }
    }

    public static void assertFalse(boolean actual, String message) {
        try {
            Assert.assertFalse(actual, message);
        } catch (Throwable e) {
            logWarningInLogFileAndExtentReportForCustomAssertClass(e, message);
            throw e;
        }
    }

    public static void assertEquals(Object actual, Object expected, String message) {
        try {
            Assert.assertEquals(actual, expected);
        } catch (Throwable e) {
            logWarningInLogFileAndExtentReportForCustomAssertClass(e, message);
            throw e;
        }
    }

    public static void assertEquals(String actual, String expected, String message) {
        try {
            Assert.assertEquals(actual, expected);
        } catch (Throwable e) {
            logWarningInLogFileAndExtentReportForCustomAssertClass(e, message);
            throw e;
        }
    }

    public static void assertEquals(int actual, int expected, String message) {
        try {
            Assert.assertEquals(actual, expected);
        } catch (Throwable e) {
            logWarningInLogFileAndExtentReportForCustomAssertClass(e, message);
            throw e;
        }
    }

    public static void assertNotEquals(Object actual, Object expected, String message) {
        try {
            Assert.assertNotEquals(actual, expected);
        } catch (Throwable e) {
            logWarningInLogFileAndExtentReportForCustomAssertClass(e, message);
            throw e;
        }
    }

    public static void assertNotEquals(String actual, String expected, String message) {
        try {
            Assert.assertNotEquals(actual, expected);
        } catch (Throwable e) {
            logWarningInLogFileAndExtentReportForCustomAssertClass(e, message);
            throw e;
        }
    }

    public static void assertNotEquals(int actual, int expected, String message) {
        try {
            Assert.assertNotEquals(actual, expected);
        } catch (Throwable e) {
            logWarningInLogFileAndExtentReportForCustomAssertClass(e, message);
            throw e;
        }
    }

    public static void assertNotNull(Object actual, String message) {
        try {
            Assert.assertNotNull(actual);
        } catch (Throwable e) {
            logWarningInLogFileAndExtentReportForCustomAssertClass(e, message);
            throw e;
        }
    }

    public static void assertNotNull(String actual, String message) {
        try {
            Assert.assertNotNull(actual);
        } catch (Throwable e) {
            logWarningInLogFileAndExtentReportForCustomAssertClass(e, message);
            throw e;
        }
    }


    public static void logWarningInLogFileForCustomAssert(Throwable e, String message) {
        try {
            String childSelfParentString = getChildSelfParentFromThrowableForCustomAssertClass(e, message);
            log.warn(childSelfParentString);
        } catch (Exception ee) {
            System.out.println("###############\nFailed to write the test log warning to the Log File within the logWarningInLogFileForCustomAssert method of the CustomAssert class " + getStackTraceFromThrowable(ee, 10) + "\n###############");
        }
    }

    public static void logWarningInExtentReportForCustomAssert(Throwable e, String message) {
        try {
            String childSelfParentString = getChildSelfParentFromThrowableForCustomAssertClass(e, message);
            ObjectManager.getObject().test.warning(childSelfParentString, MediaEntityBuilder.createScreenCaptureFromPath(Screenshot.capture(getCallerInfoForScreenshotExtentReportForCustomAssert(e) + " - WARN")).build());
        } catch (Exception ee) {
            logWarningInLogFile(log, e, "###############\nFailed to write the test log warning to the Extent Report within the logWarningInExtentReportForCustomAssert method of the CustomAssert class " + getStackTraceFromThrowable(ee, 10) + "\n###############");
            logWarningInExtentReportWithoutScreenshotForCustomAssert(e, message + " ###############\nFailed to write the test log warning to the Extent Report within the logWarningInExtentReportForCustomAssert method of the CustomAssert class " + getStackTraceFromThrowable(ee, 10) + "\n###############");
            System.out.println("###############\nFailed to write the test log warning to the Extent Report within the logWarningInExtentReportForCustomAssert method of the CustomAssert class " + getStackTraceFromThrowable(ee, 10) + "\n###############");
        }
    }

    public static void logWarningInExtentReportWithoutScreenshotForCustomAssert(Throwable e, String message) {
        try {
            String childSelfParentString = getChildSelfParentFromThrowableForCustomAssertClass(e, message);
            ObjectManager.getObject().test.warning(childSelfParentString);
        } catch (Exception ee) {
            logWarningInLogFileForCustomAssert(e,"###############\nFailed to write the test log warning to the Extent Report within the logWarningInExtentReportWithoutScreenshotForCustomAssert method of the CustomAssert class " + getStackTraceFromThrowable(ee, 10) + "\n###############");
            System.out.println("###############\nFailed to write the test log warning to the Extent Report within the logWarningInExtentReportWithoutScreenshotForCustomAssert method of the CustomAssert class " + getStackTraceFromThrowable(ee, 10) + "\n###############");
        }
    }

    public static void logWarningInLogFileAndExtentReportForCustomAssertClass(Throwable e, String message) {
//        try {
            logWarningInLogFileForCustomAssert(e, message);
            logWarningInExtentReportForCustomAssert(e, message);
//        } catch (Exception ee) {
//            logWarningInLogFileForCustomAssert(ee, "Could not write test log warning in log file or in Extent Report for logWarningInLogFileAndExtentReportForCustomAssertClass : " + getStackTraceFromThrowable(ee, 10));
//            ObjectManager.getObject().test.warning("Could not write test log warning in log file or in Extent Report for logWarningInLogFileAndExtentReportForCustomAssertClass: " + getStackTraceFromThrowable(ee, 10));
//        }

//        try {
//            String childSelfParentString = getChildSelfParentFromThrowableForCustomAssertClass(e, message);
//
//            try {
//                log.warn(childSelfParentString);
//            } catch (Exception ee) {
//                ObjectManager.getObject().test.warning("Could not write test log warning in log file for logWarningInLogFileAndExtentReportForCustomAssertClass : " + getStackTraceFromThrowable(e, 10));
//                log.fatal("Could not write test log warning in log file for logWarningInLogFileAndExtentReportForCustomAssertClass : {}", getStackTraceFromThrowable(e, 10));
//            }
//
//            try {
//                ObjectManager.getObject().test.warning(childSelfParentString, MediaEntityBuilder.createScreenCaptureFromPath(Screenshot.capture(getCallerInfoForScreenshotExtentReportForCustomAssert(e) + " - WARN")).build());
//            } catch (Exception ee) {
//                log.fatal("Could not write test log warning in Extent Report for logWarningInLogFileAndExtentReportForCustomAssertClass : {}", getStackTraceFromThrowable(e, 10));
//                ObjectManager.getObject().test.warning("Could not write test log warning in Extent Report for logWarningInLogFileAndExtentReportForCustomAssertClass : " + getStackTraceFromThrowable(e, 10));
//            }
//
//        } catch (Exception ei) {
//            log.fatal("Could not write test log warning in log file or in Extent Report for logWarningInLogFileAndExtentReportForCustomAssertClass : {}", getStackTraceFromThrowable(e, 10));
//            ObjectManager.getObject().test.warning("Could not write test log warning in log file or in Extent Report for logWarningInLogFileAndExtentReportForCustomAssertClass: " + getStackTraceFromThrowable(e, 10));
//        }
    }

    public static String getChildSelfParentFromThrowableForCustomAssertClass(Throwable e, String message) {
        try {
            StringBuilder sb = new StringBuilder();
            String throwableClassName = e.getClass().getName();
            String throwableMessage = getMessageFromThrowable(e);
            StackTraceElement child;
            StackTraceElement self;
            StackTraceElement parent;
            StackTraceElement[] stackTraceElements = e.getStackTrace();

            sb.append("Custom Assert : ").append(throwableClassName).append(" Throwable Message : ").append(throwableMessage).append(" ").append(message).append("\n");

            for (int i = 0; i < stackTraceElements.length; i++) {
                self = stackTraceElements[i];

                if (self.getClassName().startsWith("in.finology")) {

                    if (i > 0) {
                        child = stackTraceElements[i - 1];
                        sb.append(" #1 ").append(child.getClassName()).append(" ").append(child.getMethodName()).append(" ").append(child.getLineNumber());
                    }

                    sb.append(" #2 ").append(self.getClassName()).append(" ").append(self.getMethodName()).append(" ").append(self.getLineNumber());

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
            return "Unable to getChildSelfParentFromThrowableForCustomAssertClass in CustomAssert class " + getMessageFromThrowable(ee);
        }
    }

    public static String getCallerInfoForScreenshotExtentReportForCustomAssert(Throwable e) {
        try {
            for (StackTraceElement element : e.getStackTrace()) {
                if (element.getClassName().startsWith("in.finology") && !element.getClassName().contains("CustomAsserts")) {
                    return element.getMethodName() + "_" + element.getLineNumber();
                }
            }
            return "Unable_to_getCallerInfoForScreenshotExtentReportForCustomAssert";
        } catch (Throwable ee) {
            return "Error_to_getCallerInfoForScreenshotExtentReportForCustomAssert";
        }
    }

    public static String getCallerInfoMessageForExtentReport(Throwable e) {
        try {
            String throwableClassName = e.getClass().getName();
            String throwableMessage = e.getMessage();
            for (StackTraceElement element : e.getStackTrace()) {
                if (element.getClassName().startsWith("in.finology") && !element.getClassName().contains("CustomAsserts")) {
                    return element.getClassName() + " : " + element.getMethodName() + " : " + element.getLineNumber() + " :: " + throwableClassName + " : " + throwableMessage;
                }
            }
            return "Unable to find the package name starts with with in.finology in Throwable of CustomAsserts for getCallerInfoMessageForExtent";
        } catch (Throwable ee) {
            return "Error while getting caller Info and Message for Extent Report in CustomAsserts getCallerInfoMessageForExtent " + getMessageFromThrowable(ee);
        }
    }
}