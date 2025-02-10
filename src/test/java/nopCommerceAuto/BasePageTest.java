package nopCommerceAuto;

import nopCommerceAuto.utils.DriverFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BasePageTest {

    protected WebDriver driver;

    protected final Logger LOGGER = LogManager.getLogger(this.getClass());


    @BeforeMethod
    public void SetUp() {
        driver = DriverFactory.createDriver();
    }

    protected WebDriver getDriver() {
        return DriverFactory.getDriver();
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }

    public void pause(Number timeout) {
        LOGGER.debug("Will wait for {} seconds", timeout);

        try {
            long timeoutMillis = (long) (timeout.doubleValue() * 1000);
            Thread.sleep(timeoutMillis);
        } catch (InterruptedException e) {
            LOGGER.warn("Thread was interrupted during pause", e);
            Thread.currentThread().interrupt();
        }

        LOGGER.debug("Pause is over. Keep going..");
    }

}
