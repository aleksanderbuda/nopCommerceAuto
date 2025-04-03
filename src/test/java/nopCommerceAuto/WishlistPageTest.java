package nopCommerceAuto;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import nopCommerceAuto.constants.Constants;
import nopCommerceAuto.pages.CartPage;
import nopCommerceAuto.pages.HomePage;
import nopCommerceAuto.pages.NotebooksPage;
import nopCommerceAuto.pages.WishlistPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
@Epic("Regression Tests")
@Feature("Wishlist Page")
public class WishlistPageTest extends AbstractPageTest {

    @Test(description = "Check if user is able to access wishlist with shareable link")
    public void checkWishlistShareableLink() {
        SoftAssert softAssert = new SoftAssert();
        WebDriver driver = getDriver();

        HomePage homePage = new HomePage(driver);
        homePage.open();

        NotebooksPage notebooksPage = homePage.openNotebooksPage();
        Assert.assertTrue(notebooksPage.isPageOpened(), "Notebooks page is not opened");

        notebooksPage.clickFirstWishlistButton();
        softAssert.assertTrue(notebooksPage.isSuccessBannerVisible(), "Success banner is not visible");

        WishlistPage wishlistPage = notebooksPage.openWishlistPage();
        Assert.assertTrue(wishlistPage.isPageOpened(), "Wishlist page is not opened");

        String wishlistProductName = wishlistPage.getProductName();
        String wishlistProductPrice = wishlistPage.getProductPrice();
        String shareableWishlistUrl = wishlistPage.getShareableWishlistUrl();

        wishlistPage.clickShareableWishlistUrl();
        Assert.assertEquals(shareableWishlistUrl, driver.getCurrentUrl(), "Url is not the same");

        String shareableWishlistProductName = wishlistPage.getProductName();
        String shareableWishlistProductPrice = wishlistPage.getProductPrice();

        softAssert.assertEquals(wishlistProductName, shareableWishlistProductName, "Product name is not the same");
        softAssert.assertEquals(wishlistProductPrice, shareableWishlistProductPrice, "Product price is not the same");

        softAssert.assertAll();
    }

@Test()
public void checkIfUserCanAddToCartItemFromShareableWishlist() {
    SoftAssert softAssert = new SoftAssert();
    WebDriver driver = getDriver();

    HomePage homePage = new HomePage(driver);
    homePage.open();

    NotebooksPage notebooksPage = homePage.openNotebooksPage();
    Assert.assertTrue(notebooksPage.isPageOpened(), "Notebooks page is not opened");

    notebooksPage.clickFirstWishlistButton();
    softAssert.assertTrue(notebooksPage.isSuccessBannerVisible(), "Success banner is not visible");

    WishlistPage wishlistPage = notebooksPage.openWishlistPage();
    Assert.assertTrue(wishlistPage.isPageOpened(), "Wishlist page is not opened");

    wishlistPage.clickShareableWishlistUrl();
    softAssert.assertEquals(Constants.PageTitles.SHAREABLE_WISHLIST_PAGE_TITLE, wishlistPage.getTableTitle(), "Title of the Shareable Wishlist page dose not match");
    String shareableWishlistProductName = wishlistPage.getProductName();
    String shareableWishlistProductPrice = wishlistPage.getProductPrice();

    wishlistPage.clickAddToCartCheckbox();

    CartPage cartPage = wishlistPage.clickAddToCartButton();
    Assert.assertTrue(cartPage.isPageOpened(), "Cart page is not opened");

    String cartProductName = cartPage.getProductName();
    String cartProductPrice = cartPage.getProductPrice();

    softAssert.assertEquals(shareableWishlistProductName, cartProductName, "Product name is not the same");
    softAssert.assertEquals(shareableWishlistProductPrice,cartProductPrice,  "Product price is not the same");

    softAssert.assertAll();
    }
}