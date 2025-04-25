package cucumber.steps;

import cucumber.context.TestContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import nopCommerceAuto.pages.HomePage;
import nopCommerceAuto.pages.LoginPage;
import org.junit.Assert;

public class LoginPageSteps {
    private final HomePage homePage;
    private final LoginPage loginPage;
    private final TestContext testContext;

    public LoginPageSteps(TestContext testContext) {
        this.testContext = testContext;
        this.homePage = new HomePage(testContext.getDriver());
        this.loginPage = new LoginPage(testContext.getDriver());
    }

    @Given("the user is on the login page")
    public void userIsOnLoginPage() {
        loginPage.open(); // Uses AbstractPage's open() method
    }

    @When("the user enters email {string}")
    public void userEntersEmail(String email) {
        loginPage.fillEmailField(email);
    }

    @When("the user enters password {string}")
    public void userEntersPassword(String password) {
        loginPage.fillPasswordField(password);
    }

    @When("the user clicks the login button")
    public void userClicksLoginButton() {
        loginPage.clickLogInButton();
    }

    @Then("the invalid credentials error should be displayed")
    public void verifyInvalidCredentialsError() {
        Assert.assertTrue("Invalid credentials error not displayed",
                loginPage.isIncorrectCredentialsErrorVisible());
    }

    @Then("the email field error should be displayed")
    public void verifyEmailFieldError() {
        Assert.assertTrue("Email field error not displayed",
                loginPage.isEmailFieldErrorVisible());
    }

    @When("the user attempts to login with email {string} and password {string}")
    public void attemptLogin(String email, String password) {
        loginPage.fillEmailField(email);
        loginPage.fillPasswordField(password);
        loginPage.clickLogInButton();
    }

    @Then("the password field should be empty")
    public void verifyPasswordFieldEmpty() {
        Assert.assertTrue("Password field is not empty",
                loginPage.getPasswordField().getText().isEmpty());
    }

    @Then("the user should be redirected to the home page")
    public void verifyHomePageRedirect() {
        HomePage homePage = new HomePage(testContext.getDriver());
        Assert.assertTrue("User was not redirected to the home page after login",
                homePage.isPageOpened()
        );
    }
}