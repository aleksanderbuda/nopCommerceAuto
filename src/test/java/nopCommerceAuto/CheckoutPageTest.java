package nopCommerceAuto;

import net.bytebuddy.utility.RandomString;
import nopCommerceAuto.pages.*;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
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
        notebooksPage.clickAddToCartButton();
        softAssert.assertTrue(notebooksPage.isSuccessBannerVisible(), "Success banner is not visible");

        CartPage cartPage = notebooksPage.openCartPage();
        Assert.assertTrue(cartPage.isPageOpened(), "Cart Page is not opened");
        cartPage.clickTOSCheckbox();

        CheckoutPage checkoutPage = cartPage.openCheckoutPage();
        String selectedCountry = checkoutPage.selectRandomCountry();

        String city = StringUtils.capitalize(RandomStringUtils.randomAlphabetic(7).toLowerCase());
        String address = createRandomAddress();
        String zipCode = RandomStringUtils.randomNumeric(5);
        String phoneNumber = RandomStringUtils.randomAlphabetic(9);

        checkoutPage.fillCity(city);
        checkoutPage.fillAddress(address);
        checkoutPage.fillZipCode(zipCode);
        checkoutPage.fillPhoneNumber(phoneNumber);
        checkoutPage.clickContinueButton();

        checkoutPage.openCartPage();
        cartPage.clickTOSCheckbox();
        CheckoutPage reopenedCheckoutPage = cartPage.openCheckoutPage();

        String savedBillingInfo = reopenedCheckoutPage.getSavedBillingAddressInfo();
        String expectedBillingInfo = firstName + " " +  lastName + ", " +  selectedCountry + ", " +  city + ", " +  address + ", " +  zipCode;

        softAssert.assertTrue(savedBillingInfo.contains(expectedBillingInfo),
                "Saved billing address does not match expected values. Expected: " +
                        expectedBillingInfo + ", Actual: " + savedBillingInfo);

        softAssert.assertAll();
    }
}
