package in.finology.stepDefns;

import in.finology.utils.ObjectManager;
import io.cucumber.java.en.Then;

import static in.finology.utils.CustomAsserts.*;

public class OTPVerification_Steps {

    @Then("User should not be redirected to otp verification page")
    public void user_should_not_be_redirected_to_otp_verification_page() {
        assertTrue(!ObjectManager.getObject().otpVerificationPage.verifyOnOTPVerificationPageUnder5Seconds(), "User is not redirected to OTP Verification Page with invalid user credentials");
    }

    @Then("User should redirect to OTP verification page")
    public void user_should_redirect_to_otp_verification_page() {
        assertTrue(ObjectManager.getObject().otpVerificationPage.verifyOnOTPVerificationPage(), "User is redirected to OTP Verification Page with valid user credentials");
    }

    @Then("Finology logo, home button, enter otp text, otp input box, verify otp button should be displayed on OTP verification page")
    public void finology_logo_home_button_enter_otp_text_otp_input_box_verify_otp_button_should_be_displayed_on_OTP_verification_page() {
        assertTrue(ObjectManager.getObject().otpVerificationPage.verifyAllElementsExceptResendOTPAreDisplayed(), "All elements are displayed on OTP Verification Page");
    }
}