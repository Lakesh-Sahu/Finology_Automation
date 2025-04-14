import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

public class DriverFactory {

    private static WebDriver driver;

    private DriverFactory(){}

    public static WebDriver getDriver(String browser) {
        if(driver == null) {
            switch (browser) {
                case "Chrome":
                    driver = new ChromeDriver();
                    break;

                case "Edge":
                    driver = new EdgeDriver();
                    break;

                case "Firefox":
                    driver = new FirefoxDriver();
                    break;

                case "Safari":
                    driver = new SafariDriver();
                    break;

                default:
                    break;
            }
        }
        return driver;
    }
}