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

public class LoginAuthPage extends CommonMethods {


    @FindBy(xpath = "//div[@class='logo']//img")
    private WebElement finologyLogo;

    @FindBy(linkText = "Home")
    private WebElement homeBtn;

    @FindBy(linkText = "Sign Up")
    private WebElement signUpBtn;

    // After clicking loginWithEmailBtn in same page

    @FindBy(name = "emailMobile")
    private WebElement emailMobileInputBox;

    @FindBy(name = "password")
    private WebElement passwordInputBox;

    @FindBy(id = "btnLogin")
    private WebElement loginBtn;

    @FindBy(partialLinkText = "Sign in with Google")
    private WebElement signInWithGoogleBtn;

    @FindBy(linkText = "Back to Login")
    private WebElement backToLoginBtn;

    @FindBy(linkText = "Reset Password")
    private WebElement resetPasswordBtn;

    // After entering empty mobile number or password and clicking login button
    @FindBy(id = "txtEmailMobile-error")
    private WebElement requiredEmailMobileNumberText;

    @FindBy(id = "txtPassword-error")
    private WebElement requiredPasswordText;

    // Error while wrong email/mobile number or password
    // You entered wrong details. Please re-check and try again.
    @FindBy(xpath = "//p[normalize-space()='ERROR']/following-sibling::p")
    private WebElement wrongDetailsErrorMessage;

    @FindBy(xpath = "//p[normalize-space()='ERROR']/following-sibling::a")
    private WebElement wrongDetailsErrorMessageOkBtn;


    WebDriver driver;
    WebDriverWait wait;
    static Logger log = LogManager.getLogger(LoginPage.class);
    private final String url;

    public LoginAuthPage(WebDriver driver) {
        this.driver = driver;
        AjaxElementLocatorFactory ajax = new AjaxElementLocatorFactory(driver, 15);
        PageFactory.initElements(ajax, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
//        url = "https://www.finology.in/login?ReturnUrl=%2Faccount%2Fdashboard";
        url = "https://www.finology.in/login/auth";
    }

    public boolean goToLoginAuthPage() {
        try {
            driver.get(url);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean verifyOnLoginAuthPage() {
        try {
            return wait.until(ExpectedConditions.urlToBe(url));
        } catch (Exception e) {
            return false;
        }
    }

    public boolean enterMobileNumberOrEmail(String emailOrMobileNumber) {
        try {
            return sendKeys(emailMobileInputBox, emailOrMobileNumber);
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

    public boolean performLoginUsingMobileNumberOrEmail(String emailOrMobileNumber, String password) {
        try {
            return goToLoginAuthPage() && verifyOnLoginAuthPage() && enterMobileNumberOrEmail(emailOrMobileNumber) && enterPassword(password) && clickLoginBtn();
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

    public boolean verifyAllElementsAreDisplayed() {
        try {
            return finologyLogoIsDisplayed() && homeButtonIsDisplayed() && signUpBtnIsDisplayed() && emailMobileInputBoxIsDisplayed() && passwordInputBoxIsDisplayed() && loginBtnIsDisplayed() && signInWithGoogleBtnIsDisplayed() && backToLoginBtnIsDisplayed() && resetPasswordBtnIsDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // Error while wrong email/mobile number or password
    public String getWrongDetailsErrorMessage() {
        try {
            return wrongDetailsErrorMessage.getText();
        } catch (Exception e) {
            return "";
        }
    }

    public String getRequiredEmailMobileNumberErrorMessage() {
        try {
            return requiredEmailMobileNumberText.getText();
        } catch (Exception e) {
            return "";
        }
    }

    public String getRequiredPasswordErrorMessage() {
        try {
            return requiredPasswordText.getText();
        } catch (Exception e) {
            return "";
        }
    }

    public boolean clickHomeBtn() {
        try {
            homeBtn.click();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean clickSignupBtn() {
        try {
            signUpBtn.click();
            return true;
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

    public boolean signUpBtnIsDisplayed() {
        try {
            return signUpBtn.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean signInWithGoogleBtnIsDisplayed() {
        try {
            return signInWithGoogleBtn.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean resetPasswordBtnIsDisplayed() {
        try {
            return resetPasswordBtn.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // Error while wrong email/mobile number or password
    public boolean wrongDetailsErrorMessageIsDisplayed() {
        try {
            return wrongDetailsErrorMessage.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean wrongDetailsErrorMessageOkBtnIsDisplayed() {
        try {
            return wrongDetailsErrorMessageOkBtn.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean emailMobileInputBoxIsDisplayed() {
        try {
            return emailMobileInputBox.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean passwordInputBoxIsDisplayed() {
        try {
            return passwordInputBox.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean loginBtnIsDisplayed() {
        try {
            return loginBtn.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean backToLoginBtnIsDisplayed() {
        try {
            return backToLoginBtn.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}