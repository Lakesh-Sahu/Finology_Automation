package stepDefns;

import com.CommonMethods;
import com.pages.*;
import driverFactory.DriverFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class Login_Steps extends CommonMethods {
    WebDriver driver;
    HomePage hp;
    SignUpPage sup;
    OTPVerificationPage otpvp;
    LoginPage lp;
    LoginAuthPage lap;

    public Login_Steps() {
        driver = DriverFactory.getDriver("Chrome");
        hp = new HomePage(driver);
        sup = new SignUpPage(driver);
        otpvp = new OTPVerificationPage(driver);
        lp = new LoginPage(driver);
        lap = new LoginAuthPage(driver);
    }

    @Then("User is on new login page")
    public void user_is_on_new_login_page() {
        Assert.assertTrue(lp.goToLoginPage(), "User is unable to go to login page");
    }

    @Then("User should be on login page")
    public void user_should_be_on_login_page() {
        Assert.assertTrue(lp.verifyOnLoginPage(), "User is not on login page");
    }

    @Then("User should be redirected to login page")
    public void user_should_be_redirected_to_login_page() {
        Assert.assertTrue(lp.verifyOnLoginPage(), "User is not redirected to login page after clicking signup button");
    }

    @Then("Already account {string} should be shown")
    public void already_account_should_be_shown(String string) {
        Assert.assertTrue(compareString(lp.getAlreadyAccountErrorMessage(), string), "Already account error message does not match");
    }


    @Given("User enter {string} in mobile number input box")
    public void user_enter_in_mobile_number_input_box(String number) {
        Assert.assertTrue(lp.enterMobileNumber(number), "User is unable to enter mobile number for login");
    }

    @When("User clicks Continue button")
    public void user_clicks_continue_button() {
        Assert.assertTrue(lp.clickContinueBtn(), "User is unable to click continue button on login page");
    }

    @Then("Invalid mobile or required error {string} should be shown")
    public void invalid_mobile_or_required_error_should_be_shown(String message) {
        Assert.assertTrue(compareString(lp.getInvalidOrRequiredMobileNumberText(), message), "Invalid or Required error message does not match");
    }

    @Given("Generate new mobile number and User enter in mobile number input box")
    public void generate_new_mobile_number_and_user_enter_in_mobile_number_input_box() {
        Assert.assertTrue(lp.enterMobileNumber(mobileNumberGenerateAndAppend()), "User is unable to generate and enter mobile number for login");
    }

    @When("User clicks Login with Email button")
    public void user_clicks_login_with_email_button() {
        Assert.assertTrue(lp.clickLoginInWithEmailBtn(), "User is unable to click Login with Email button");
    }
}