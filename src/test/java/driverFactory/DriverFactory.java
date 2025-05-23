package driverFactory;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.util.HashMap;
import java.util.Map;

public class DriverFactory {

    private static WebDriver driver;

    private DriverFactory() {
    }

    public static WebDriver getDriver(String browser) {
        if (driver == null) {
            switch (browser) {
                case "Chrome":
                    ChromeOptions op = getChromeOptions();
                    driver = new ChromeDriver(op);
                    ((JavascriptExecutor) driver).executeScript(
                            "Object.defineProperty(navigator, 'webdriver', {get: () => undefined})"
                    );
//                    driver = new ChromeDriver();
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

    private static ChromeOptions getChromeOptions() {
        ChromeOptions op = new ChromeOptions();
//        op.addArguments("--headless=new");
//        op.addArguments("--incognito");

        op.addArguments("start-maximized");  // open Chrome browser in maximized mode
        op.addArguments("--disable-extensions"); // disable all the pre-installed or third party installed extensions
        op.addArguments("--disable-popup-blocking");    // disable blocking of popups by Chrome browser mechanism
//        op.addArguments("--disable-infobars"); //deprecated
        op.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"}); // hides the "Chrome is being controlled by automated software" banner
        op.setExperimentalOption("useAutomationExtension", false); // turns off Selenium's automation extension to reduce detection

        // Disable Chrome's password manager
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);   // disables login service or disables Chrome’s credential saving service
        prefs.put("profile.password_manager_enabled", false); // disables password manager UI prompt
        op.setExperimentalOption("prefs", prefs);

//        op.addArguments("user-data-dir=C:/Users/Lakesh Sahu/AppData/Local/Google/Chrome/User Data");  // Using a default profile
//        op.addArguments("profile-directory=Profile 8");   // changing the use of default profile into Profile 8
//
        op.addArguments("user-data-dir=D:/Selenium Projects/Selenium Profile");  // Using a default profile
        op.addArguments("profile-directory=Profile 8");   // changing the use of default profile into Profile 8
        return op;
    }
}