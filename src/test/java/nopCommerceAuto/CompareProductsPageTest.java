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

            NotebooksPage notebooksPage = homePage.openNotebooksPage();
            Assert.assertTrue(notebooksPage.isPageOpened(), "Notebooks page is not opened");

            notebooksPage.click8GbMemoryCheckbox();
            softAssert.assertTrue(notebooksPage.isCheckbox8GbClicked(),
                    "The URL did not change after checking the 8GB memory box");

            pause(1);
            List<ProductInfo> products = notebooksPage.getProductInfo();
            ProductInfo firstProduct = products.get(0);
            ProductInfo secondProduct = products.get(1);
//            System.out.println(firstProduct);
//            System.out.println(secondProduct);

            notebooksPage.clickCompareButtonForProducts(2);

            CompareProductsPage compareProductsPage = notebooksPage.openCompareProductPage();
            Assert.assertTrue(compareProductsPage.isPageOpened(),
                    "Compare Product Page is not opened");


            softAssert.assertAll();

        }
        //dodać jeden produkt do compare page zgarnąć jego cene, nazwe i img src z product page
        //dodać drugi produkt do compare page zgarnąć jego cene, nazwe i img src z product page
        // na compare page assert price, name, img src z obrazka
    //dodać, log, że stronka się otworzyła
    }

