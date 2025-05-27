package in.finology.utils;

import com.aventstack.extentreports.MediaEntityBuilder;
import in.finology.screenshot.Screenshot;
import in.finology.utils.ObjectManager;
import org.testng.Assert;

public class CustomAsserts extends Base {

    public static void assertEquals(Object actual, Object expected, String message) {
        try {
            Assert.assertEquals(actual, expected);
        } catch (Throwable e) {
//            String callerInfo = getCallerInfo(Thread.currentThread().getStackTrace());
            testFail(message, e);
            throw e;
        }
    }

    public static void assertEquals(String actual, String expected, String message) {
        try {
            Assert.assertEquals(actual, expected);
        } catch (AssertionError e) {
//            String callerInfo = getCallerInfo(Thread.currentThread().getStackTrace());
            testFail(message,e);
            throw e;
        }
    }

    public static void assertEquals(int actual, int expected, String message) {
        try {
            Assert.assertEquals(actual, expected);
        } catch (AssertionError e) {
//            String callerInfo = getCallerInfo(Thread.currentThread().getStackTrace());
            testFail(message, e);
            throw e;
        }
    }


    public static void assertNotEquals(Object actual, Object expected, String message) {
        try {
            Assert.assertNotEquals(actual, expected);
        } catch (AssertionError e) {
//            String callerInfo = getCallerInfo(Thread.currentThread().getStackTrace());
            testFail(message, e);
            throw e;
        }
    }

    public static void assertNotEquals(String actual, String expected, String message) {
        try {
            Assert.assertNotEquals(actual, expected);
        } catch (AssertionError e) {
//            String callerInfo = getCallerInfo(Thread.currentThread().getStackTrace());
            testFail(message, e);
            throw e;
        }
    }

    public static void assertNotEquals(int actual, int expected, String message) {
        try {
            Assert.assertNotEquals(actual, expected);
        } catch (AssertionError e) {
//            String callerInfo = getCallerInfo(Thread.currentThread().getStackTrace());
            testFail(message, e);
            throw e;
        }
    }


    public static void assertNotNull(Object actual, String message) {
        try {
            Assert.assertNotNull(actual);
        } catch (AssertionError e) {
//            String callerInfo = getCallerInfo(Thread.currentThread().getStackTrace());
            testFail(message, e);
            throw e;
        }
    }

    public static void assertNotNull(String actual, String message) {
        try {
            Assert.assertNotNull(actual);
        } catch (AssertionError e) {
//            String callerInfo = getCallerInfo(Thread.currentThread().getStackTrace());
            testFail(message, e);
            throw e;
        }
    }

    public static void assertTrue(Boolean actual, String message) {
        try {
            Assert.assertTrue(actual);
        } catch (AssertionError e) {
//            String callerInfo = getCallerInfo(Thread.currentThread().getStackTrace());
            testFail(message, e);
            throw e;
        }
    }

    public static void assertTrue(boolean actual, String message) {
        try {
            Assert.assertTrue(actual);
        } catch (AssertionError e) {
//            String callerInfo = getCallerInfo(Thread.currentThread().getStackTrace());
            testFail(message, e);
            throw e;
        }
    }


    public static void assertFalse(Boolean actual, String message) {
        try {
            Assert.assertFalse(actual);
        } catch (AssertionError e) {
//            String callerInfo = getCallerInfo(Thread.currentThread().getStackTrace());
            testFail(message, e);
            throw e;
        }
    }

    public static void assertFalse(boolean actual, String message) {
        try {
            Assert.assertFalse(actual, message);
        } catch (AssertionError e) {
//            String callerInfo = getCallerInfo(Thread.currentThread().getStackTrace());
            testFail(message, e);
            throw e;
        }
    }

    public static void testFail(String message, Throwable e) {
        try {
//            String callerInfo = getCallerInfo(Thread.currentThread().getStackTrace(), 3);
            String callerInfo = getCallerInfoMessageForExtent(e);
            String callerInfoForCapture = getCallerInfoForScreenshotExtent(e);
            ObjectManager.getObject().test.fail(callerInfo + " " + message, MediaEntityBuilder.createScreenCaptureFromPath(Screenshot.capture(callerInfoForCapture)).build());
        } catch (Exception ee) {
            System.out.println("##### Unable to call the getCallerInfo or unable to capture the screenshot for the extent report for following message #####" + "\n" + message);
        }
    }

    public static void testSkip() {
        try {
//            String callerInfo = getCallerInfo(Thread.currentThread().getStackTrace(), 3);
            String callerInfo = "to be update CustomAsserts testSkip";

            ObjectManager.getObject().test.skip(callerInfo);
        } catch (Exception e) {
            System.out.println("##### Unable to call the getCallerInfo for the extent report #####");
        }
    }


    public static String getCallerInfoMessageForExtent(Throwable e) {
        try {
//            System.out.println("############ start inside CustomAsserts getCallerInfoForExtent ################");
//            System.out.println("########### 1");
//            System.out.println(e.getStackTrace()[0] + "#$#$#$");
//            System.out.println("########### 2");
//            for (StackTraceElement ee : e.getStackTrace()) {
//                System.out.println(ee + "###");
//            }
//            System.out.println("########### 3");
//            System.out.println("getMessage : " + e.getMessage());
//            System.out.println("########### 4");
//            System.out.println("getLocalizedMessage : " + e.getLocalizedMessage());



            String throwableClassName = e.getClass().getName();
            String throwableMessage = e.getMessage();
            for (StackTraceElement element : e.getStackTrace()) {
                if (element.getClassName().startsWith("in.finology") && !element.getClassName().contains("CustomAsserts")) {
                    System.out.println("First application-level stack trace: " + element);
                    System.out.println("getFileName : " + element.getFileName());
                    System.out.println("getClassName : " + element.getClassName());
                    System.out.println("getMethodName : " + element.getMethodName());
                    System.out.println("getLineNumber : " + element.getLineNumber());

                    return element.getClassName() + " : " + element.getMethodName() + " : " + element.getLineNumber() + " :: " + throwableClassName + " : " + throwableMessage;
                }
            }

//            System.out.println("############ end inside CustomAsserts getCallerInfoForExtent ################");

            return "Unable to find the package name starts with with in.finology in Throwable of CustomAsserts for getCallerInfoMessageForExtent";
        } catch (Throwable ee) {
//            System.out.println("################ error in CustomAsserts getCallerInfoMessageForExtent");
            return "Error while getting caller Info and Message for Extent Report in CustomAsserts getCallerInfoMessageForExtent";
        }
    }

    public static String getCallerInfoForScreenshotExtent(Throwable e) {
        try {
            for (StackTraceElement element : e.getStackTrace()) {
                if (element.getClassName().startsWith("in.finology") && !element.getClassName().contains("CustomAsserts")) {
//                    System.out.println("First application-level stack trace: " + element);
//                    System.out.println("getFileName : " + element.getFileName());
//                    System.out.println("getClassName : " + element.getClassName());
//                    System.out.println("getMethodName : " + element.getMethodName());
//                    System.out.println("getLineNumber : " + element.getLineNumber());

                    return element.getClassName() + "_" + element.getLineNumber();
                }
            }

//            System.out.println("############ end inside CustomAsserts getCallerInfoForExtent ################");

            return "Unable to find the package name starts with with in.finology in Throwable of CustomAsserts for getCallerInfoForScreenshotExtent";
        } catch (Throwable ee) {
//            System.out.println("################ error in CustomAsserts getCallerInfoMessageForExtent");
            return "Error while getting caller Info for Extent Report Screenshot in CustomAsserts getCallerInfoForScreenshotExtent";
        }
    }




    public void solver(Throwable e) {
        try {
            System.out.println("########### 1");

            System.out.println(e.getStackTrace()[0] + "#$#$#$");

            System.out.println("########### 2");

            for (StackTraceElement ee : e.getStackTrace()) {
                System.out.println(ee + "###");
            }

            System.out.println("########### 3");

            System.out.println(e.getMessage());

            System.out.println("########### 4");

            System.out.println("getLocalizedMessage : " + e.getLocalizedMessage());

            for (StackTraceElement element : e.getStackTrace()) {
                // Print the first line from *your* package
                if (element.getClassName().startsWith("in.finology")) {
                    System.out.println("First application-level stack trace: " + element);
                    System.out.println("getFileName : " + element.getFileName());
                    System.out.println("getClassName : " + element.getClassName());
                    System.out.println("getMethodName : " + element.getMethodName());
                    System.out.println("getLineNumber : " + element.getLineNumber());
                    break;
                }
            }

        } catch (Throwable ee) {
            System.out.println("################ error in CustomAsserts solver");
        }

    }


}