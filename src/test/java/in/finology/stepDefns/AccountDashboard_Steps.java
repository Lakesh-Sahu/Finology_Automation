package in.finology.stepDefns;

import in.finology.utils.CommonMethods;
import in.finology.utils.ObjectManager;
import in.finology.pages.*;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class AccountDashboard_Steps extends CommonMethods {

//    WebDriver driver;
//    HomePage hp;
//    SignUpPage sup;
//    OTPVerificationPage otpvp;
//    LoginPage lp;
//    LoginAuthPage lap;
//    AccountDashboardPage adp;
//
//    public AccountDashboard_Steps() {
////        driver = DriverFactory.getDriver("Chrome");
//        driver = ObjectManager.getObject().getDriver();
//        hp = new HomePage(driver);
//        sup = new SignUpPage(driver);
//        otpvp = new OTPVerificationPage(driver);
//        lp = new LoginPage(driver);
//        lap = new LoginAuthPage(driver);
//        adp = new AccountDashboardPage(driver);
//    }

    @Before("@account_dashboard")
    public void performingLogin() {
        try {
            ObjectManager.getObject().loginAuthPage.performLoginUsingMobileNumberOrEmail("testuserac121@gmail.com", "finTestUserAcology");
        } catch (Exception ignored) {
        }
    }

    @Given("User is logged in and on account dashboard page")
    public void userIsLoggedInAndOnAccountDashboardPage() {
        Assert.assertTrue( ObjectManager.getObject().accountDashboardPage.verifyOnDashboardPage() && ObjectManager.getObject().accountDashboardPage.userPhotoDashboardBtnIsDisplayed(), "User is not logged in");
    }

    @Then("User should be redirected to account dashboard page")
    public void user_should_be_redirected_to_account_dashboard_page() {
        Assert.assertTrue(ObjectManager.getObject().accountDashboardPage.verifyOnDashboardPage(), "User is not on Account Dashboard page");
    }

    @And("User should perform logout from account dashboard page")
    public void user_should_perform_logout_from_account_dashboard_page() {
        Assert.assertTrue(ObjectManager.getObject().accountDashboardPage.performLogoutFromAccountDashboardPageUsingSideBarLogoutBtn(), "User is unable to logout from the side bar logout button");
    }


    @Given("User should be on account dashboard page")
    public void userShouldBeOnAccountDashboardPage() {
        Assert.assertTrue(ObjectManager.getObject().accountDashboardPage.verifyOnDashboardPage(), "User is not on account dashboard page");
    }

    @Then("All dashboard elements should be displayed")
    public void allDashboardElementsShouldBeDisplayed() {
        Assert.assertTrue(ObjectManager.getObject().accountDashboardPage.verifyAllElementsAreDisplayed(), "All elements in account dashboard page are not visible");
    }

    @When("User clicks user profile")
    public void userClicksUserProfile() {
        Assert.assertTrue(ObjectManager.getObject().accountDashboardPage.clickUserPhotoImgBtn(), "User is unable to logout from account dashboard page");
    }

    @Then("All user photo elements should be displayed")
    public void allUserPhotoElementsShouldBeDisplayed() {
        Assert.assertTrue(ObjectManager.getObject().accountDashboardPage.verifyAllUserPhotoElementsAreDisplayed(), "All elements of user photo elements in account dashboard page are not visible");
    }

    @Then("Welcome {string} should be displayed")
    public void welcomeShouldBeDisplayed(String message) {
        Assert.assertTrue(compareString(ObjectManager.getObject().accountDashboardPage.getWelcomeText(), message), "Welcome message of account dashboard page does not match");
    }
}