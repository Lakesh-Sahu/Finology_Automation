package in.finology.stepDefns;

import in.finology.utils.ObjectManager;
import in.finology.pages.*;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import static in.finology.utils.CustomAsserts.*;


public class OTPVerification_Steps {
//    WebDriver driver;
//    HomePage hp;
//    SignUpPage sup;
//    OTPVerificationPage otpvp;
//    LoginPage lp;
//    LoginAuthPage lap;
//
//    public OTPVerification_Steps() {

    /// /        driver = DriverFactory.getDriver("Chrome");
//        driver = ObjectManager.getObject().getDriver();
//        hp = new HomePage(driver);
//        sup = new SignUpPage(driver);
//        otpvp = new OTPVerificationPage(driver);
//        lp = new LoginPage(driver);
//        lap = new LoginAuthPage(driver);
//    }
    @Then("User should not be redirected to otp verification page")
    public void user_should_not_be_redirected_to_otp_verification_page() {
        assertFalse(ObjectManager.getObject().otpVerificationPage.verifyOnOTPVerificationPageUnder10Seconds(), "Invalid user is redirected to otp verification page");
    }

    @Then("User should redirect to OTP verification page")
    public void user_should_redirect_to_otp_verification_page() {
        try {
            assertTrue(ObjectManager.getObject().otpVerificationPage.verifyOnOTPVerificationPage(), "Valid user is not redirected to OTP verification page");
        } catch (Throwable e) {
//            System.out.println("########### 1");
//
//            System.out.println(e.getStackTrace()[0] + "#$#$#$");
//
//            System.out.println("########### 2");
//
//            for (StackTraceElement ee : e.getStackTrace()) {
//                System.out.println(ee + "###");
//            }
//
//            System.out.println("########### 3");
//
//            System.out.println(e.getMessage());
//
//            System.out.println("########### 4");
//
//            System.out.println("getLocalizedMessage : " + e.getLocalizedMessage());
//
//            for (StackTraceElement element : e.getStackTrace()) {
//                // Print the first line from *your* package
//                if (element.getClassName().startsWith("in.finology")) {
//                    System.out.println("First application-level stack trace: " + element);
//                    System.out.println("getFileName : " + element.getFileName());
//                    System.out.println("getClassName : " + element.getClassName());
//                    System.out.println("getMethodName : " + element.getMethodName());
//                    System.out.println("getLineNumber : " + element.getLineNumber());
//                    break;
//                }
//            }


            throw e;
        }
    }

    @Then("Finology logo, home button, enter otp text, otp input box, verify otp button should be displayed on OTP verification page")
    public void finology_logo_home_button_enter_otp_text_otp_input_box_verify_otp_button_should_be_displayed_on_OTP_verification_page() {
        assertTrue(ObjectManager.getObject().otpVerificationPage.verifyAllElementsExceptResendOTPAreDisplayed(), "All elements are not displayed in OTP verification page");
    }
}
