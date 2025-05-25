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

public class LoginPage extends CommonMethods {

    @FindBy(xpath = "//div[@class='logo']//img")
    private WebElement finologyLogo;

    @FindBy(linkText = "Home")
    private WebElement homeBtn;

    @FindBy(linkText = "Sign Up")
    private WebElement signUpBtn;

    @FindBy(xpath = "//label[text()='Enter Your Mobile Number']/following-sibling::input")
    private WebElement mobileNumberInputBox;

    @FindBy(xpath = "//label[text()='Enter Your Mobile Number']/following-sibling::button")
    private WebElement continueBtn;

    // After entering invalid mobile number and clicking continue button
    @FindBy(id = "mobile-error")
    private WebElement invalidOrRequiredMobileNumberText;

    @FindBy(partialLinkText = "Sign in with Google")
    private WebElement signInWithGoogleBtn;

    @FindBy(linkText = "Login with Email")
    private WebElement loginWithEmailBtn;

    @FindBy(linkText = "Reset Password")
    private WebElement resetPasswordBtn;

    // Error while Sign Up with already created account details
    @FindBy(xpath = "//p[normalize-space()='ERROR']/following-sibling::p")
    private WebElement alreadyAccountErrorMessage;

    @FindBy(xpath = "//p[normalize-space()='ERROR']/following-sibling::a")
    private WebElement alreadyAccountErrorMessageOkBtn;

    WebDriver driver;
    WebDriverWait wait;
    static Logger log = LogManager.getLogger(LoginPage.class);
    private final String url;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        AjaxElementLocatorFactory ajax = new AjaxElementLocatorFactory(driver, 15);
        PageFactory.initElements(ajax, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
//        url = "https://www.finology.in/login?ReturnUrl=%2Faccount%2Fdashboard";
        url = "https://www.finology.in/login";
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
            return wait.until(ExpectedConditions.urlMatches("https://www.finology.in/login(?!/auth)"));
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
            return click(driver, continueBtn);
        } catch (Exception e) {
            return false;
        }
    }

//    public boolean loginUsingMobileNumberAndEmail(String number, String emailOrMobileNumber, String password) {
//        try {
//            return goToLoginPage() && verifyOnLoginPage() && enterMobileNumber(number) && clickContinueBtn() && enterMobileNumberOrEmail(emailOrMobileNumber) && enterPassword(password) && clickLoginBtn();
//        } catch (Exception e) {
//            return false;
//        }
//    }

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

//    public boolean enterMobileNumberOrEmail(String emailOrMobileNumber) {
//        try {
//            return sendKeys(emailMobileInputBox, emailOrMobileNumber);
//        } catch (Exception e) {
//            return false;
//        }
//    }

//    public boolean loginUsingEmailOrMobileNumber(String emailOrMobileNumber, String password) {
//        try {
//            return goToLoginPage() && verifyOnLoginPage() && clickLoginInWithEmailBtn() && enterMobileNumberOrEmail(emailOrMobileNumber) && enterPassword(password) && clickLoginBtn();
//        } catch (Exception e) {
//            return false;
//        }
//    }


    public boolean verifyAllElementsAreDisplayed() {
        try {
            return finologyLogoIsDisplayed() && homeButtonIsDisplayed() && signUpBtnIsDisplayed() && mobileNumberInputBoxIsDisplayed() && continueBtnIsDisplayed() && signInWithGoogleBtnIsDisplayed() && loginWithEmailBtnIsDisplayed() && resetPasswordBtnIsDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getAlreadyAccountErrorMessage() {
        try {
            return alreadyAccountErrorMessage.getText();
        } catch (Exception e) {
            return "";
        }
    }

    public String getInvalidOrRequiredMobileNumberText() {
        try {
            return invalidOrRequiredMobileNumberText.getText();
        } catch (Exception e) {
            return "";
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

    public boolean mobileNumberInputBoxIsDisplayed() {
        try {
            return mobileNumberInputBox.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean continueBtnIsDisplayed() {
        try {
            return continueBtn.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean invalidOrRequiredMobileNumberTextIsDisplayed() {
        try {
            return invalidOrRequiredMobileNumberText.isDisplayed();
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

    public boolean loginWithEmailBtnIsDisplayed() {
        try {
            return loginWithEmailBtn.isDisplayed();
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

    // Error while Sign Up with already created account details

    public boolean alreadyAccountErrorMessageIsDisplayed() {
        try {
            return alreadyAccountErrorMessage.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean alreadyAccountErrorMessageOkBtnIsDisplayed() {
        try {
            return alreadyAccountErrorMessageOkBtn.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}