package in.finology.utils;

import com.aventstack.extentreports.ExtentTest;
import in.finology.pages.*;
import org.openqa.selenium.WebDriver;

// This class is used to create the Object of classes per thread for parallel execution
public class ObjectCreator extends Base{
    private final WebDriver driver;
    private final String className;
    private final String methodName;

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

    public ObjectCreator(WebDriver driver, String className, String methodName, String description) {
        this.driver = driver;
        this.className = className;
        this.methodName = methodName;

        accountDashboardPage = new AccountDashboardPage(driver);
        accountSubscriptionPage = new AccountSubscriptionPage(driver);
        googleSignInPage = new GoogleSignInPage(driver);
        homePage = new HomePage(driver);
        loginAuthPage = new LoginAuthPage(driver);
        loginPage = new LoginPage(driver);
        otpVerificationPage = new OTPVerificationPage(driver);
        signUpPage = new SignUpPage(driver);
        cm = new CommonMethods();

        test = reports.createTest(className + " " + methodName + " " + description);
    }

    public WebDriver getDriver() {
        return driver;
    }

    public String getClassNameByUser() {
        return className;
    }

    public String getMethodNameByUser() {
        return methodName;
    }
}