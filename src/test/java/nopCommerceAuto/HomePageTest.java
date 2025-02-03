package nopCommerceAuto;

import nopCommerceAuto.constants.Constants;
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

        boolean galaxyImageChanged = homePage.waitForSliderImage(Constants.Urls.SLIDER_GALAXY_URL);
        softAssert.assertTrue(galaxyImageChanged, "Galaxy slider image did not change as expected");

        boolean iphoneImageChanged = homePage.waitForSliderImage(Constants.Urls.SLIDER_IPHONE_URL);
        softAssert.assertTrue(iphoneImageChanged, "iPhone slider image did not change as expected");

        softAssert.assertAll();
    }
}
