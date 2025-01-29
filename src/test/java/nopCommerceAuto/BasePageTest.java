package nopCommerceAuto;

import nopCommerceAuto.utils.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BasePageTest {

    protected WebDriver driver;

    @BeforeMethod
    public void SetUp() {
        driver = DriverFactory.createDriver();
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
