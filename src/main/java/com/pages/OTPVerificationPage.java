package com.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OTPVerificationPage {

    @FindBy(xpath = "//div[@class='logo']//img")
    private WebElement finologyLogo;

    @FindBy(linkText = "Home")
    private WebElement homeBtn;

    @FindBy(name = "verifyOtp")
    private WebElement otpInputBox;

    @FindBy(xpath = "//button[normalize-space()='Verify OTP']")
    private WebElement verifyOtpBtn;

    @FindBy(linkText = "Resend OTP")
    private WebElement resendOTPButton;


    WebDriver driver;
    WebDriverWait wait;
    static Logger log = LogManager.getLogger(LoginPage.class);
    private final String url = "https://www.finology.in/verification?source=signup&ReturnUrl=%2Faccount%2Fdashboard";

    public OTPVerificationPage(WebDriver driver) {
        this.driver = driver;
        AjaxElementLocatorFactory ajax = new AjaxElementLocatorFactory(driver, 15);
        PageFactory.initElements(ajax, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }
}
