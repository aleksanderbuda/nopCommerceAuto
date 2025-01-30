package nopCommerceAuto;

import nopCommerceAuto.pages.HomePage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class HomePageTest extends BasePageTest {

    @Test
    public void checkIfSliderBannerChangesPictures() {
        SoftAssert softAssert = new SoftAssert();
        WebDriver driver = getDriver();

        HomePage homePage = new HomePage(driver);
        homePage.open();
        Assert.assertTrue(homePage.isPageOpened(), "Home Page is not opened");


        softAssert.assertAll();
    }
}
