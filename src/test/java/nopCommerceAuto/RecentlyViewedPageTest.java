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

        RecentlyViewedPage recentlyViewedPage = notebooksPage.openRecentlyViewed();
        Assert.assertTrue(recentlyViewedPage.isPageOpened(), "Recently Viewed Products page is not opened");

        softAssert.assertEquals(productTitles.get(0), recentlyViewedPage.getRecName(2),
                "First product name is not correctly displayed in recently viewed");
        softAssert.assertEquals(productTitles.get(1), recentlyViewedPage.getRecName(1),
                "Second product name is not correctly displayed in recently viewed");
        softAssert.assertEquals(productTitles.get(2), recentlyViewedPage.getRecName(0),
                "Third product name is not correctly displayed in recently viewed");

        // to do poprawy jest ...
        softAssert.assertAll();
    }

//    @Test
//    public void checkIfRecentlyViewedItemsUpdateThePageWithEveryItem() {
//        SoftAssert softAssert = new SoftAssert();
//        WebDriver driver = getDriver();
//
//        HomePage homePage = new HomePage(driver);
//        homePage.open();
//        Assert.assertTrue(homePage.isPageOpened(), "Home Page is not opened");
//
//        NotebooksPage notebooksPage = homePage.openNotebooksPage();
//        Assert.assertTrue(notebooksPage.isPageOpened(), "Notebooks page is not opened");
//
//        ProductPage productPage1 = notebooksPage.selectProduct(0);
//        String productName1 = productPage1.getName();
//        String productPrice1 = productPage1.getPrice();
//        driver.navigate().back();
//
//        ProductPage productPage2 = notebooksPage.selectProduct(1);
//        String productName2 = productPage2.getName();
//        String productPrice2 = productPage2.getPrice();
//        driver.navigate().back();
//
//        ProductPage productPage3 = notebooksPage.selectProduct(2);
//        String productName3 = productPage3.getName();
//        String productPrice3 = productPage3.getPrice();
//        driver.navigate().back();
//
//        RecentlyViewedPage recentlyViewedPageOpened = productPage3.openRecentlyViewed();
//        Assert.assertTrue(recentlyViewedPageOpened.isPageOpened(), "Recently Viewed Products page is not opened");
//
//        softAssert.assertEquals(productName1, recentlyViewedPageOpened.getRecName(2),
//                "First product name is not correctly displayed in recently viewed");
//        softAssert.assertEquals(productName2, recentlyViewedPageOpened.getRecName(1),
//                "Second product name is not correctly displayed in recently viewed");
//        softAssert.assertEquals(productName3, recentlyViewedPageOpened.getRecName(0),
//                "Third product name is not correctly displayed in recently viewed");
//
//        softAssert.assertAll();
//    }
//    @Test
//    public void checkIfRecentlyViewedItemsUpdateThePageWithEveryItem() {
//        SoftAssert softAssert = new SoftAssert();
//        WebDriver driver = getDriver();
//
//        HomePage homePage = new HomePage(driver);
//        homePage.open();
//        Assert.assertTrue(homePage.isPageOpened(), "Home Page is not opened");
//
//        NotebooksPage notebooksPage = homePage.openNotebooksPage();
//        Assert.assertTrue(notebooksPage.isPageOpened(), "Notebooks page is not opened");
//
//        ProductPage productPage1 = notebooksPage.selectProduct(0);
//        String productName1 = productPage1.getName();
//        String productPrice1 = productPage1.getPrice();
//
//        RecentlyViewedPage recentlyViewedPageOpened = productPage1.openRecentlyViewed();
//        Assert.assertTrue(recentlyViewedPageOpened.isPageOpened(), "Recently Viewed Products page is not opened");
//
//        softAssert.assertEquals(productName1, recentlyViewedPageOpened.getName(0),
//                "First product name is not correctly displayed in recently viewed");
//        softAssert.assertEquals(productPrice1, recentlyViewedPageOpened.getPrice(0),
//                "First product price is not correctly displayed in recently viewed");
//
//        notebooksPage = recentlyViewedPageOpened.openNotebooksPage();
//        softAssert.assertTrue(notebooksPage.isPageOpened(), "Notebooks page failed to open");
//
//        ProductPage productPage2 = notebooksPage.selectProduct(1);
//        String productName2 = productPage2.getName();
//        String productPrice2 = productPage2.getPrice();
//
//        RecentlyViewedPage recentlyViewedPage2 = productPage2.openRecentlyViewed();
//        softAssert.assertTrue(recentlyViewedPage2.isPageOpened(), "Recently Viewed Products page is not opened");
//
//        softAssert.assertEquals(productName2, recentlyViewedPage2.getName(0),
//                "Second product name is not correctly displayed in recently viewed");
//        softAssert.assertEquals(productPrice2, recentlyViewedPage2.getPrice(0),
//                "Second product price is not correctly displayed in recently viewed");
//
//        notebooksPage = recentlyViewedPage2.openNotebooksPage();
//        softAssert.assertTrue(notebooksPage.isPageOpened(), "Notebooks page is not opened");
//
//        ProductPage productPage3 = notebooksPage.selectProduct(2);
//        String productName3 = productPage3.getName();
//        String productPrice3 = productPage3.getPrice();
//
//        RecentlyViewedPage recentlyViewedPage3 = productPage3.openRecentlyViewed();
//        Assert.assertTrue(recentlyViewedPage3.isPageOpened(), "Recently Viewed Products page is not opened");
//
//        softAssert.assertEquals(productName3, recentlyViewedPage3.getName(0),
//                "Third product name is not correctly displayed in recently viewed");
//        softAssert.assertEquals(productPrice3, recentlyViewedPage3.getPrice(0),
//                "Third product price is not correctly displayed in recently viewed");
//
//        softAssert.assertAll();
//    }
}
