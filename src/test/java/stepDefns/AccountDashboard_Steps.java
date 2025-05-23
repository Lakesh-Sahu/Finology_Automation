package stepDefns;

import com.CommonMethods;
import com.pages.*;
import driverFactory.DriverFactory;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class AccountDashboard_Steps extends CommonMethods {

    WebDriver driver;
    HomePage hp;
    SignUpPage sup;
    OTPVerificationPage otpvp;
    LoginPage lp;
    LoginAuthPage lap;
    AccountDashboardPage adp;

    public AccountDashboard_Steps() {
        driver = DriverFactory.getDriver("Chrome");
        hp = new HomePage(driver);
        sup = new SignUpPage(driver);
        otpvp = new OTPVerificationPage(driver);
        lp = new LoginPage(driver);
        lap = new LoginAuthPage(driver);
        adp = new AccountDashboardPage(driver);
    }

    @Before("@account_dashboard")
    public void performingLogin() {
        try {
            lap.performLoginUsingMobileNumberOrEmail("testuserac121@gmail.com", "finTestUserAcology");
        } catch (Exception e) {
            return;
        }
    }

    @Given("User is logged in and on account dashboard page")
    public void userIsLoggedInAndOnAccountDashboardPage() {
        Assert.assertTrue( adp.verifyOnDashboardPage() && adp.userPhotoDashboardBtnIsDisplayed(), "User is not logged in");

    }

    @Then("User should be redirected to account dashboard page")
    public void user_should_be_redirected_to_account_dashboard_page() {
        Assert.assertTrue(adp.verifyOnDashboardPage(), "User is not on Account Dashboard page");
    }

    @And("User should perform logout from account dashboard page")
    public void user_should_perform_logout_from_account_dashboard_page() {
        Assert.assertTrue(adp.performLogoutFromAccountDashboardPageUsingSideBarLogoutBtn(), "User is unable to logout from the side bar logout button");
    }


    @Given("User should be on account dashboard page")
    public void userShouldBeOnAccountDashboardPage() {
        Assert.assertTrue(adp.verifyOnDashboardPage(), "User is not on account dashboard page");
    }

    @Then("All dashboard elements should be displayed")
    public void allDashboardElementsShouldBeDisplayed() {
        Assert.assertTrue(adp.verifyAllElementsAreDisplayed(), "All elements in account dashboard page are not visible");
    }

    @When("User clicks user profile")
    public void userClicksUserProfile() {
        Assert.assertTrue(adp.clickUserPhotoImgBtn(), "User is unable to logout from account dashboard page");
    }

    @Then("All user photo elements should be displayed")
    public void allUserPhotoElementsShouldBeDisplayed() {
        Assert.assertTrue(adp.verifyAllUserPhotoElementsAreDisplayed(), "All elements of user photo elements in account dashboard page are not visible");
    }

    @Then("Welcome {string} should be displayed")
    public void welcomeShouldBeDisplayed(String message) {
        Assert.assertTrue(compareString(adp.getWelcomeText(), message), "Welcome message of account dashboard page does not match");
    }
}