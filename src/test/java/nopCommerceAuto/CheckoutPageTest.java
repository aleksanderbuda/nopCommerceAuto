package nopCommerceAuto;

import nopCommerceAuto.pages.CartPage;
import nopCommerceAuto.pages.HomePage;
import nopCommerceAuto.pages.NotebooksPage;
import nopCommerceAuto.pages.RegisterPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class CheckoutPageTest extends AbstractPageTest {

    @Test
    public void verifyIfBillingAddressIsSavedOnceEntered() {
        SoftAssert softAssert = new SoftAssert();
        WebDriver driver = getDriver();

        HomePage homePage = new HomePage(driver);
        homePage.open();
        Assert.assertTrue(homePage.isPageOpened(), "Home page is not opened");

        RegisterPage registerPage = homePage.openRegisterPage();
        Assert.assertTrue(registerPage.isPageOpened(), "Register Page is not opened");

        String firstName = createRandomUsername();
        String lastName = createRandomUsername();
        String email = createRandomEmail();
        String password = createRandomPassword();

        registerPage.fillName(firstName);
        registerPage.fillLastName(lastName);
        registerPage.fillEmail(email);
        registerPage.fillPassword(password);
        registerPage.fillConfirmPassword(password);

        registerPage.clickRegisterButton();
        Assert.assertTrue(registerPage.isRegistrationSuccess(), "Registration was unsuccessful");

        registerPage.clickContinueButton();
        Assert.assertTrue(homePage.isPageOpened(), "Home Page is not opened");

        NotebooksPage notebooksPage = homePage.openNotebooksPage();
        Assert.assertTrue(notebooksPage.isPageOpened(), "Notebooks page is not opened");
        notebooksPage.clickFirstAddToCartButton();

        CartPage cartPage = notebooksPage.openCartPage();
        Assert.assertTrue(cartPage.isPageOpened(), "Cart Page is not opened");





        softAssert.assertAll();


        //kliknąc checkout, wprowadzić cały adres, kliknąć continue
        //cofnąć się i wejść jeszcze raz i sprawdzić czy zapisane dane znajdują się w zapisanym adresie
    }

}
