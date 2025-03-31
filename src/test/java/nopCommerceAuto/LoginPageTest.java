package nopCommerceAuto;

import nopCommerceAuto.pages.HomePage;
import nopCommerceAuto.pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utils.LoginDataProvider;

public class LoginPageTest extends AbstractPageTest {

    @Test(dataProvider = "credentialsData", dataProviderClass = LoginDataProvider.class)
    public void testLoginWithDataCredentials(String email, String password, boolean expectSuccess) {
        SoftAssert softAssert = new SoftAssert();
        WebDriver driver = getDriver();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();

        loginPage.fillEmailField(email);
        loginPage.fillPasswordField(password);
        loginPage.clickLogInButton();

        HomePage homePage = new HomePage(driver);
        softAssert.assertTrue(
                expectSuccess ? homePage.isPageOpened() : loginPage.isIncorrectCredentialsErrorVisible(),
                expectSuccess ? "Login should succeed for: " + email : "Credentials error should be visible for: " + email);

        softAssert.assertAll();
    }

    @Test(dataProvider = "emailFormatData", dataProviderClass = LoginDataProvider.class)
    public void testLoginWithInvalidEmailFormat(String email, String password, boolean expectSuccess) {
        SoftAssert softAssert = new SoftAssert();
        WebDriver driver = getDriver();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();

        loginPage.fillEmailField(email);
        loginPage.fillPasswordField(password);
        loginPage.clickLogInButton();

        softAssert.assertTrue(loginPage.isEmailFieldErrorVisible(),
                "Email validation error should be visible for: " + email);

        softAssert.assertAll();
    }
}