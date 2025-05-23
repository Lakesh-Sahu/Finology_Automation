package stepDefns;

import com.pages.*;
import driverFactory.DriverFactory;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class LoginAuth_Steps {

    WebDriver driver;
    HomePage hp;
    SignUpPage sup;
    OTPVerificationPage otpvp;
    LoginPage lp;
    LoginAuthPage lap;

    public LoginAuth_Steps() {
        driver = DriverFactory.getDriver("Chrome");
        hp = new HomePage(driver);
        sup = new SignUpPage(driver);
        otpvp = new OTPVerificationPage(driver);
        lp = new LoginPage(driver);
        lap = new LoginAuthPage(driver);
    }

    @Then("User should be redirected to Login Auth Page")
    public void user_should_be_redirected_to_login_auth_page() {
        Assert.assertTrue(lap.verifyOnLoginAuthPage());
    }

    @Then("User enter {string} in the mobile or email input box and {string} in the password input box")
    public void user_enter_in_the_mobile_or_email_input_box_and_in_the_password_input_box(String mobileNumberOrEmail, String password) {
        Assert.assertTrue(lap.enterMobileNumberOrEmail(mobileNumberOrEmail), "User is unable to enter mobile number or email in the mobileNumberOrEmail box");
                Assert.assertTrue(lap.enterPassword(password), "User is unable to enter password in password input box");
    }

    @Then("User clicks login button")
    public void user_clicks_login_button() {
        Assert.assertTrue(lap.clickLoginBtn(), "User is unable to click Login button on Login Auth page");
    }
}