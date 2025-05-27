package in.finology.stepDefns;

import in.finology.utils.CommonMethods;
import in.finology.utils.ObjectManager;
import in.finology.pages.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;

import static in.finology.utils.CustomAsserts.*;

public class Login_Steps extends CommonMethods {
//    WebDriver driver;
//    HomePage hp;
//    SignUpPage sup;
//    OTPVerificationPage otpvp;
//    LoginPage lp;
//    LoginAuthPage lap;
//
//    public Login_Steps() {
////        driver = DriverFactory.getDriver("Chrome");
//        driver = ObjectManager.getObject().getDriver();
//        hp = new HomePage(driver);
//        sup = new SignUpPage(driver);
//        otpvp = new OTPVerificationPage(driver);
//        lp = new LoginPage(driver);
//        lap = new LoginAuthPage(driver);
//    }

    @Then("User is on new login page")
    public void user_is_on_new_login_page() {
        assertTrue(ObjectManager.getObject().loginPage.goToLoginPage(), "User is unable to go to login page");
    }

    @Then("User should be on login page")
    public void user_should_be_on_login_page() {
        assertTrue(ObjectManager.getObject().loginPage.verifyOnLoginPage(), "User is not on login page");
    }

    @Then("User should be redirected to login page")
    public void user_should_be_redirected_to_login_page() {
        assertTrue(ObjectManager.getObject().loginPage.verifyOnLoginPage(), "User is not redirected to login page after clicking signup button");
    }

    @Then("Already account {string} should be shown")
    public void already_account_should_be_shown(String string) {
        assertTrue(compareString(ObjectManager.getObject().loginPage.getAlreadyAccountErrorMessage(), string), "Already account error message does not match");
    }


    @Given("User enter {string} in mobile number input box")
    public void user_enter_in_mobile_number_input_box(String number) {
        assertTrue(ObjectManager.getObject().loginPage.enterMobileNumber(number), "User is unable to enter mobile number for login");
    }

    @When("User clicks Continue button")
    public void user_clicks_continue_button() {
        assertTrue(ObjectManager.getObject().loginPage.clickContinueBtn(), "User is unable to click continue button on login page");
    }

    @Then("Invalid mobile or required error {string} should be shown")
    public void invalid_mobile_or_required_error_should_be_shown(String message) {
        assertTrue(compareString(ObjectManager.getObject().loginPage.getInvalidOrRequiredMobileNumberText(), message), "Invalid or Required error message does not match");
    }

    @Given("Generate new mobile number and User enter in mobile number input box")
    public void generate_new_mobile_number_and_user_enter_in_mobile_number_input_box() {
        assertTrue(ObjectManager.getObject().loginPage.enterMobileNumber(mobileNumberGenerateAndAppend()), "User is unable to generate and enter mobile number for login");
    }

    @When("User clicks Login with Email button")
    public void user_clicks_login_with_email_button() {
        assertTrue(ObjectManager.getObject().loginPage.clickLoginInWithEmailBtn(), "User is unable to click Login with Email button");
    }
}