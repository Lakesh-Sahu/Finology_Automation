package in.finology.pages;

import in.finology.utils.CommonMethods;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OTPVerificationPage extends CommonMethods {

    @FindBy(xpath = "//div[@class='logo']//img")
    private WebElement finologyLogo;

    @FindBy(linkText = "Home")
    private WebElement homeBtn;

    @FindBy(xpath = "//label[normalize-space()='Enter OTP recieved on your Mobile']")
    private WebElement enterOtpText;

    @FindBy(name = "verifyOtp")
    private WebElement otpInputBox;

    @FindBy(xpath = "//button[normalize-space()='Verify OTP']")
    private WebElement verifyOtpBtn;

    @FindBy(linkText = "Resend OTP")
    private WebElement resendOTPButton;

    By byResendOTPButton = By.linkText("Resend OTP");

    WebDriver driver;
    WebDriverWait wait;
    static Logger log = LogManager.getLogger(OTPVerificationPage.class);
    private final String url;

    public OTPVerificationPage(WebDriver driver) {
        this.driver = driver;
        AjaxElementLocatorFactory ajax = new AjaxElementLocatorFactory(driver, 15);
        PageFactory.initElements(ajax, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
//        url = "https://www.finology.in/verification?source=signup&ReturnUrl=%2Faccount%2Fdashboard";
        url = "https://www.finology.in/verification";
    }

    public boolean verifyOnOTPVerificationPageUnder5Seconds() {
        try {
            return new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.urlContains(url));
        } catch (Exception e) {
            logWarningInLogFileAndExtentReport(log, e, "Exception while verifying on OTP Verification Page Under 5 Seconds");
            return false;
        }
    }

    public boolean verifyOnOTPVerificationPage() {
        try {
            return wait.until(ExpectedConditions.urlContains(url));
        } catch (Exception e) {
            logWarningInLogFileAndExtentReport(log, e, "Exception while verifying on OTP Verification Page");
            return false;
        }
    }

    public boolean verifyAllElementsExceptResendOTPAreDisplayed() {
        try {
            return finologyLogoIsDisplayed() && homeButtonIsDisplayed() && enterOTPTextIsDisplayed() && otpInputBoxIsDisplayed() && verifyOTPBtnIsDisplayed();
        } catch (Exception e) {
            logWarningInLogFileAndExtentReport(log, e, "Exception while verifying All Elements Except Resend OTP are Displayed on OTP Verification Page");
            return false;
        }
    }
    public boolean verifyAllElementsAreDisplayed() {
        try {
            return finologyLogoIsDisplayed() && homeButtonIsDisplayed() && enterOTPTextIsDisplayed() && otpInputBoxIsDisplayed() && verifyOTPBtnIsDisplayed() && resendOTPBtnIsDisplayed();
        } catch (Exception e) {
            logWarningInLogFileAndExtentReport(log, e, "Exception while verifying All Elements are Displayed on OTP Verification Page");
            return false;
        }
    }

    public boolean performOTPVerification(String otp) {
        try {
            return enterOTP(otp) && clickVerifyOTPBtn();
        } catch (Exception e) {
            logWarningInLogFileAndExtentReport(log, e, "Exception while performing OTP Verification on OTP Verification Page");
            return false;
        }
    }

    public boolean enterOTP(String otp) {
        try {
            return sendKeys(otpInputBox, otp);
        } catch (Exception e) {
            logWarningInLogFileAndExtentReport(log, e, "Exception while entering OTP on OTP Verification Page");
            return false;
        }
    }

    public boolean clickVerifyOTPBtn() {
        try {
            return click(driver, verifyOtpBtn);
        } catch (Exception e) {
            logWarningInLogFileAndExtentReport(log, e, "Exception while clicking Verify OTP Button on OTP Verification Page");
            return false;
        }
    }

    public boolean clickResendOTPBtn() {
        try {
            WebDriverWait waitFor50 = new WebDriverWait(driver, Duration.ofSeconds(50));
            return click(driver, waitFor50.until(ExpectedConditions.presenceOfElementLocated(byResendOTPButton)));
        } catch (Exception e) {
            logWarningInLogFileAndExtentReport(log, e, "Exception while clicking Resend OTP Button on OTP Verification Page");
            return false;
        }
    }

    public boolean finologyLogoIsDisplayed() {
        try {
            return finologyLogo.isDisplayed();
        } catch (Exception e) {
            logWarningInLogFileAndExtentReport(log, e, "Exception while verifying finology Logo is Displayed on OTP Verification Page");
            return false;
        }
    }

    public boolean homeButtonIsDisplayed() {
        try {
            return homeBtn.isDisplayed();
        } catch (Exception e) {
            logWarningInLogFileAndExtentReport(log, e, "Exception while verifying home Button is Displayed on OTP Verification Page");
            return false;
        }
    }

    public boolean enterOTPTextIsDisplayed() {
        try {
            return enterOtpText.isDisplayed();
        } catch (Exception e) {
            logWarningInLogFileAndExtentReport(log, e, "Exception while verifying enter OTP Text is Displayed on OTP Verification Page");
            return false;
        }
    }

    public boolean otpInputBoxIsDisplayed() {
        try {
            return otpInputBox.isDisplayed();
        } catch (Exception e) {
            logWarningInLogFileAndExtentReport(log, e, "Exception while verifying OTP Input Box is Displayed on OTP Verification Page");
            return false;
        }
    }

    public boolean verifyOTPBtnIsDisplayed() {
        try {
            return verifyOtpBtn.isDisplayed();
        } catch (Exception e) {
            logWarningInLogFileAndExtentReport(log, e, "Exception while verifying OTP Button is Displayed on OTP Verification Page");
            return false;
        }
    }

    public boolean resendOTPBtnIsDisplayed() {
        try {
            WebDriverWait waitFor50 = new WebDriverWait(driver, Duration.ofSeconds(50));
            return waitFor50.until(ExpectedConditions.presenceOfElementLocated(byResendOTPButton)).isDisplayed();
        } catch (Exception e) {
            logWarningInLogFileAndExtentReport(log, e, "Exception while verifying resend OTP Button is Displayed on OTP Verification Page");
            return false;
        }
    }
}