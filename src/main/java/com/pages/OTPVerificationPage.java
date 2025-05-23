package com.pages;

import com.CommonMethods;
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
    static Logger log;
    private final String url;

    public OTPVerificationPage(WebDriver driver) {
        this.driver = driver;
        AjaxElementLocatorFactory ajax = new AjaxElementLocatorFactory(driver, 15);
        PageFactory.initElements(ajax, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
//        url = "https://www.finology.in/verification?source=signup&ReturnUrl=%2Faccount%2Fdashboard";
        url = "https://www.finology.in/verification";
        log = LogManager.getLogger(LoginPage.class);
    }

    public boolean verifyOnOTPVerificationPageUnder10Seconds() {
        try {
            return new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.urlContains(url));
        } catch (Exception e) {
            return false;
        }
    }

    public boolean verifyOnOTPVerificationPage() {
        try {
            return wait.until(ExpectedConditions.urlContains(url));
        } catch (Exception e) {
            return false;
        }
    }

    public boolean verifyAllElementsExceptResendOTPAreDisplayed() {
        try {
            return finologyLogoIsDisplayed() && homeButtonIsDisplayed() && enterOtpTextIsDisplayed() && otpInputBoxIsDisplayed() && verifyOtpBtnIsDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    public boolean verifyAllElementsAreDisplayed() {
        try {
            return finologyLogoIsDisplayed() && homeButtonIsDisplayed() && enterOtpTextIsDisplayed() && otpInputBoxIsDisplayed() && verifyOtpBtnIsDisplayed() && resendOTPButtonIsDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean performOTPVerification(String otp) {
        try {
            return enterOtp(otp) && clickVerifyOtpBtn();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean enterOtp(String otp) {
        try {
            return sendKeys(otpInputBox, otp);
        } catch (Exception e) {
            return false;
        }
    }

    public boolean clickVerifyOtpBtn() {
        try {
            return click(driver, verifyOtpBtn);
        } catch (Exception e) {
            return false;
        }
    }

    public boolean clickResendOtpBtn() {
        try {
            WebDriverWait waitFor50 = new WebDriverWait(driver, Duration.ofSeconds(50));
            return click(driver, waitFor50.until(ExpectedConditions.presenceOfElementLocated(byResendOTPButton)));
        } catch (Exception e) {
            return false;
        }
    }

    public boolean finologyLogoIsDisplayed() {
        try {
            return finologyLogo.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean homeButtonIsDisplayed() {
        try {
            return homeBtn.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean enterOtpTextIsDisplayed() {
        try {
            return enterOtpText.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean otpInputBoxIsDisplayed() {
        try {
            return otpInputBox.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean verifyOtpBtnIsDisplayed() {
        try {
            return verifyOtpBtn.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean resendOTPButtonIsDisplayed() {
        try {
            WebDriverWait waitFor50 = new WebDriverWait(driver, Duration.ofSeconds(50));
            return waitFor50.until(ExpectedConditions.presenceOfElementLocated(byResendOTPButton)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}