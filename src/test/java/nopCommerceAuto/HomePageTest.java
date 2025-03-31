package nopCommerceAuto;

import nopCommerceAuto.constants.Constants;
import nopCommerceAuto.pages.HomePage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class HomePageTest extends AbstractPageTest {

    @Test()
    public void checkIfCarouselChangesPictures() {
        SoftAssert softAssert = new SoftAssert();
        WebDriver driver = getDriver();

        HomePage homePage = new HomePage(driver);
        homePage.open();
        Assert.assertTrue(homePage.isPageOpened(), "Home Page is not opened");

        homePage.waitForCarouselInitialization();

        boolean galaxyImageChanged = homePage.waitForCarouselImage(Constants.Urls.CAROUSEL_GALAXY_IMAGE_URL);
        softAssert.assertTrue(galaxyImageChanged, "Galaxy slider image did not change as expected");

        boolean iphoneImageChanged = homePage.waitForCarouselImage(Constants.Urls.CAROUSEL_IPHONE_IMAGE_URL);
        softAssert.assertTrue(iphoneImageChanged, "iPhone slider image did not change as expected");

        homePage.switchToNextImage();
        boolean galaxyImageChangedManually = homePage.waitForCarouselImage(Constants.Urls.CAROUSEL_GALAXY_IMAGE_URL);
        softAssert.assertTrue(galaxyImageChangedManually,
                "Galaxy slider image did not change as expected after manually clicking the navigation button");

        homePage.switchToNextImage();
        boolean iphoneImageChangedManually = homePage.waitForCarouselImage(Constants.Urls.CAROUSEL_IPHONE_IMAGE_URL);
        softAssert.assertTrue(iphoneImageChangedManually,
                "iPhone slider image did not change as expected after manually clicking the navigation button");

        softAssert.assertAll();
    }
}
