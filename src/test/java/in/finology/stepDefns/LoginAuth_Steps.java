package in.finology.stepDefns;

import in.finology.utils.CommonMethods;
import in.finology.utils.ObjectManager;
import io.cucumber.java.en.Then;
import org.testng.Assert;

import static in.finology.utils.CustomAsserts.*;

public class LoginAuth_Steps extends CommonMethods {

    @Then("User should be redirected to Login Auth Page")
    public void user_should_be_redirected_to_login_auth_page() {
        Assert.assertTrue(ObjectManager.getObject().loginAuthPage.verifyOnLoginAuthPage(), "User is redirected to Login Auth Page");
    }

    @Then("User enter {string} in the mobile or email input box and {string} in the password input box")
    public void user_enter_in_the_mobile_or_email_input_box_and_in_the_password_input_box(String mobileNumberOrEmail, String password) {
        setExamplesKeyValueInHashMap("mobile number or email", mobileNumberOrEmail);
        setExamplesKeyValueInHashMap("password", password);
        assertTrue(ObjectManager.getObject().loginAuthPage.enterMobileNumberOrEmail(mobileNumberOrEmail), "User is able to enter mobile number or email in the mobileNumberOrEmail box");
        assertTrue(ObjectManager.getObject().loginAuthPage.enterPassword(password), "User is able to enter password in password input box");
    }

    @Then("User clicks login button")
    public void user_clicks_login_button() {
        assertTrue(ObjectManager.getObject().loginAuthPage.clickLoginBtn(), "User is able to click Login button on Login Auth page");
    }
}