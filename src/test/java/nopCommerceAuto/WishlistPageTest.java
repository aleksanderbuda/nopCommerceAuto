package nopCommerceAuto;

import nopCommerceAuto.pages.HomePage;
import nopCommerceAuto.pages.NotebooksPage;
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

        notebooksPage.openWishlistPage();
    }
}
