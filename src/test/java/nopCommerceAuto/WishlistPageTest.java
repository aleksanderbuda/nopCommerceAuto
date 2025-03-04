package nopCommerceAuto;

import nopCommerceAuto.pages.HomePage;
import nopCommerceAuto.pages.NotebooksPage;
import nopCommerceAuto.pages.WishlistPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class WishlistPageTest extends AbstractPageTest {

    @Test
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

//zrobić test z data providerem

@Test
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





}
}