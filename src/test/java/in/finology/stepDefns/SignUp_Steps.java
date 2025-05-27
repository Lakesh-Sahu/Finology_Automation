package in.finology.stepDefns;

import in.finology.utils.CommonMethods;
import in.finology.utils.ObjectManager;
import in.finology.pages.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;

import static in.finology.utils.CustomAsserts.*;

public class SignUp_Steps extends CommonMethods {
//    WebDriver driver;
//    HomePage hp;
//    SignUpPage sup;
//    OTPVerificationPage otpvp;
//    LoginPage lp;
//    LoginAuthPage lap;
//
//    public SignUp_Steps() {
////        driver = DriverFactory.getDriver("Chrome");
//        driver = ObjectManager.getObject().getDriver();
//        hp = new HomePage(driver);
//        sup = new SignUpPage(driver);
//        otpvp = new OTPVerificationPage(driver);
//        lp = new LoginPage(driver);
//        lap = new LoginAuthPage(driver);
//    }

    @Given("User is on new sign up page")
    public void user_is_on_new_sign_up_page() {
        assertTrue(ObjectManager.getObject().signUpPage.goToSignUpPage(), "User is not on sign up page");
    }

    @Then("User should be redirected to sign up page")
    public void user_should_be_redirected_to_sign_up_page() {
        assertTrue(ObjectManager.getObject().signUpPage.verifyOnSignUpPage(), "User is not redirected to sign up page");
    }

    @Given("User enters {string}, {string} and {string}")
    public void user_enters_and(String name, String emailId, String mobileNumber) {
        assertTrue(ObjectManager.getObject().signUpPage.enterSignUpDetails(name, emailId, mobileNumber), "User is unable to enter sign up details");
    }

    @When("User clicks sign up button")
    public void user_clicks_sign_up_button() {
        assertTrue(ObjectManager.getObject().signUpPage.clickSignUpBtn(), "User is unable to click signup button in signup page");
    }

    @Then("Invalid name Error {string} should be shown below name input box")
    public void invalid_name_error_should_be_shown_below_name_input_box(String message) {
        assertTrue(compareString(ObjectManager.getObject().signUpPage.getInvalidNameErrorMessage(), message), "Invalid name error message does not match");
    }

    @Then("Empty name Error {string} should be shown below name input box")
    public void empty_name_error_should_be_shown_below_name_input_box(String message) {
        assertTrue(compareString(ObjectManager.getObject().signUpPage.getEmptyNameErrorMessage(), message), "Empty name error message does not match");
    }

    @Then("Error {string} or {string} should be shown below email input box")
    public void error_should_be_shown_below_email_input_box(String message1, String message2) {
        assertTrue(compareString(ObjectManager.getObject().signUpPage.getInvalidEmailErrorMessage(), message1) || compareString(ObjectManager.getObject().signUpPage.getInvalidEmailErrorMessage(), message2), "Invalid Email error message does not match");
    }

    // Unused
    @Then("User should be redirected to OTP verification page or User should be redirected to login page or Already account {string} should be shown or User should see incognito mode error message {string}")
    public void user_should_be_redirected_to_otp_verification_page_or_user_should_be_redirected_to_login_page_or_already_account_should_be_shown_or_user_should_see_incognito_mode_error_message(String message, String message2) {
        assertTrue(ObjectManager.getObject().otpVerificationPage.verifyOnOTPVerificationPage() || ObjectManager.getObject().loginPage.verifyOnLoginPage() || compareString(ObjectManager.getObject().loginPage.getAlreadyAccountErrorMessage(), message) || compareString(ObjectManager.getObject().signUpPage.getIncognitoModeErrorMessage(), message2), "User should be redirected to login page or Already account error message should be shown or User should see incognito mode error message Failed");
    }

    //  Unused (used only when browser is launched without any profile
    @Then("User should see incognito mode error message {string} or redirected to OTP verification page")
    public void user_should_see_incognito_mode_error_message_or_redirected_to_OTP_verification_page(String message) {
        assertTrue(compareString(ObjectManager.getObject().signUpPage.getIncognitoModeErrorMessage(), message) || ObjectManager.getObject().otpVerificationPage.verifyOnOTPVerificationPage(), "Incognito mode error message does not appear or valid user is not redirected to OTP verification page");
    }
}