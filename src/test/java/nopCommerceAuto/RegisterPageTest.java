package nopCommerceAuto;

import nopCommerceAuto.pages.HomePage;
import nopCommerceAuto.pages.RegisterPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class RegisterPageTest extends AbstractPageTest {

    @Test()
    public void verifyRequiredFieldsOnRegistrationForm() {
        SoftAssert softAssert = new SoftAssert();
        WebDriver driver = getDriver();

        HomePage homePage = new HomePage(driver);
        homePage.open();
        Assert.assertTrue(homePage.isPageOpened(), "Home Page is not opened");

        RegisterPage registerPage = homePage.openRegisterPage();
        Assert.assertTrue(registerPage.isPageOpened(), "Register Page is not opened");

        registerPage.clickRegisterButton();
        Assert.assertTrue(registerPage.areRegisterFieldsRequired(), "Required fields are not matching");

        String firstName = createRandomUsername();
        String lastName = createRandomUsername();
        String email = createRandomEmail();
        String password = createRandomPassword();

        registerPage.fillName(firstName);
        softAssert.assertTrue(registerPage.isFirstNameErrorNotVisible(),
                "First name error is still visible after filling");
        softAssert.assertTrue(registerPage.isLastNameErrorVisible(),
                "Last name error should still be visible");
        softAssert.assertTrue(registerPage.isEmailErrorVisible(),
                "Email error should still be visible");
        softAssert.assertTrue(registerPage.isPasswordErrorVisible(),
                "Password error should still be visible");

        registerPage.fillLastName(lastName);
        softAssert.assertTrue(registerPage.isFirstNameErrorNotVisible(),
                "First name error should not be visible");
        softAssert.assertTrue(registerPage.isLastNameErrorNotVisible(),
                "Last name error is still visible after filling");
        softAssert.assertTrue(registerPage.isEmailErrorVisible(),
                "Email error should still be visible");
        softAssert.assertTrue(registerPage.isPasswordErrorVisible(),
                "Password error should still be visible");

        registerPage.fillEmail(email);
        softAssert.assertTrue(registerPage.isEmailErrorNotVisible(),
                "Email error is still visible after filling");

        registerPage.fillPassword(password);
        registerPage.fillConfirmPassword(password);
        softAssert.assertTrue(registerPage.isPasswordErrorNotVisible(),
                "Password error is still visible after filling");

        registerPage.clickRegisterButton();
        Assert.assertTrue(registerPage.isRegistrationSuccess(), "Registration was unsuccessful");

        softAssert.assertAll();
    }
}
