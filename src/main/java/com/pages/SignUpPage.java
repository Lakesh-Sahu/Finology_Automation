package com.pages;

import com.CommonMethods;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
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
    private WebElement signInWithGoogleBtnTest;

    @FindBy(id = "txtCustomerName-error")
    private WebElement emptyNameMessage;

    @FindBy(id = "spanName")
    private WebElement invalidNameMessage;

    @FindBy(id = "Email-error")
    private WebElement invalidEmailMessage;

    WebDriver driver;
    WebDriverWait wait;
    static Logger log = LogManager.getLogger(SignUpPage.class);
    private final String url = "https://www.finology.in/signup?ReturnUrl=%2Faccount%2Fdashboard";

    public SignUpPage(WebDriver driver) {
        this.driver = driver;
        AjaxElementLocatorFactory ajax = new AjaxElementLocatorFactory(driver, 15);
        PageFactory.initElements(ajax, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

}