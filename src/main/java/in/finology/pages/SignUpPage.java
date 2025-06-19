package in.finology.pages;

import in.finology.utils.CommonMethods;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SignUpPage extends CommonMethods {

    @FindBy(xpath = "//div[@class='logo']//img")
    private WebElement finologyLogo;

    @FindBy(linkText = "Home")
    private WebElement homeBtn;

    @FindBy(linkText = "Sign in")
    private WebElement signInBtn;

    @FindBy(name = "Name")
    private WebElement nameInputBox;

    @FindBy(name = "Email")
    private WebElement emailInputBox;

    @FindBy(name = "txtMobile")
    private WebElement mobileNumberInputBox;

    @FindBy(xpath = "//button[normalize-space('Sign Up')]")
    private WebElement signUpBtn;

    @FindBy(partialLinkText = "Sign up with Google")
    private WebElement signUpWithGoogleBtn;

    @FindBy(id = "txtCustomerName-error")
    private WebElement emptyNameMessage;

    @FindBy(id = "spanName")
    private WebElement invalidNameMessage;

    @FindBy(id = "Email-error")
    private WebElement invalidEmailMessage;

    // Error while Sign Up in incognito mode
    @FindBy(xpath = "//p[normalize-space()='ERROR']/following-sibling::p")
    private WebElement incognitoModeErrorMessage;

    @FindBy(xpath = "//p[normalize-space()='ERROR']/following-sibling::a")
    private WebElement incognitoModeErrorMessageOkBtn;

    WebDriver driver;
    WebDriverWait wait;
    private static final Logger log = LogManager.getLogger(SignUpPage.class);
//    private final String url = "https://www.finology.in/signup?ReturnUrl=%2Faccount%2Fdashboard";
    private final String url = "https://www.finology.in/signup";

    public SignUpPage(WebDriver driver) {
        this.driver = driver;
        AjaxElementLocatorFactory ajax = new AjaxElementLocatorFactory(driver, 15);
        PageFactory.initElements(ajax, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public boolean goToSignUpPage() {
        try {
            driver.get(url);
            return true;
        } catch (Exception e) {
            logWarningInLogFileAndExtentReport(log, e, "Exception while navigating to Sign Up Page");
            return false;
        }
    }

    public boolean verifyOnSignUpPage() {
        try {
            return wait.until(ExpectedConditions.urlContains(url));
        } catch (Exception e) {
            logWarningInLogFileAndExtentReport(log, e, "Exception while verifying on Sign Up Page");
            return false;
        }
    }

    public boolean verifyAllElementsAreDisplayed() {
        try {
            return finologyLogoIsDisplayed() && homeButtonIsDisplayed() && signInBtnIsDisplayed() && nameInputBoxIsDisplayed() && emailInputBoxIsDisplayed() && mobileNumberInputBoxIsDisplayed() && signUpBtnIsDisplayed() && signUpWithGoogleBtnIsDisplayed();
        } catch (Exception e) {
            logWarningInLogFileAndExtentReport(log, e, "Exception while verifying All Elements are Displayed on Sign Up Page");
            return false;
        }
    }

    public boolean enterSignUpDetails(String name, String emailId, String mobileNumber) {
        try {
            return enterName(name) && enterEmailId(emailId) && enterMobileNumber(mobileNumber);
        } catch (Exception e) {
            logWarningInLogFileAndExtentReport(log, e, "Exception while entering Sign Up Details on Sign Up Page");
            return false;
        }
    }

    public boolean performSignUpUsingNameEmailIdMobileNumber(String name, String emailId, String mobileNumber) {
        try {
            return enterName(name) && enterEmailId(emailId) && enterMobileNumber(mobileNumber) && clickSignUpBtn();
        } catch (Exception e) {
            logWarningInLogFileAndExtentReport(log, e, "Exception while performing Sign Up Using Name Email Id Mobile Number on Sign Up Page");
            return false;
        }
    }

    public boolean performSignUpUsingSignInWithGoogleBtn() {
        try {
            return clickSignInWithGoogleBtn();
        } catch (Exception e) {
            logWarningInLogFileAndExtentReport(log, e, "Exception while performing Sign Up Using Sign In With Google Button on Sign Up Page");
            return false;
        }
    }

    public String getInvalidNameErrorMessage() {
        try {
            return invalidNameMessage.getText();
        } catch (Exception e) {
            logWarningInLogFileAndExtentReport(log, e, "Exception while getting Invalid Name Error Message on Sign Up Page");
            return "";
        }
    }

    public String getEmptyNameErrorMessage() {
        try {
            return emptyNameMessage.getText();
        } catch (Exception e) {
            logWarningInLogFileAndExtentReport(log, e, "Exception while getting Empty Name Error Message on Sign Up Page");
            return "";
        }
    }

    public String getInvalidEmailErrorMessage() {
        try {
            return invalidEmailMessage.getText();
        } catch (Exception e) {
            logWarningInLogFileAndExtentReport(log, e, "Exception while getting Invalid Email Error Message on Sign Up Page");
            return "";
        }
    }

    public String getIncognitoModeErrorMessage() {
        try {
            return incognitoModeErrorMessage.getText();
        } catch (Exception e) {
            logWarningInLogFileAndExtentReport(log, e, "Exception while getting Incognito Mode Error Message on Sign Up Page");
            return "";
        }
    }

    public boolean finologyLogoIsDisplayed() {
        try {
            return finologyLogo.isDisplayed();
        } catch (Exception e) {
            logWarningInLogFileAndExtentReport(log, e, "Exception while verifying finology Logo is Displayed on Sign Up Page");
            return false;
        }
    }

    public boolean homeButtonIsDisplayed() {
        try {
            return homeBtn.isDisplayed();
        } catch (Exception e) {
            logWarningInLogFileAndExtentReport(log, e, "Exception while verifying home Button is Displayed on Sign Up Page");
            return false;
        }
    }

    public boolean signInBtnIsDisplayed() {
        try {
            return signInBtn.isDisplayed();
        } catch (Exception e) {
            logWarningInLogFileAndExtentReport(log, e, "Exception while verifying sign In Button is Displayed on Sign Up Page");
            return false;
        }
    }

    public boolean nameInputBoxIsDisplayed() {
        try {
            return nameInputBox.isDisplayed();
        } catch (Exception e) {
            logWarningInLogFileAndExtentReport(log, e, "Exception while verifying name Input Box is Displayed on Sign Up Page");
            return false;
        }
    }

    public boolean emailInputBoxIsDisplayed() {
        try {
            return emailInputBox.isDisplayed();
        } catch (Exception e) {
            logWarningInLogFileAndExtentReport(log, e, "Exception while verifying email Input Box is Displayed on Sign Up Page");
            return false;
        }
    }

    public boolean mobileNumberInputBoxIsDisplayed() {
        try {
            return mobileNumberInputBox.isDisplayed();
        } catch (Exception e) {
            logWarningInLogFileAndExtentReport(log, e, "Exception while verifying mobile Number Input Box is Displayed on Sign Up Page");
            return false;
        }
    }

    public boolean signUpBtnIsDisplayed() {
        try {
            return signUpBtn.isDisplayed();
        } catch (Exception e) {
            logWarningInLogFileAndExtentReport(log, e, "Exception while verifying sign Up Button is Displayed on Sign Up Page");
            return false;
        }
    }

    public boolean signUpWithGoogleBtnIsDisplayed() {
        try {
            return signUpWithGoogleBtn.isDisplayed();
        } catch (Exception e) {
            logWarningInLogFileAndExtentReport(log, e, "Exception while verifying sign Up With Google Button is Displayed on Sign Up Page");
            return false;
        }
    }

    public boolean enterName(String name) {
        try {
            return sendKeys(nameInputBox, name);
        } catch (Exception e) {
            logWarningInLogFileAndExtentReport(log, e, "Exception while entering Name on Sign Up Page");
            return false;
        }
    }

    public boolean enterEmailId(String emailId) {
        try {
            return sendKeys(emailInputBox, emailId);
        } catch (Exception e) {
            logWarningInLogFileAndExtentReport(log, e, "Exception while entering Email Id on Sign Up Page");
            return false;
        }
    }

    public boolean enterMobileNumber(String mobileNumber) {
        try {
            return sendKeys(mobileNumberInputBox, mobileNumber);
        } catch (Exception e) {
            logWarningInLogFileAndExtentReport(log, e, "Exception while entering Mobile Number on Sign Up Page");
            return false;
        }
    }

    public boolean clickSignUpBtn() {
        try {
            return click(driver, signUpBtn);
        } catch (Exception e) {
            logWarningInLogFileAndExtentReport(log, e, "Exception while clicking Sign Up Button on Sign Up Page");
            return false;
        }
    }

    public boolean clickSignInWithGoogleBtn() {
        try {
            return click(driver, signUpWithGoogleBtn);
        } catch (Exception e) {
            logWarningInLogFileAndExtentReport(log, e, "Exception while clicking Sign In With Google Button on Sign Up Page");
            return false;
        }
    }
}