package in.finology.pages;

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

public class AccountSubscriptionPage {

    @FindBy(xpath = "//img[contains(@src, '/finology-logo.svg')]")
    private WebElement finologyLogo;

    @FindBy(xpath = "//h3[normalize-space() = 'Finology Subscription']/following-sibling::p")
    private WebElement welcomeText;

    @FindBy(xpath = "//a[normalize-space()='Subscribe']")
    private WebElement subscribeBtn;


    // Sidebars

    @FindBy(xpath = "//a[normalize-space()='Home']")
    private WebElement sideBarHomeBtn;

    @FindBy(xpath = "//a[normalize-space()='Subscription']")
    private WebElement sideBarSubscriptionBtn;

    @FindBy(xpath = "//a[normalize-space()='Profile']")
    private WebElement sideBarProfileBtn;

    @FindBy(xpath = "//a[normalize-space()='Password']")
    private WebElement sideBarPasswordBtn;

    @FindBy(xpath = "//a[normalize-space()='Support']")
    private WebElement sideBaSupportBtn;

    @FindBy(xpath = "//a[normalize-space()='Logout']")
    private WebElement sideBarLogoutBtn;


    @FindBy(id = "userphoto")
    private WebElement userPhotoImgBtn;

    // After clicking userPhotoImgBtn

    @FindBy(xpath = "//a[contains(., 'DashboardYour financial dashboard')]")
    private WebElement userPhotoDashboardBtn;

    @FindBy(xpath = "//a[contains(., 'ProfileUpdate your profile')]")
    private WebElement userPhotoProfileBtn;

    @FindBy(xpath = "//a[contains(., 'SupportAsk us your queries')]")
    private WebElement userPhotoSupportBtn;

    @FindBy(xpath = "//a[contains(., 'LogoutLogout of all products')]")
    private WebElement userPhotoLogoutBtn;


    WebDriver driver;
    WebDriverWait wait;
    static Logger log = LogManager.getLogger(LoginPage.class);
    private final String url;

    public AccountSubscriptionPage(WebDriver driver) {
        this.driver = driver;
        AjaxElementLocatorFactory ajax = new AjaxElementLocatorFactory(driver, 15);
        PageFactory.initElements(ajax, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        url = "https://www.finology.in/account/subscription";
    }

    public boolean goToAccountSubscriptionPage() {
        try {
            driver.get(url);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean verifyOnAccountSubscriptionPage() {
        try {
            return wait.until(ExpectedConditions.urlToBe(url));
        } catch (Exception e) {
            return false;
        }
    }

    public boolean clickUserPhotoImgBtn() {
        try {
            userPhotoImgBtn.click();
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

    public boolean welcomeTextIsDisplayed() {
        try {
            return welcomeText.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean subscribeBtnIsDisplayed() {
        try {
            return subscribeBtn.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean sideBarHomeBtnIsDisplayed() {
        try {
            return sideBarHomeBtn.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean sideBarSubscriptionBtnIsDisplayed() {
        try {
            return sideBarSubscriptionBtn.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean sideBarProfileBtnIsDisplayed() {
        try {
            return sideBarProfileBtn.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean sideBarPasswordBtnIsDisplayed() {
        try {
            return sideBarPasswordBtn.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean sideBarSupportBtnIsDisplayed() {
        try {
            return sideBaSupportBtn.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean sideBarLogoutBtnIsDisplayed() {
        try {
            return sideBarLogoutBtn.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean userPhotoImgBtnIsDisplayed() {
        try {
            return userPhotoImgBtn.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }


    public boolean clickFinologyLogo() {
        try {
            finologyLogo.click();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean clickSubscribeBtn() {
        try {
            subscribeBtn.click();
            return true;
        } catch (Exception e) {
            return false;
        }
    }




    public boolean clickSideBarHomeBtn() {
        try {
            sideBarHomeBtn.click();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean clickSideBarSubscriptionBtn() {
        try {
            sideBarSubscriptionBtn.click();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean clickSideBarProfileBtn() {
        try {
            sideBarProfileBtn.click();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean clickSideBarPasswordBtn() {
        try {
            sideBarPasswordBtn.click();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean clickSideBarSupportBtn() {
        try {
            sideBaSupportBtn.click();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean clickSideBarLogoutBtn() {
        try {
            sideBarLogoutBtn.click();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // After clicking userPhotoImgBtn

    public boolean userPhotoDashboardBtnIsDisplayed() {
        try {
            return userPhotoDashboardBtn.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean userPhotoProfileBtnIsDisplayed() {
        try {
            return userPhotoProfileBtn.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean userPhotoSupportBtnIsDisplayed() {
        try {
            return userPhotoSupportBtn.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean userPhotoLogoutBtnIsDisplayed() {
        try {
            return userPhotoLogoutBtn.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean clickUserPhotoDashboardBtn() {
        try {
            userPhotoDashboardBtn.click();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean clickUserPhotoProfileBtn() {
        try {
            userPhotoProfileBtn.click();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean clickUserPhotoSupportBtn() {
        try {
            userPhotoSupportBtn.click();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean clickUserPhotoLogoutBtn() {
        try {
            userPhotoLogoutBtn.click();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getWelcomeText() {
        try {
            return welcomeText.getText();
        } catch (Exception e) {
            return "";
        }
    }

    public boolean verifyAllElementsAreDisplayed() {
        try {
            return finologyLogoIsDisplayed() && welcomeTextIsDisplayed() && subscribeBtnIsDisplayed() && userPhotoImgBtnIsDisplayed() && sideBarHomeBtnIsDisplayed() && sideBarSubscriptionBtnIsDisplayed() && sideBarProfileBtnIsDisplayed() && sideBarPasswordBtnIsDisplayed() && sideBarSupportBtnIsDisplayed() && sideBarLogoutBtnIsDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean verifyAllUserPhotoElementsAreDisplayed() {
        try {
            return userPhotoDashboardBtnIsDisplayed() && userPhotoProfileBtnIsDisplayed() && userPhotoSupportBtnIsDisplayed() && userPhotoLogoutBtnIsDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
