package testCases;

import in.finology.utils.CommonMethods;
import in.finology.utils.driverFactory.DriverFactory;
import in.finology.pages.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Scanner;

//public class TestCase01 extends CommonMethods {

//    @FindBy(linkText = "Go to Insider")
//    private static WebElement goToInsiderBtn;
//
//    @FindBy(linkText = "Got more questions?")
//    private static WebElement gotMoreQuestionsBtn;
//
//    @FindBy(linkText = "Privacy Policy")
//    private static WebElement privacyPolicyBtn;
//
//    @FindBy(xpath = "(//div[@class='col33'])[1]")
//    private static WebElement card;
//
//    @FindBy(xpath = "//p[contains(text(), 'Demat Account')]/..")
//    private static WebElement dematCard;
//
//    @FindBy(xpath = "/html/body/div[5]")
//    private static WebElement bigCard;
//
//    @FindBy(partialLinkText = "Sign up with Google")
//    private WebElement signUpWithGoogleBtn;
//
//    @FindBy(linkText = "Login")
//    private WebElement loginBtn;
//
//    @FindBy(linkText = "Sign Up")
//    private WebElement signUpBtn;
//
//    @FindBy(partialLinkText = "Sign up with Google")
//    private WebElement signInWithGoogleBtnTest;

//    WebDriver driver;
//    WebDriverWait wait;
//    HomePage hp;
//    SignUpPage sup;
//    OTPVerificationPage otpvp;
//    LoginPage lp;
//    LoginAuthPage lap;
//    AccountDashboardPage adp;
//
//
//    String registrationNumber;
//    String chassisNumber;
//    String engineNumber;
//    String mobileNumber;
//
//    public TestCase01() {
//        Scanner sc = new Scanner(System.in);
//
//        System.out.println("Enter registration number : ");
//        registrationNumber = sc.nextLine();
//
//        System.out.println("Enter Chassis Number : ");
//        chassisNumber = sc.nextLine();
//
//        System.out.println("Enter Engine Number : ");
//        engineNumber = sc.nextLine();
//
//        System.out.println("Enter Mobile Number : ");
//        mobileNumber = sc.nextLine();
//
//
//        this.driver = (new DriverFactory()).getDriver("Chrome");
//        AjaxElementLocatorFactory ajax = new AjaxElementLocatorFactory(driver, 10);
//        PageFactory.initElements(ajax, this);
//        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        hp = new HomePage(driver);
//        sup = new SignUpPage(driver);
//        otpvp = new OTPVerificationPage(driver);
//        lp = new LoginPage(driver);
//        lap = new LoginAuthPage(driver);
//        adp = new AccountDashboardPage(driver);
//    }

//    public void runMe() throws InterruptedException {
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        driver.get("https://www.finology.in/");


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


//        click(driver, loginBtn);
//        click(driver, signUpBtn);
//        click(driver, signInWithGoogleBtnTest);


//        System.out.println(System.currentTimeMillis());
//        System.out.println(System.getProperty("user.dir"));
//
//        String number = mobileNumberGenerator();
//        System.out.println(number);
//        System.out.println(number.length());
//
//        System.out.println(mobileNumberGenerateAndAppend());

//        System.out.println(driver.getCurrentUrl());
//        lp.goToLoginPage();
//        System.out.println(driver.getCurrentUrl());
//        lap.goToLoginAuthPage();
//        System.out.println(driver.getCurrentUrl());
//        lap.clickHomeBtn();
//        wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Know More"))).click();
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Know More"))).click();
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[text()='Do-it-yourself']")));
//        System.out.println("done");
//        lp.goToLoginPage();
//        System.out.println(lp.verifyOnLoginPage());
//        System.out.println("done");
//        driver.get("https://www.finology.in/account/dashboard");
//        Thread.sleep(15000);
//        System.out.println(driver.getCurrentUrl());

//        driver.get("https://www.finology.in/signup");
//        System.out.println(driver.getCurrentUrl());

//        adp.goToAccountDashboardPage();
//        System.out.println(driver.getCurrentUrl());
//        Thread.sleep(30000);
//        long start = System.currentTimeMillis();
//        adp.logout();
//        long end = System.currentTimeMillis();
//        System.out.println((end - start));

//        adp.goToAccountDashboardPage();
//        System.out.println(adp.sideBarHomeBtnIsDisplayed());
//        adp.clickSideBarHomeBtn();

//        String e = "ok sbi India";
//
//        for(String el : e.split(" ")) {
//
//            System.out.print(el + ":");
//        }
//
//        HashMap<Integer, List<List<Integer>>> myMap = new HashMap<>();
//        System.out.println(myMap.get(8));
//
//        String f1 = "llu";
//        String f2 = "LLa";
//
//        System.out.println(f1.compareTo(f2));



//    -----------------XXX-------------------------
//        driver.get("https://order.realmazon.com/");
//
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@value='CHHATTISGARH']"))).click();
//
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//button[normalize-space()='Close'])[3]"))).click();
//
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='Order HSRP']"))).click();
//
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Registration Number']"))).clear();
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Registration Number']"))).sendKeys(registrationNumber);
//
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Chassis Number']"))).clear();
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Chassis Number']"))).sendKeys(chassisNumber);
//
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Engine Number']"))).clear();
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Engine Number']"))).sendKeys(registrationNumber);
//
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Mobile Number']"))).clear();
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Mobile Number']"))).sendKeys(registrationNumber);
//
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@Value='Validate']"))).click();
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Mobile Number']"))).sendKeys(registrationNumber);
//    -----------------XXX-------------------------

//    }
//
//    public static void main(String[] args) throws InterruptedException {
//        (new TestCase01()).runMe();
//    }
//}