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

public class AccountDashboardPage extends CommonMethods {

    @FindBy(xpath = "//img[contains(@src, '/finology-logo.svg')]")
    private WebElement finologyLogo;

    @FindBy(xpath = "//h3[normalize-space() = 'Dashboard']/following-sibling::p")
    private WebElement welcomeText;


    // Recipe
    @FindBy(partialLinkText = "Go to Recipe")
    private WebElement goToRecipeBtn;

    // Quest
    @FindBy(partialLinkText = "Go to Quest")
    private WebElement goToQuestBtn;

    // Broker
    @FindBy(partialLinkText = "Go to Select")
    private WebElement goToSelectBtn;

    // Insider
    @FindBy(partialLinkText = "Go to Insider")
    private WebElement goToInsiderBtn;

    // Reserve
    @FindBy(partialLinkText = "Go to Reserve")
    private WebElement goToReserveBtn;


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
    private static final Logger log = LogManager.getLogger(AccountDashboardPage.class);
    private final String url;

    public AccountDashboardPage(WebDriver driver) {
        this.driver = driver;
        AjaxElementLocatorFactory ajax = new AjaxElementLocatorFactory(driver, 15);
        PageFactory.initElements(ajax, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        url = "https://www.finology.in/account/dashboard";
    }

    public boolean goToAccountDashboardPage() {
        try {
            driver.get(url);
            return true;
        } catch (Exception e) {
            logWarningInLogFileAndExtentReport(log, e, "Exception while navigation to Account Dashboard Page");
            return false;
        }
    }

    public boolean verifyOnDashboardPage() {
        try {
            return wait.until(ExpectedConditions.urlToBe(url));
        } catch (Exception e) {
            logWarningInLogFileAndExtentReport(log, e, "Exception while verifying on Account Dashboard Page");
            return false;
        }
    }

    public boolean clickUserPhotoImgBtn() {
        try {
            userPhotoImgBtn.click();
            return true;
        } catch (Exception e) {
            logWarningInLogFileAndExtentReport(log, e, "Exception while clicking User Photo Image on Account Dashboard Page");
            return false;
        }
    }

    public boolean performLogoutFromAccountDashboardPageUsingUserPhotoImgBtn() {
        try {
            clickUserPhotoImgBtn();
            userPhotoLogoutBtn.click();
            wait.until(ExpectedConditions.urlToBe("https://www.finology.in/"));
            return true;
        } catch (Exception e) {
            logWarningInLogFileAndExtentReport(log, e, "Exception while performing Logout from Account Dashboard Page Using User Photo Image Button");
            return false;
        }
    }

    public boolean performLogoutFromAccountDashboardPageUsingSideBarLogoutBtn() {
        try {
            clickSideBarLogoutBtn();
            wait.until(ExpectedConditions.urlToBe("https://www.finology.in/"));
            return true;
        } catch (Exception e) {
            logWarningInLogFileAndExtentReport(log, e, "Exception while performing Logout from Account Dashboard Page Using Side Bar Logout Button");
            return false;
        }
    }

    public boolean finologyLogoIsDisplayed() {
        try {
            return finologyLogo.isDisplayed();
        } catch (Exception e) {
            logWarningInLogFileAndExtentReport(log, e, "Exception while verifying finology Logo is Displayed on Account Dashboard Page");
            return false;
        }
    }

    public boolean welcomeTextIsDisplayed() {
        try {
            return welcomeText.isDisplayed();
        } catch (Exception e) {
            logWarningInLogFileAndExtentReport(log, e, "Exception while while verifying welcome Text is Displayed on Account Dashboard Page");
            return false;
        }
    }

    public boolean goToRecipeBtnIsDisplayed() {
        try {
            return goToRecipeBtn.isDisplayed();
        } catch (Exception e) {
            logWarningInLogFileAndExtentReport(log, e, "Exception while while verifying go To Recipe Button is Displayed on Account Dashboard Page");
            return false;
        }
    }

    public boolean goToQuestBtnIsDisplayed() {
        try {
            return goToQuestBtn.isDisplayed();
        } catch (Exception e) {
            logWarningInLogFileAndExtentReport(log, e, "Exception while while verifying go To Quest Button is Displayed on Account Dashboard Page");
            return false;
        }
    }

    public boolean goToSelectBtnIsDisplayed() {
        try {
            return goToSelectBtn.isDisplayed();
        } catch (Exception e) {
            logWarningInLogFileAndExtentReport(log, e, "Exception while while verifying go To Select Button is Displayed on Account Dashboard Page");
            return false;
        }
    }

    public boolean goToInsiderBtnIsDisplayed() {
        try {
            return goToInsiderBtn.isDisplayed();
        } catch (Exception e) {
            logWarningInLogFileAndExtentReport(log, e, "Exception while while verifying go To Insider Button is Displayed on Account Dashboard Page");
            return false;
        }
    }

    public boolean goToReserveBtnIsDisplayed() {
        try {
            return goToReserveBtn.isDisplayed();
        } catch (Exception e) {
            logWarningInLogFileAndExtentReport(log, e, "Exception while while verifying go To Reserve Button is Displayed on Account Dashboard Page");
            return false;
        }
    }

    public boolean sideBarHomeBtnIsDisplayed() {
        try {
            return sideBarHomeBtn.isDisplayed();
        } catch (Exception e) {
            logWarningInLogFileAndExtentReport(log, e, "Exception while while verifying side Bar Home Button is Displayed on Account Dashboard Page");
            return false;
        }
    }

    public boolean sideBarSubscriptionBtnIsDisplayed() {
        try {
            return sideBarSubscriptionBtn.isDisplayed();
        } catch (Exception e) {
            logWarningInLogFileAndExtentReport(log, e, "Exception while while verifying side Bar Subscription Button is Displayed on Account Dashboard Page");
            return false;
        }
    }

    public boolean sideBarProfileBtnIsDisplayed() {
        try {
            return sideBarProfileBtn.isDisplayed();
        } catch (Exception e) {
            logWarningInLogFileAndExtentReport(log, e, "Exception while while verifying side Bar Profiles Button is Displayed on Account Dashboard Page");
            return false;
        }
    }

    public boolean sideBarPasswordBtnIsDisplayed() {
        try {
            return sideBarPasswordBtn.isDisplayed();
        } catch (Exception e) {
            logWarningInLogFileAndExtentReport(log, e, "Exception while while verifying side Bar Password Button is Displayed on Account Dashboard Page");
            return false;
        }
    }

    public boolean sideBarSupportBtnIsDisplayed() {
        try {
            return sideBaSupportBtn.isDisplayed();
        } catch (Exception e) {
            logWarningInLogFileAndExtentReport(log, e, "Exception while while verifying side Bar Support Button is Displayed on Account Dashboard Page");
            return false;
        }
    }

    public boolean sideBarLogoutBtnIsDisplayed() {
        try {
            return sideBarLogoutBtn.isDisplayed();
        } catch (Exception e) {
            logWarningInLogFileAndExtentReport(log, e, "Exception while while verifying side Bar Logout Button is Displayed on Account Dashboard Page");
            return false;
        }
    }

    public boolean userPhotoImgBtnIsDisplayed() {
        try {
            return userPhotoImgBtn.isDisplayed();
        } catch (Exception e) {
            logWarningInLogFileAndExtentReport(log, e, "Exception while while verifying user Photo Button is Displayed on Account Dashboard Page");
            return false;
        }
    }


    public boolean clickFinologyLogo() {
        try {
            finologyLogo.click();
            return true;
        } catch (Exception e) {
            logWarningInLogFileAndExtentReport(log, e, "Exception while clicking Finology Logo on Account Dashboard Page");
            return false;
        }
    }

    public boolean clickGoToRecipeBtn() {
        try {
            goToRecipeBtn.click();
            return true;
        } catch (Exception e) {
            logWarningInLogFileAndExtentReport(log, e, "Exception while clicking Go To Recipe Button on Account Dashboard Page");
            return false;
        }
    }

    public boolean clickGoToQuestBtn() {
        try {
            goToQuestBtn.click();
            return true;
        } catch (Exception e) {
            logWarningInLogFileAndExtentReport(log, e, "Exception while clicking Go To Quest Button on Account Dashboard Page");
            return false;
        }
    }

    public boolean clickGoToSelectBtn() {
        try {
            goToSelectBtn.click();
            return true;
        } catch (Exception e) {
            logWarningInLogFileAndExtentReport(log, e, "Exception while clicking Go To Select Button on Account Dashboard Page");
            return false;
        }
    }

    public boolean clickGoToInsiderBtn() {
        try {
            goToInsiderBtn.click();
            return true;
        } catch (Exception e) {
            logWarningInLogFileAndExtentReport(log, e, "Exception while clicking Go To Insider Button on Account Dashboard Page");
            return false;
        }
    }

    public boolean clickGoToReserveBtn() {
        try {
            goToReserveBtn.click();
            return true;
        } catch (Exception e) {
            logWarningInLogFileAndExtentReport(log, e, "Exception while clicking Go To Reserve Button on Account Dashboard Page");
            return false;
        }
    }

    public boolean clickSideBarHomeBtn() {
        try {
            sideBarHomeBtn.click();
            return true;
        } catch (Exception e) {
            logWarningInLogFileAndExtentReport(log, e, "Exception while clicking Side Bar Home Button on Account Dashboard Page");
            return false;
        }
    }

    public boolean clickSideBarSubscriptionBtn() {
        try {
            sideBarSubscriptionBtn.click();
            return true;
        } catch (Exception e) {
            logWarningInLogFileAndExtentReport(log, e, "Exception while clicking Side Bar Subscription Button on Account Dashboard Page");
            return false;
        }
    }

    public boolean clickSideBarProfileBtn() {
        try {
            sideBarProfileBtn.click();
            return true;
        } catch (Exception e) {
            logWarningInLogFileAndExtentReport(log, e, "Exception while clicking Side Bar Profile Button on Account Dashboard Page");
            return false;
        }
    }

    public boolean clickSideBarPasswordBtn() {
        try {
            sideBarPasswordBtn.click();
            return true;
        } catch (Exception e) {
            logWarningInLogFileAndExtentReport(log, e, "Exception while clicking SideBar Password Button on Account Dashboard Page");
            return false;
        }
    }

    public boolean clickSideBarSupportBtn() {
        try {
            sideBaSupportBtn.click();
            return true;
        } catch (Exception e) {
            logWarningInLogFileAndExtentReport(log, e, "Exception while clicking Side Bar Support Button on Account Dashboard Page");
            return false;
        }
    }

    public boolean clickSideBarLogoutBtn() {
        try {
            sideBarLogoutBtn.click();
            return true;
        } catch (Exception e) {
            logWarningInLogFileAndExtentReport(log, e, "Exception while clicking Side Bar Logout Button on Account Dashboard Page");
            return false;
        }
    }

    // After clicking userPhotoImgBtn

    public boolean userPhotoDashboardBtnIsDisplayed() {
        try {
            return userPhotoDashboardBtn.isDisplayed();
        } catch (Exception e) {
            logWarningInLogFileAndExtentReport(log, e, "Exception while verifying user Photo Dashboard Button is Displayed on Account Dashboard Page");
            return false;
        }
    }

    public boolean userPhotoProfileBtnIsDisplayed() {
        try {
            return userPhotoProfileBtn.isDisplayed();
        } catch (Exception e) {
            logWarningInLogFileAndExtentReport(log, e, "Exception while verifying user Photo Profile Button is Displayed on Account Dashboard Page");
            return false;
        }
    }

    public boolean userPhotoSupportBtnIsDisplayed() {
        try {
            return userPhotoSupportBtn.isDisplayed();
        } catch (Exception e) {
            logWarningInLogFileAndExtentReport(log, e, "Exception while verifying user Photo Support Button is Displayed on Account Dashboard Page");
            return false;
        }
    }

    public boolean userPhotoLogoutBtnIsDisplayed() {
        try {
            return userPhotoLogoutBtn.isDisplayed();
        } catch (Exception e) {
            logWarningInLogFileAndExtentReport(log, e, "Exception while verifying user Photo Logout Button isDisplayed on Account Dashboard Page");
            return false;
        }
    }

    public boolean clickUserPhotoDashboardBtn() {
        try {
            userPhotoDashboardBtn.click();
            return true;
        } catch (Exception e) {
            logWarningInLogFileAndExtentReport(log, e, "Exception while clicking User Photo Dashboard Button on Account Dashboard Page");
            return false;
        }
    }

    public boolean clickUserPhotoProfileBtn() {
        try {
            userPhotoProfileBtn.click();
            return true;
        } catch (Exception e) {
            logWarningInLogFileAndExtentReport(log, e, "Exception while clicking User Photo Profile Button on Account Dashboard Page");
            return false;
        }
    }

    public boolean clickUserPhotoSupportBtn() {
        try {
            userPhotoSupportBtn.click();
            return true;
        } catch (Exception e) {
            logWarningInLogFileAndExtentReport(log, e, "Exception while clicking User Photo Support Button on Account Dashboard Page");
            return false;
        }
    }

    public boolean clickUserPhotoLogoutBtn() {
        try {
            userPhotoLogoutBtn.click();
            return true;
        } catch (Exception e) {
            logWarningInLogFileAndExtentReport(log, e, "Exception while clicking User Photo Logout Button on Account Dashboard Page");
            return false;
        }
    }


    public String getWelcomeText() {
        try {
            return welcomeText.getText();
        } catch (Exception e) {
            logWarningInLogFileAndExtentReport(log, e, "Exception while getting Welcome Text on Account Dashboard Page");
            return "";
        }
    }

    public boolean verifyAllElementsAreDisplayed() {
        try {
            return finologyLogoIsDisplayed() && welcomeTextIsDisplayed() && userPhotoImgBtnIsDisplayed() && goToRecipeBtnIsDisplayed() && goToQuestBtnIsDisplayed() && goToSelectBtnIsDisplayed() && goToInsiderBtnIsDisplayed() && goToReserveBtnIsDisplayed() && sideBarHomeBtnIsDisplayed() && sideBarSubscriptionBtnIsDisplayed() && sideBarProfileBtnIsDisplayed() && sideBarPasswordBtnIsDisplayed() && sideBarSupportBtnIsDisplayed() && sideBarLogoutBtnIsDisplayed();
        } catch (Exception e) {
            logWarningInLogFileAndExtentReport(log, e, "Exception while verifying All Elements are Displayed on Account Dashboard Page");
            return false;
        }
    }

    public boolean verifyAllUserPhotoElementsAreDisplayed() {
        try {
            return userPhotoDashboardBtnIsDisplayed() && userPhotoProfileBtnIsDisplayed() && userPhotoSupportBtnIsDisplayed() && userPhotoLogoutBtnIsDisplayed();
        } catch (Exception e) {
            logWarningInLogFileAndExtentReport(log, e, "Exception while verifying All User Photo Elements are Displayed on Account Dashboard Page");
            return false;
        }
    }
}