package nopCommerceAuto;

import nopCommerceAuto.model.ProductInfo;
import nopCommerceAuto.pages.CompareProductsPage;
import nopCommerceAuto.pages.HomePage;
import nopCommerceAuto.pages.NotebooksPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class CompareProductsPageTest extends BasePageTest {

        @Test
        public void verifyAddProductsToComparePage() {
            SoftAssert softAssert = new SoftAssert();
            WebDriver driver = getDriver();

            HomePage homePage = new HomePage(driver);
            homePage.open();
            Assert.assertTrue(homePage.isPageOpened(), "Home Page is not opened");

            pause(1);
            NotebooksPage notebooksPage = homePage.openNotebooksPage();
            Assert.assertTrue(notebooksPage.isPageOpened(), "Notebooks page is not opened");

            notebooksPage.click8GbMemoryCheckbox();
            softAssert.assertTrue(notebooksPage.isCheckbox8GbClicked(),
                    "The URL did not change after checking the 8GB memory box");

            pause(1);
            List<ProductInfo> notebooksPageProducts = notebooksPage.getProducts();
            ProductInfo notebooksPageFirstProduct = notebooksPageProducts.get(0);
            ProductInfo notebooksPageSecondProduct = notebooksPageProducts.get(1);

            notebooksPage.clickCompareButton(2);

            CompareProductsPage compareProductsPage = notebooksPage.openComparePage();
            Assert.assertTrue(compareProductsPage.isPageOpened(),
                    "Compare Product Page is not opened");

            pause(1);
            List<ProductInfo> comparePageProducts = compareProductsPage.getProductInfoFromTable();
            ProductInfo comparePageFirstProduct = comparePageProducts.get(0);
            ProductInfo comparePageSecondProduct = comparePageProducts.get(1);

            Assert.assertEquals(notebooksPageFirstProduct.toString(), comparePageSecondProduct.toString(),
                    "First product on Compare Page is not the same as selected on Notebooks page");
            Assert.assertEquals(notebooksPageSecondProduct.toString(), comparePageFirstProduct.toString(),
                    "Second product on Compare Page is not the same as selected on Notebooks page");

            softAssert.assertAll();
        }
    }

