package in.finology.screenshot;

import in.finology.utils.Base;
import in.finology.utils.ObjectManager;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Level;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;

public class Screenshot extends Base {

    // takes the screenshot to attach in the Extent Report
    public static String capture(String callerInfo) {
        try {
            WebDriver driver = ObjectManager.getObject().getDriver();

            if (driver != null) {
                String timestamp = String.valueOf(java.time.LocalDateTime.now()).replaceAll("[.:]", "");
                String relativePath = "/extent_reports/" + getSingleTimeStamp() + "/" + timestamp + "_" + callerInfo + ".png";
                TakesScreenshot scrShot = ((TakesScreenshot) driver);
                File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
                File DestFile = new File(System.getProperty("user.dir") + relativePath);
                FileUtils.copyFile(SrcFile, DestFile);
                return relativePath;
            }
        } catch (Exception e) {
            ObjectManager.getObject().test.warning(getCallerInfoFromStackTrace(e.getStackTrace()) + " callerInfo : " + callerInfo + " : Taking screenshot at capture : Fail : " + getMessageFromException(e));
            logExceptionInLog(getCallerInfoFromStackTrace(Thread.currentThread().getStackTrace()), " callerInfo : " + callerInfo + " : Taking screenshot at capture : Fail : " + getMessageFromException(e), e, Level.WARN);
        }
        return "";
    }

    public static byte[] captureAsBYTE(String callerInfo) {
        try {
            WebDriver driver = ObjectManager.getObject().getDriver();

            if (driver != null) {
                TakesScreenshot scrShot = ((TakesScreenshot) driver);
                return scrShot.getScreenshotAs(OutputType.BYTES);
            }
        } catch (Exception e) {
            ObjectManager.getObject().test.warning(getCallerInfoFromStackTrace(e.getStackTrace()) + " callerInfo : " + callerInfo + " : Taking screenshot at captureAsBYTE : Fail : " + getMessageFromException(e));
            logExceptionInLog(getCallerInfoFromStackTrace(Thread.currentThread().getStackTrace()), " callerInfo : " + callerInfo + " : Taking screenshot at captureAsBYTE : Fail : " + getMessageFromException(e), e, Level.WARN);
        }
        return new byte[]{};
    }

    public static String captureAsBASE64(String callerInfo) {
        try {
            WebDriver driver = ObjectManager.getObject().getDriver();

            if (driver != null) {
                TakesScreenshot scrShot = ((TakesScreenshot) driver);
                return scrShot.getScreenshotAs(OutputType.BASE64);
            }
        } catch (Exception e) {
            ObjectManager.getObject().test.warning(getCallerInfoFromStackTrace(e.getStackTrace()) + " callerInfo : " + callerInfo + " : Taking screenshot at captureAsBASE64 : Fail : " + getMessageFromException(e));
            logExceptionInLog(getCallerInfoFromStackTrace(Thread.currentThread().getStackTrace()), " callerInfo : " + callerInfo + " : Taking screenshot at captureAsBASE64 : Fail : " + getMessageFromException(e), e, Level.WARN);
        }
        return "";
    }
}