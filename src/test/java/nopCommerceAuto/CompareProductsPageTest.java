package nopCommerceAuto;

import nopCommerceAuto.pages.CompareProductsPage;
import nopCommerceAuto.pages.HomePage;
import nopCommerceAuto.pages.NotebooksPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

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

            softAssert.assertAll();

        }
        //dodać jeden produkt do compare page zgarnąć jego cene, nazwe i img src z product page
        //dodać drugi produkt do compare page zgarnąć jego cene, nazwe i img src z product page
        // na compare page assert price, name, img src z obrazka
    }

