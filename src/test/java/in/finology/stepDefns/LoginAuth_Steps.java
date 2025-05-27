package in.finology.stepDefns;

import in.finology.utils.ObjectManager;
import in.finology.pages.*;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import static in.finology.utils.CustomAsserts.*;


public class LoginAuth_Steps {

//    WebDriver driver;
//    HomePage hp;
//    SignUpPage sup;
//    OTPVerificationPage otpvp;
//    LoginPage lp;
//    LoginAuthPage lap;
//
//    public LoginAuth_Steps() {

    /// /        driver = DriverFactory.getDriver("Chrome");
//        driver = ObjectManager.getObject().getDriver();
//        hp = new HomePage(driver);
//        sup = new SignUpPage(driver);
//        otpvp = new OTPVerificationPage(driver);
//        lp = new LoginPage(driver);
//        lap = new LoginAuthPage(driver);
//    }
    @Then("User should be redirected to Login Auth Page")
    public void user_should_be_redirected_to_login_auth_page() {
        Assert.assertTrue(ObjectManager.getObject().loginAuthPage.verifyOnLoginAuthPage(), "user is not redirected to login auth page");
    }

    @Then("User enter {string} in the mobile or email input box and {string} in the password input box")
    public void user_enter_in_the_mobile_or_email_input_box_and_in_the_password_input_box(String mobileNumberOrEmail, String password) {
        assertTrue(ObjectManager.getObject().loginAuthPage.enterMobileNumberOrEmail(mobileNumberOrEmail), "User is unable to enter mobile number or email in the mobileNumberOrEmail box");
        assertTrue(ObjectManager.getObject().loginAuthPage.enterPassword(password), "User is unable to enter password in password input box");
    }

    @Then("User clicks login button")
    public void user_clicks_login_button() {
        assertTrue(ObjectManager.getObject().loginAuthPage.clickLoginBtn(), "User is unable to click Login button on Login Auth page");
    }
}