package nopCommerceAuto;

import nopCommerceAuto.pages.HomePage;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class HomePageTest extends BasePageTest {

    @Test
    public void testExample() {
        SoftAssert softAssert = new SoftAssert();
        HomePage homePage = new HomePage(getDriver());

        homePage.open();
    }
}
