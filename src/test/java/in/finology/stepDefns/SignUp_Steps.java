package in.finology.stepDefns;

import in.finology.utils.CommonMethods;
import in.finology.utils.ObjectManager;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static in.finology.utils.CustomAsserts.*;

public class SignUp_Steps extends CommonMethods {

    @Given("User is on new sign up page")
    public void user_is_on_new_sign_up_page() {
        assertTrue(ObjectManager.getObject().signUpPage.goToSignUpPage(), "User is on Sign Up Page");
    }

    @Then("User should be redirected to sign up page")
    public void user_should_be_redirected_to_sign_up_page() {
        assertTrue(ObjectManager.getObject().signUpPage.verifyOnSignUpPage(), "User is redirected to Sign Up Page");
    }

    @Given("User enters {string}, {string} and {string}")
    public void user_enters_and(String name, String emailId, String mobileNumber) {
        setExamplesKeyValueInHashMap("name", name);
        setExamplesKeyValueInHashMap("email id", emailId);
        setExamplesKeyValueInHashMap("mobile number", mobileNumber);
        assertTrue(ObjectManager.getObject().signUpPage.enterSignUpDetails(name, emailId, mobileNumber), "User is able to enter name, email id, and mobile number on Sign Up Page");
    }

    @Given("User enters new name, email id and mobile number")
    public void user_enters_new_name_email_id_and_mobile_number() {
        String newName = nameGenerator();
        String newEmail = emailGenerator();
        String newMobileNumber = mobileNumberGenerator();
        setExamplesKeyValueInHashMap("name", newName);
        setExamplesKeyValueInHashMap("email id", newEmail);
        setExamplesKeyValueInHashMap("mobile number", newMobileNumber);
        assertTrue(ObjectManager.getObject().signUpPage.enterSignUpDetails(newName, newEmail, newMobileNumber), "User is able to enter new name, email id, and mobile number on Sign Up Page");
    }

    @When("User clicks sign up button")
    public void user_clicks_sign_up_button() {
        assertTrue(ObjectManager.getObject().signUpPage.clickSignUpBtn(), "User is able to click Signup button on Signup Page");
    }

    @Then("Invalid name Error {string} should be shown below name input box")
    public void invalid_name_error_should_be_shown_below_name_input_box(String message) {
        setExamplesKeyValueInHashMap("message", message);
        assertTrue(compareTwoStringsRemoveSpaceIgnoreCase(ObjectManager.getObject().signUpPage.getInvalidNameErrorMessage(), message), "Invalid name error message below name input box matches with \"" + message + "\"");
    }

    @Then("Empty name Error {string} should be shown below name input box")
    public void empty_name_error_should_be_shown_below_name_input_box(String message) {
        setExamplesKeyValueInHashMap("message", message);
        assertTrue(compareTwoStringsRemoveSpaceIgnoreCase(ObjectManager.getObject().signUpPage.getEmptyNameErrorMessage(), message), "Empty name error message below name input box matches with \"" + message + "\"");
    }

    @Then("Error {string} or {string} should be shown below email input box")
    public void error_should_be_shown_below_email_input_box(String message1, String message2) {
        setExamplesKeyValueInHashMap("message1", message1);
        setExamplesKeyValueInHashMap("message2", message2);
        assertTrue(compareTwoStringsRemoveSpaceIgnoreCase(ObjectManager.getObject().signUpPage.getInvalidEmailErrorMessage(), message1) || compareTwoStringsRemoveSpaceIgnoreCase(ObjectManager.getObject().signUpPage.getInvalidEmailErrorMessage(), message2), "Invalid Email error message below email input box matches with \"" + message1 + "\" or \"" + message2 + "\"");
    }

    // Unused
    @Then("User should be redirected to OTP verification page or User should be redirected to login page or Already account {string} should be shown or User should see incognito mode error message {string}")
    public void user_should_be_redirected_to_otp_verification_page_or_user_should_be_redirected_to_login_page_or_already_account_should_be_shown_or_user_should_see_incognito_mode_error_message(String message1, String message2) {
        setExamplesKeyValueInHashMap("message1", message1);
        setExamplesKeyValueInHashMap("message2", message2);
        assertTrue(ObjectManager.getObject().otpVerificationPage.verifyOnOTPVerificationPage() || ObjectManager.getObject().loginPage.verifyOnLoginPage() || compareTwoStringsRemoveSpaceIgnoreCase(ObjectManager.getObject().loginPage.getAlreadyAccountErrorMessage(), message1) || compareTwoStringsRemoveSpaceIgnoreCase(ObjectManager.getObject().signUpPage.getIncognitoModeErrorMessage(), message2), "User is redirected to OTP Verification Page or User is redirected to Login Page or Already Account error message matches with \"" + message1 + "\" or User incognito mode error message matches with \"" + message2 + "\"");
    }

    //  Unused (used only when browser is launched without any profile
    @Then("User should see incognito mode error message {string} or redirected to OTP verification page")
    public void user_should_see_incognito_mode_error_message_or_redirected_to_OTP_verification_page(String message) {
        setExamplesKeyValueInHashMap("message", message);
        assertTrue(compareTwoStringsRemoveSpaceIgnoreCase(ObjectManager.getObject().signUpPage.getIncognitoModeErrorMessage(), message) || ObjectManager.getObject().otpVerificationPage.verifyOnOTPVerificationPage(), "Valid user is redirected to OTP Verification Page or Incognito mode error message matches with \"" + message + "\"");
    }
}