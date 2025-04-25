package cucumber.steps;

import cucumber.context.TestContext;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import nopCommerceAuto.pages.HomePage;
import nopCommerceAuto.pages.RegisterPage;
import org.junit.Assert;

import static nopCommerceAuto.AbstractPageTest.*;

public class RegisterPageSteps {
    private final RegisterPage registerPage;
    private final TestContext testContext;
    private final HomePage homePage;

    public RegisterPageSteps(TestContext testContext) {
        this.testContext = testContext;
        this.registerPage = new RegisterPage(testContext.getDriver());
        this.homePage = new HomePage((testContext.getDriver()));
    }

    @Then("the user navigates to registration page")
    public void userNavigatesToRegisterPage() {
        homePage.openRegisterPage();
    }

    @When("the user fills first name with random value")
    public void fillFirstNameRandom() {
        String fn = createRandomUsername();
        testContext.setFirstName(fn);
        registerPage.fillName(fn);
    }

    @When("the user fills last name with random value")
    public void fillLastNameRandom() {
        String ln = createRandomUsername();
        testContext.setLastName(ln);
        registerPage.fillLastName(ln);
    }


    @When("the user fills email with {string}")
    public void fillEmailManually(String email) {
        testContext.setEmail(email);
        registerPage.fillEmail(email);
    }

    @When("the user fills email with random value")
    public void fillEmailRandom() {
        String email = createRandomEmail();
        testContext.setEmail(email);
        registerPage.fillEmail(email);
    }

    @When("the user fills company with {string}")
    public void fillCompany(String company) {
        registerPage.fillCompanyName(company);
    }

    @When("the user fills password with random value")
    public void fillPasswordRandom() {
        String pw = createRandomPassword(7);
        testContext.setPassword(pw);
        registerPage.fillPassword(pw);
        registerPage.fillConfirmPassword(pw);
    }

    @When("the user fills confirm password with {string}")
    public void fillConfirmPassword(String confirmPassword) {
        registerPage.fillConfirmPassword(confirmPassword);
    }

    @When("the user selects gender")
    public void selectGender() {
        registerPage.clickGenderButton();
    }

    @When("the user clicks the register button")
    public void clickRegister() {
        registerPage.clickRegisterButton();
    }

    @Then("the registration should be successful")
    public void verifySuccessfulRegistration() {
        Assert.assertTrue("Registration did not complete successfully",
                registerPage.isRegistrationSuccess());
    }

    @When("the user clicks the continue button")
    public void clickContinue() {
        registerPage.clickContinueButton();
    }

    @Then("the first name error should be visible")
    public void firstNameErrorVisible() {
        Assert.assertTrue("First name error not visible", registerPage.isFirstNameErrorVisible());
    }

    @Then("the last name error should be visible")
    public void lastNameErrorVisible() {
        Assert.assertTrue("Last name error not visible", registerPage.isLastNameErrorVisible());
    }

    @Then("the email error should be visible")
    public void emailErrorVisible() {
        Assert.assertTrue("Email error not visible", registerPage.isEmailErrorVisible());
    }

    @Then("the password error should be visible")
    public void passwordErrorVisible() {
        Assert.assertTrue("Password error not visible", registerPage.isPasswordErrorVisible());
    }

    @Then("all required field errors should be visible")
    public void allRequiredFieldsVisible() {
        Assert.assertTrue("Not all required errors are visible", registerPage.areRegisterFieldsRequired());
    }

    @When("the user fills all fields except {string}")
    public void fillAllFieldsExcept(String excludedField) {
        if (!excludedField.equals("first name")) {
            registerPage.fillName("John");
        }
        if (!excludedField.equals("last name")) {
            registerPage.fillLastName("Doe");
        }
        if (!excludedField.equals("email")) {
            registerPage.fillEmail("valid@example.com");
        }
        if (!excludedField.equals("password")) {
            registerPage.fillPassword("ValidPass123");
            registerPage.fillConfirmPassword("ValidPass123");
        }
    }

}
