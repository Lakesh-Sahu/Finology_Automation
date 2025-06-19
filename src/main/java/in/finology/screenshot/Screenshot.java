package in.finology.screenshot;

import in.finology.utils.Base;
import in.finology.utils.ObjectManager;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;

public class Screenshot extends Base {
    private static final Logger log = LogManager.getLogger(Screenshot.class);

    // takes the screenshot to attach in the Extent Report
    public static String capture(String callerInfo) {
        try {
            WebDriver driver = ObjectManager.getObject().getDriver();

            if (driver != null) {
                String timestamp = String.valueOf(java.time.LocalDateTime.now()).replaceAll("[.:]", "");
                String relativePath = "screenshots/" + timestamp + "_" + callerInfo + ".png";
                TakesScreenshot scrShot = ((TakesScreenshot) driver);
                File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
                File DestFile = new File(System.getProperty("user.dir") + "/extentReports/" + singleTimeStamp + "/" + relativePath);
                FileUtils.copyFile(SrcFile, DestFile);
                return relativePath;
            }
        } catch (Exception e) {
            logWarningInLogAndExtentReportWithoutScreenshot(log, e, "Taking screenshot at capture : Fail");
        }
        return "screenshots/Error_while_capturing_screenshot.png";
    }

    public static byte[] captureAsBYTE() {
        try {
            WebDriver driver = ObjectManager.getObject().getDriver();

            if (driver != null) {
                TakesScreenshot scrShot = ((TakesScreenshot) driver);
                return scrShot.getScreenshotAs(OutputType.BYTES);
            }
        } catch (Exception e) {
            logWarningInLogAndExtentReportWithoutScreenshot(log, e, "Taking screenshot at captureAsBYTE : Fail");
        }
        return new byte[]{};
    }

    public static String captureAsBASE64() {
        try {
            WebDriver driver = ObjectManager.getObject().getDriver();

            if (driver != null) {
                TakesScreenshot scrShot = ((TakesScreenshot) driver);
                return scrShot.getScreenshotAs(OutputType.BASE64);
            }
        } catch (Exception e) {
            logWarningInLogAndExtentReportWithoutScreenshot(log, e, "Taking screenshot at captureAsBASE64 : Fail");
        }
        return "";
    }
}