package in.finology.utils;

import com.aventstack.extentreports.ExtentTest;
import in.finology.pages.*;
import org.openqa.selenium.WebDriver;

// This class is used to create the Object of classes per thread for parallel execution
public class ObjectCreator extends Base{
    private final WebDriver driver;
    private final String scenarioName;
    private final String scenarioLine;
    private final String featureURI;
    private final String scenarioTags;

    public AccountDashboardPage accountDashboardPage;
    public AccountSubscriptionPage accountSubscriptionPage;
    public GoogleSignInPage googleSignInPage;
    public HomePage homePage;
    public LoginAuthPage loginAuthPage;
    public LoginPage loginPage;
    public OTPVerificationPage otpVerificationPage;
    public SignUpPage signUpPage;
    public CommonMethods cm;
    public ExtentTest test;

    public ObjectCreator(WebDriver driver, String featureURI, String scenarioName, String scenarioLine, String scenarioTags) {
        this.driver = driver;
        this.featureURI = featureURI;
        this.scenarioName = scenarioName;
        this.scenarioLine = scenarioLine;
        this.scenarioTags = scenarioTags;

        accountDashboardPage = new AccountDashboardPage(driver);
        accountSubscriptionPage = new AccountSubscriptionPage(driver);
        googleSignInPage = new GoogleSignInPage(driver);
        homePage = new HomePage(driver);
        loginAuthPage = new LoginAuthPage(driver);
        loginPage = new LoginPage(driver);
        otpVerificationPage = new OTPVerificationPage(driver);
        signUpPage = new SignUpPage(driver);
        cm = new CommonMethods();

        test = reports.createTest(featureURI + " " + scenarioName + " " + scenarioLine + " " + scenarioTags);
    }

    public WebDriver getDriver() {
        return driver;
    }

    public String getScenarioNameByUser() {
        return scenarioName;
    }

    public String getScenarioLineByUser() {
        return scenarioLine;
    }

    public String getScenarioURIByUser() {
        return featureURI;
    }

    public String getScenarioTagsByUser() {
        return scenarioTags;
    }
}