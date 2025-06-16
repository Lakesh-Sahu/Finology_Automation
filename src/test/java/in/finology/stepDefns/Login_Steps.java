package in.finology.stepDefns;

import in.finology.utils.CommonMethods;
import in.finology.utils.ObjectManager;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static in.finology.utils.CustomAsserts.*;

public class Login_Steps extends CommonMethods {

    @Then("User is on new login page")
    public void user_is_on_new_login_page() {
        assertTrue(ObjectManager.getObject().loginPage.goToLoginPage(), "User is able to go to Login Page");
    }

    @Then("User should be on login page")
    public void user_should_be_on_login_page() {
        assertTrue(ObjectManager.getObject().loginPage.verifyOnLoginPage(), "User is on Login Page");
    }

    @Then("User should be redirected to login page")
    public void user_should_be_redirected_to_login_page() {
        assertTrue(ObjectManager.getObject().loginPage.verifyOnLoginPage(), "User is redirected to Login Page after clicking Signup button");
    }

    @Then("Already account {string} should be shown")
    public void already_account_should_be_shown(String errorMessage) {
        setExamplesKeyValueInHashMap("message", errorMessage);
        assertTrue(compareTwoStringsRemoveSpaceIgnoreCase(ObjectManager.getObject().loginPage.getAlreadyAccountErrorMessage(), errorMessage), "Already account error message matches with \"" + errorMessage + "\"");
    }


    @Given("User enter {string} in mobile number input box")
    public void user_enter_in_mobile_number_input_box(String number) {
        setExamplesKeyValueInHashMap("mobile number", number);
        assertTrue(ObjectManager.getObject().loginPage.enterMobileNumber(number), "User is able to enter mobile number in mobile number input box for Login");
    }

    @When("User clicks Continue button")
    public void user_clicks_continue_button() {
        assertTrue(ObjectManager.getObject().loginPage.clickContinueBtn(), "User is able to click Continue button on Login Page");
    }

    @Then("Invalid mobile or required error {string} should be shown")
    public void invalid_mobile_or_required_error_should_be_shown(String message) {
        setExamplesKeyValueInHashMap("message", message);
        assertTrue(compareTwoStringsRemoveSpaceIgnoreCase(ObjectManager.getObject().loginPage.getInvalidOrRequiredMobileNumberText(), message), "Invalid or Required error message matches with \"" + message + "\"");
    }

    @Given("Generate new mobile number and User enter in mobile number input box")
    public void generate_new_mobile_number_and_user_enter_in_mobile_number_input_box() {
        assertTrue(ObjectManager.getObject().loginPage.enterMobileNumber(mobileNumberGenerateAndAppend()), "User is able to generate and enter new mobile number in mobile number input box for Login");
    }

    @When("User clicks Login with Email button")
    public void user_clicks_login_with_email_button() {
        assertTrue(ObjectManager.getObject().loginPage.clickLoginInWithEmailBtn(), "User is able to click Login with Email button");
    }
}