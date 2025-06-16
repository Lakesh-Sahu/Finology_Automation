package in.finology.utils.listeners;

import com.aventstack.extentreports.MediaEntityBuilder;
import in.finology.screenshot.Screenshot;
import in.finology.utils.Base;
import in.finology.utils.ObjectManager;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener extends Base implements ITestListener {
//    private static final Logger log = LogManager.getLogger(TestListener.class);
//
//    public void onTestStart(ITestResult result) {
//        // It is running at least one time
//        try {
//            System.out.println("result getTestClass : " + result.getTestClass());
//            System.out.println("result getTestName : " + result.getTestName());
//            System.out.println("result getName : " + result.getName());
//            System.out.println("result getStatus : " + result.getStatus());
//            System.out.println("result isNotRunning : " + result.isNotRunning());
//            System.out.println("result isSuccess : " + result.isSuccess());
//        } catch (Exception e) {
//            logWarningInLogFileAndExtentReport(log, e, "Exception found on TestListener onTestStart");
//        }
//    }
//
//    public void onTestSuccess(ITestResult result) {
//        try {
//            try {
//                System.out.println("result getTestClass : " + result.getTestClass());
//                System.out.println("result getTestName : " + result.getTestName());
//                System.out.println("result getName : " + result.getName());
//                System.out.println("result getStatus : " + result.getStatus());
//                System.out.println("result isNotRunning : " + result.isNotRunning());
//                System.out.println("result isSuccess : " + result.isSuccess());
//            } catch (Exception e) {
//                logWarningInLogFileAndExtentReport(log, e, "Exception found on TestListener onTestSuccess");
//            }
//
//            String callerInfo = getCallerInfoAndStatusFromITestResult(result, "PASSED");
//
//            ObjectManager.getObject().test.pass(callerInfo, MediaEntityBuilder.createScreenCaptureFromPath(Screenshot.capture(callerInfo)).build());
//        } catch (Exception e) {
//            logWarningInLogFileAndExtentReport(log, e, "Exception found on TestListener onTestSuccess");
//        }
//    }
//
//    public void onTestFailure(ITestResult result) {
//        try {
//            System.out.println("result getTestClass : " + result.getTestClass());
//            System.out.println("result getTestName : " + result.getTestName());
//            System.out.println("result getName : " + result.getName());
//            System.out.println("result getStatus : " + result.getStatus());
//            System.out.println("result isNotRunning : " + result.isNotRunning());
//            System.out.println("result isSuccess : " + result.isSuccess());
//
//            String callerInfo = getCallerInfoAndStatusFromITestResult(result, "FAILED");
//            String throwableCallerInfoMessage = "Error occurred : " + getThrowableCallerInfoMessageFromITestResult(result, "FAILED");
//
//            ObjectManager.getObject().test.fail(throwableCallerInfoMessage, MediaEntityBuilder.createScreenCaptureFromPath(Screenshot.capture(callerInfo)).build());
//            logResultInLog(result, "FAILED");
//
//        } catch (Exception e) {
//            logWarningInLogFileAndExtentReport(log, e, "Exception while logging in the ExtentReport or in log file");
//        }
//    }
//
//    public void onTestSkipped(ITestResult result) {
//        try {
//            String callerInfo = getCallerInfoAndStatusFromITestResult(result, "SKIPPED");
//            String throwableCallerInfoMessage = "Error occurred : " + getThrowableCallerInfoMessageFromITestResult(result, "SKIPPED");
//
//            ObjectManager.getObject().test.skip(throwableCallerInfoMessage, MediaEntityBuilder.createScreenCaptureFromPath(Screenshot.capture(callerInfo)).build());
//            logResultInLog(result, "SKIPPED");
//        } catch (Exception e) {
//            logWarningInLogFile(log, e, "Exception while logging in the ExtentReport or in log file");
//        }
//    }
//
//    public void onStart(ITestContext context) {
//        logStepInLogFile(log, Level.INFO, "##### Test Cases Started #####");
//    }
//
//    public void onFinish(ITestContext context) {
//        logStepInLogFile(log, Level.INFO, "##### Test Cases Ended #####");
//    }
//
//    // log the result of test case if it is FAILED or SKIPPED
//    private void logResultInLog(ITestResult result, String status) {
//        try {
//            Throwable throwable = result.getThrowable();
//            if (throwable != null) {
//                for (StackTraceElement element : throwable.getStackTrace()) {
//                    if (element.getClassName().equals(result.getTestClass().getName())) {
//                        int lineNumber = element.getLineNumber();
//                        String fileName = element.getFileName();
//                        String message = fileName + " " + result.getMethod().getMethodName() + " " + lineNumber + " - " + result.getThrowable().getMessage() + " - " + status;
//
//                        logStepInLogFile(log, Level.ERROR, message);
//                        break;
//                    }
//                }
//            }
//        } catch (Exception e) {
//            logWarningInLogFileAndExtentReport(log, e, "Could not write test result log in log");
//        }
//    }
//
//    private String getCallerInfoAndStatusFromITestResult(ITestResult result, String status) {
//        try {
//            Throwable throwable = result.getThrowable();
//            String className = result.getTestClass().getName();
//            String methodName = result.getName();
//
//            if (throwable != null) {
//                for (StackTraceElement element : throwable.getStackTrace()) {
//                    if (element.getClassName().equals(result.getTestClass().getName())) {
//                        int lineNumber = element.getLineNumber();
//                        return className + " " + methodName + " " + lineNumber + " - " + status;
//                    }
//                }
//            } else {
//                return className + " " + methodName + " - " + status;
//            }
//        } catch (Exception e) {
//            logWarningInExtentReport(log, e, "Could not get the caller information from the ITestResult");
//            logWarningInLogFile(log, e, "Could not get the caller information from the ITestResult");
//        }
//        return "";
//    }
//
//    private String getThrowableCallerInfoMessageFromITestResult(ITestResult result, String status) {
//        try {
//            String className = result.getTestClass().getName();
//            String methodName = result.getName();
//
//            Throwable throwable = result.getThrowable();
//            if (throwable != null) {
//                for (StackTraceElement element : throwable.getStackTrace()) {
//                    if (element.getClassName().equals(result.getTestClass().getName())) {
//
//                        String throwableClassName = throwable.getClass().getName();
//                        int lineNumber = element.getLineNumber();
//                        String throwableMessage = throwable.getMessage();
//
//                        return throwableClassName + " : " + className + " " + methodName + " " + lineNumber + " - " + throwableMessage + " - " + status;
//                    }
//                }
//            } else {
//                return className + " " + methodName + " - " + status;
//            }
//        } catch (Exception e) {
//            logWarningInExtentReport(log, e, "Could not get the caller information and throwable message from the ITestResult");
//            logWarningInLogFile(log, e, "Could not get the caller information and throwable message from the ITestResult");
//        }
//        return "";
//    }
}