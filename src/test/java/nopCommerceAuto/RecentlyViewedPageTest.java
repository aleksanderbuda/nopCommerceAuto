package nopCommerceAuto;

import nopCommerceAuto.model.ProductInfo;
import nopCommerceAuto.pages.HomePage;
import nopCommerceAuto.pages.NotebooksPage;
import nopCommerceAuto.pages.ProductPage;
import nopCommerceAuto.pages.RecentlyViewedPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class RecentlyViewedPageTest extends AbstractPageTest {

    @Test
    public void checkIfRecentlyViewedItemsUpdateThePageWithEveryItem() {
        SoftAssert softAssert = new SoftAssert();
        WebDriver driver = getDriver();

        HomePage homePage = new HomePage(driver);
        homePage.open();
        Assert.assertTrue(homePage.isPageOpened(), "Home Page is not opened");

        NotebooksPage notebooksPage = homePage.openNotebooksPage();
        Assert.assertTrue(notebooksPage.isPageOpened(), "Notebooks page is not opened");

        List<String> productTitles = notebooksPage.getProductTitles(List.of(0, 1, 2));
        softAssert.assertEquals(productTitles.size(), 3, "Expected 3 product titles to be collected");

        RecentlyViewedPage recentlyViewedPage = notebooksPage.openRecentlyViewed();
        Assert.assertTrue(recentlyViewedPage.isPageOpened(), "Recently Viewed Products page is not opened");

        softAssert.assertEquals(productTitles.get(0), recentlyViewedPage.getRecName(2),
                "First product name is not correctly displayed in recently viewed");
        softAssert.assertEquals(productTitles.get(1), recentlyViewedPage.getRecName(1),
                "Second product name is not correctly displayed in recently viewed");
        softAssert.assertEquals(productTitles.get(2), recentlyViewedPage.getRecName(0),
                "Third product name is not correctly displayed in recently viewed");

        softAssert.assertAll();
    }

}
