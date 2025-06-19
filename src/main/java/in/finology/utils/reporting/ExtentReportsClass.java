package in.finology.utils.reporting;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import in.finology.utils.Base;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;

// This class is used to configure the Extent Report
public class ExtentReportsClass extends Base {
    private static final Logger log = LogManager.getLogger(ExtentReportsClass.class);

    public static void setupExtentReport() {
        try {
            String filePath = System.getProperty("user.dir") + "/extentReports/" + singleTimeStamp + "/extent_report_" + singleTimeStamp + ".html";
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
        } catch (Exception e) {
            logWarningInLogFileAndExtentReport(log, e, "Exception while doing Extent Report setup on setupExtentReport method of ExtentReportsClass class");
        }
    }
}