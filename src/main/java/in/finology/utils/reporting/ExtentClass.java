//package in.finology.utils.reporting;
//
//import com.aventstack.extentreports.ExtentReports;
//import com.aventstack.extentreports.reporter.ExtentSparkReporter;
//import com.aventstack.extentreports.reporter.configuration.Theme;
//import org.testng.annotations.Parameters;
//
//public class ExtentClass {
//    private static ExtentSparkReporter sparkReporter;
//    private static ExtentReports reports;
//
//    @Parameters("browser")
//    public static ExtentReports getReport(String browser) {
//        if (reports == null) {
//            String singleTimeStamp = String.valueOf(java.time.LocalDateTime.now()).replaceAll("[.:]", "");
//
//            sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/reports/report_" + singleTimeStamp + ".html");
//            sparkReporter.config().setDocumentTitle("YouTube Automation Report");
//            sparkReporter.config().setReportName("Functional Testing");
//            sparkReporter.config().setTheme(Theme.DARK);
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
//        return reports;
//    }
//}
