import com.CommonMethods;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class TestCase01 extends CommonMethods {

    @FindBy(linkText = "Go to Insider")
    private static WebElement goToInsiderBtn;

    @FindBy(linkText = "Got more questions?")
    private static WebElement gotMoreQuestionsBtn;

    @FindBy(linkText = "Privacy Policy")
    private static WebElement privacyPolicyBtn;

    @FindBy(xpath = "(//div[@class='col33'])[1]")
    private static WebElement card;

    @FindBy(xpath = "//p[contains(text(), 'Demat Account')]/..")
    private static WebElement dematCard;

    @FindBy(xpath = "/html/body/div[5]")
    private static WebElement bigCard;

    @FindBy(partialLinkText = "Sign up with Google")
    private WebElement signUpWithGoogleBtn;

    @FindBy(linkText = "Login")
    private WebElement loginBtn;

    @FindBy(linkText = "Sign Up")
    private WebElement signUpBtn;

    @FindBy(partialLinkText = "Sign up with Google")
    private WebElement signInWithGoogleBtnTest;

    WebDriver driver;
    WebDriverWait wait;

    public TestCase01() {
        this.driver = DriverFactory.getDriver("Chrome");
        AjaxElementLocatorFactory ajax = new AjaxElementLocatorFactory(driver, 10);
        PageFactory.initElements(ajax, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void runMe() throws InterruptedException {
        driver.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://www.finology.in/");

//        WebElement img = driver.findElement(By.xpath("//img[@src='image/select.svg']"));
//        JavascriptExecutor js = (JavascriptExecutor) driver;
//        js.executeScript("arguments[0].scrollIntoView(true);", img);
//
//        WebElement p = driver.findElement(By.xpath("//p[@class='pl2 lh2']"));
//        System.out.println(p.getText());

//        WebElement rating = driver.findElement(By.xpath("(//p[@class='fs1 c-white pt025'])[2]"));
//        System.out.println(rating.getText());
//        Thread.sleep(2000);
//        WebElement question = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[11]/div[2]/div[2]/div/p[2]")));
//        System.out.println("qu" + question.getText());

//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("2. What are the steps involved in financial planning?"))).click();
//        List<WebElement> ls = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("(//p[@class='faqAns'])[2]/following-sibling::p")));
//        StringBuilder sb = new StringBuilder();
//
//        for (WebElement li : ls) {
//            sb.append(li.getText() + " ");
//        }
//
//        System.out.println(sb);

//        System.out.println(driver.getCurrentUrl());
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("2. What are the steps involved in financial planning?"))).click();
//        List<WebElement> ls = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("(//p[@class='faqAns'])[2]/following-sibling::p")));
//        StringBuilder sb = new StringBuilder();

//        System.out.println("JavascriptExecutor");
//        JavascriptExecutor jse = (JavascriptExecutor) driver;
//        jse.executeScript("arguments[0].scrollIntoView();", goToInsiderBtn);
//        Thread.sleep(5000);
//        jse.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'nearest', inline: 'start'});", goToInsiderBtn);
//        Thread.sleep(5000);
//        jse.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'start', inline: 'end'});", goToInsiderBtn);
//        Thread.sleep(5000);
//        jse.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center', inline: 'nearest'});", goToInsiderBtn);
//        Thread.sleep(5000);
//        jse.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'end', inline: 'center'});", goToInsiderBtn);


        click(driver, loginBtn);
        click(driver, signUpBtn);
        click(driver, signInWithGoogleBtnTest);























        //        driver.quit();
    }

    public static void main(String[] args) throws InterruptedException {
        (new TestCase01()).runMe();
    }
}
