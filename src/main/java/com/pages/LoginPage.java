package com.pages;

import com.CommonMethods;
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

public class LoginPage extends CommonMethods {

    @FindBy(xpath = "//div[@class='logo']//img")
    private WebElement finologyLogo;

    @FindBy(linkText = "Home")
    private WebElement homeBtn;

    @FindBy(linkText = "Sign Up")
    private WebElement signUpBtn;

    @FindBy(xpath = "//label[text()='Enter Your Mobile Number']/following-sibling::input")
    private WebElement mobileNumberInputBox;

    @FindBy(xpath = "//span[@id='mobile-error' and text()='Invalid Mobile']")
    private WebElement invalidMobileNumberText;

    @FindBy(xpath = "//label[text()='Enter Your Mobile Number']/following-sibling::button")
    private WebElement continueButton;

    @FindBy(name = "password")
    private WebElement passwordInputBox;

    @FindBy(id = "btnLogin")
    private WebElement loginBtn;

    @FindBy(partialLinkText = "Sign in with Google")
    private WebElement signInWithGoogleBtn;

//    @FindBy(xpath = "//a[contains(., 'Sign in with Google')]")
//    private WebElement signInWithGoogleBtn;

    @FindBy(linkText = "Login with Email")
    private WebElement loginWithEmailBtn;

    @FindBy(name = "emailMobile")
    private WebElement emailMobileInputBox;

    @FindBy(linkText = "Reset Password")
    private WebElement resetPasswordBtn;

    @FindBy(linkText = "Back to Login")
    private WebElement backToLoginBtn;

    // Error while Sign Up with already created account details
    @FindBy(xpath = "//p[normalize-space()='ERROR']/following-sibling::p")
    private WebElement alreadyAccountErrorMessage;

    @FindBy(xpath = "//p[normalize-space()='ERROR']/following-sibling::a")
    private WebElement alreadyAccountErrorMessageOkBtn;


    WebDriver driver;
    WebDriverWait wait;
    static Logger log = LogManager.getLogger(LoginPage.class);
    private final String url = "https://www.finology.in/login?ReturnUrl=%2Faccount%2Fdashboard";

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        AjaxElementLocatorFactory ajax = new AjaxElementLocatorFactory(driver, 15);
        PageFactory.initElements(ajax, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public boolean goToLoginPage() {
        try {
            driver.get(url);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean verifyOnLoginPage() {
        try {
            return wait.until(ExpectedConditions.urlContains("https://www.finology.in/login"));
        } catch (Exception e) {
            return false;
        }
    }

    public boolean enterMobileNumber(String number) {
        try {
            return sendKeys(mobileNumberInputBox, number);
        } catch (Exception e) {
            return false;
        }
    }

    public boolean clickContinueBtn() {
        try {
            return click(driver, continueButton);
        } catch (Exception e) {
            return false;
        }
    }

    public boolean enterPassword(String password) {
        try {
            return sendKeys(passwordInputBox, password);
        } catch (Exception e) {
            return false;
        }
    }

    public boolean clickLoginBtn() {
        try {
           return click(driver, loginBtn);
        } catch (Exception e) {
            return false;
        }
    }

    public boolean loginUsingMobileNumber(String number, String password) {
        try {
            return goToLoginPage() && verifyOnLoginPage() && enterMobileNumber(number) && clickContinueBtn() && enterPassword(password) && clickLoginBtn();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean clickSignInWithGoogleBtn() {
        try {
            return click(driver, signInWithGoogleBtn);
        } catch (Exception e) {
            return false;
        }
    }

    public boolean clickLoginInWithEmailBtn() {
        try {
           return click(driver, loginWithEmailBtn);
        } catch (Exception e) {
            return false;
        }
    }

    public boolean enterEmailOrMobileNumber(String emailOrMobileNumber) {
        try {
            return sendKeys(emailMobileInputBox, emailOrMobileNumber);
        } catch (Exception e) {
            return false;
        }
    }

    public boolean loginUsingEmailOrMobileNumber(String emailOrMobileNumber, String password) {
        try {
            return goToLoginPage() && verifyOnLoginPage() && clickLoginInWithEmailBtn() && enterEmailOrMobileNumber(emailOrMobileNumber) && enterPassword(password) && clickLoginBtn();
        } catch (Exception e) {
            return false;
        }
    }

































}