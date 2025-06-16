package in.finology.stepDefns;

import in.finology.utils.CommonMethods;
import in.finology.utils.ObjectManager;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static in.finology.utils.CustomAsserts.*;

public class AccountDashboard_Steps extends CommonMethods {

    @Given("User is logged in and on account dashboard page")
    public void userIsLoggedInAndOnAccountDashboardPage() {
        assertTrue( ObjectManager.getObject().loginAuthPage.performLoginUsingMobileNumberOrEmail("testuserac121@gmail.com", "finTestUserAcology") && ObjectManager.getObject().accountDashboardPage.verifyOnDashboardPage() && ObjectManager.getObject().accountDashboardPage.userPhotoImgBtnIsDisplayed(), "User is on Dashboard Page and logged in");
    }

    @Then("User should be redirected to account dashboard page")
    public void user_should_be_redirected_to_account_dashboard_page() {
        assertTrue(ObjectManager.getObject().accountDashboardPage.verifyOnDashboardPage(), "User is on Account Dashboard Page");
    }

    @And("User should perform logout from account dashboard page")
    public void user_should_perform_logout_from_account_dashboard_page() {
        assertTrue(ObjectManager.getObject().accountDashboardPage.performLogoutFromAccountDashboardPageUsingSideBarLogoutBtn(), "User is able to logout from the side bar logout button on Dashboard Page");
    }


    @Given("User should be on account dashboard page")
    public void userShouldBeOnAccountDashboardPage() {
        assertTrue(ObjectManager.getObject().accountDashboardPage.verifyOnDashboardPage(), "User is on Account Dashboard Page");
    }

    @Then("All dashboard elements should be displayed")
    public void allDashboardElementsShouldBeDisplayed() {
        assertTrue(ObjectManager.getObject().accountDashboardPage.verifyAllElementsAreDisplayed(), "All elements on Account Dashboard Page are visible");
    }

    @When("User clicks user profile")
    public void userClicksUserProfile() {
        assertTrue(ObjectManager.getObject().accountDashboardPage.clickUserPhotoImgBtn(), "User is able to click user photo on Account Dashboard Page");
    }

    @Then("All user photo elements should be displayed")
    public void allUserPhotoElementsShouldBeDisplayed() {
        assertTrue(ObjectManager.getObject().accountDashboardPage.verifyAllUserPhotoElementsAreDisplayed(), "All elements of user photo elements in Account Dashboard Page are visible");
    }

    @Then("Welcome {string} should be displayed")
    public void welcomeShouldBeDisplayed(String message) {
        setExamplesKeyValueInHashMap("welcome message", message);
        assertTrue(compareTwoStringsRemoveSpaceIgnoreCase(ObjectManager.getObject().accountDashboardPage.getWelcomeText(), message), "Welcome message of Account Dashboard Page matches with \"" + message + "\"");
    }
}