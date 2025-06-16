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
import java.util.List;

public class HomePage extends CommonMethods {
    @FindBy(xpath = "//div[@class='logo']//img")
    private WebElement finologyLogo;

    @FindBy(linkText = "Home")
    private WebElement homeBtn;

    @FindBy(linkText = "Products")
    private WebElement productsBtn;

    @FindBy(linkText = "Subscribe")
    private WebElement subscribeBtn;

    @FindBy(linkText = "Investor Journey")
    private WebElement investorJourneyBtn;

    @FindBy(linkText = "Products")
    private WebElement productBtn;

    @FindBy(linkText = "About")
    private WebElement aboutBtn;

    @FindBy(linkText = "Support")
    private WebElement supportBtn;

    @FindBy(linkText = "Login")
    private WebElement loginBtn;

    // After login
    @FindBy(linkText = "Dashboard")
    private WebElement dashboardBtn;

    @FindBy(xpath = "(//a[text()='View Plans'])[1]")
    private WebElement topViewPlansBtn;

    @FindBy(linkText = "Start for free")
    private WebElement startForFreeBtn;

    // Broker
    @FindBy(linkText = "Go to Select")
    private WebElement goToSelectBtn;

    @FindBy(xpath = "//img[@src='image/select.svg']")
    private WebElement brokerRightSideImg;

    // Quest
    @FindBy(linkText = "Start your Quest")
    private WebElement startYourQuestBtn;

    @FindBy(xpath = "//img[@src='image/quest.svg']")
    private WebElement startYourQuestLeftSideImg;

    // Ticker
    @FindBy(linkText = "Go to Ticker")
    private WebElement goToTickerBtn;

    @FindBy(xpath = "//img[@src='image/ticker.svg']")
    private WebElement goToTickerRightSideImg;

    // Recipe
    @FindBy(linkText = "Get your Recipe")
    private WebElement getYourRecipeBtn;

    @FindBy(xpath = "//img[@src='image/recipe.svg']")
    private WebElement getYourRecipeLeftSideImg;

    // Insider
    @FindBy(linkText = "Go to Insider")
    private WebElement goToInsiderBtn;

    @FindBy(xpath = "//img[@src='image/insider.svg']")
    private WebElement goToInsiderLeftSideImg;

    @FindBy(xpath = "(//a[text()='View Plans'])[2]")
    private WebElement middleViewPlansBtn;

    @FindBy(linkText = "Contact Us")
    private WebElement contactUsBtn;

    @FindBy(linkText = "Know More")
    private WebElement knowMoreBtn;

    @FindBy(xpath = "//p[@class='pl2 lh2']")
    private WebElement advisorDetails;

    @FindBy(xpath = "(//p[@class='fs1 c-white pt025'])[2]")
    private WebElement rating;

    @FindBy(linkText = "Got more questions?")
    private WebElement gotMoreQuestionsBtn;

    @FindBy(xpath = "(//a[text()='View Plans'])[3]")
    private WebElement footerViewPlansBtn;

    @FindBy(linkText = "Privacy Policy?")
    private WebElement privacyPolicyBtn;

    @FindBy(linkText = "Terms of use")
    private WebElement termsOfUseBtn;

    @FindBy(linkText = "Refunds Policy")
    private WebElement refundsPolicyBtn;


    WebDriver driver;
    WebDriverWait wait;
    static Logger log = LogManager.getLogger(HomePage.class);
    private final String url = "https://www.finology.in/";

    public HomePage(WebDriver driver) {
        this.driver = driver;
        AjaxElementLocatorFactory ajax = new AjaxElementLocatorFactory(driver, 10);
        PageFactory.initElements(ajax, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public boolean verifyOnHomePagePage() {
        try {
            return wait.until(ExpectedConditions.urlContains(url));
        } catch (Exception e) {
            logWarningInLogFileAndExtentReport(log, e, "Exception while verifying on Home Page");
            return false;
        }
    }

    // Return the faq question WebElement
    public WebElement getFAQQuestionWebElementOfQuestion(String question) {
        try {
            return wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[text()='" + question + "']")));
        } catch (Exception e) {
            logWarningInLogFileAndExtentReport(log, e, "Exception while getting the FAQ Question WebElement of a Question on Home Page");
            return null;
        }
    }

    // Return the answer in String format without space of a faq question
    public String getFAQAnswerOfQuestion(String question) {
        try {
            List<WebElement> answers = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//a[text()='" + question + "']/following-sibling::div/p[@class='faqAns']/following-sibling::p")));
            StringBuilder sb = new StringBuilder();

            for (WebElement ans : answers) {
                sb.append(ans.getText());
            }
            return sb.toString().replaceAll(" ", "");
        } catch (Exception e) {
            logWarningInLogFileAndExtentReport(log, e, "Exception while getting FAQ Answer of a Question on Home Page");
            return "";
        }
    }

    public boolean goToHomePage() {
        try {
            driver.get(url);
            return true;
        } catch (Exception e) {
            logWarningInLogFileAndExtentReport(log, e, "Exception while navigating to Home Page");
            return false;
        }
    }
}