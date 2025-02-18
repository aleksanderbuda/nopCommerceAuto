package nopCommerceAuto;

import nopCommerceAuto.utils.DriverFactory;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.Random;

public class AbstractPageTest {

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

    public static String createRandomUsername() {
        return "John " + RandomStringUtils.randomAlphabetic(1,5).toLowerCase();
    }

    public static String createRandomEmail() {
        String email = RandomStringUtils.randomAlphabetic(1,5).toLowerCase();
        return email + "@example.com";
    }

    public static String createRandomCompanyName() {
        return RandomStringUtils.randomAlphabetic(1,10).toUpperCase();
    }

    public String createRandomPassword() {
        return createRandomPassword(10);
    }

    public static String createRandomPassword(int size) {
        String pass = RandomStringUtils.randomAlphabetic(size - 4) +
                RandomStringUtils.randomAlphabetic(1).toUpperCase() +
                RandomStringUtils.randomAlphabetic(1).toLowerCase() +
                RandomStringUtils.randomNumeric(1);
        return pass + getRandomSpecialChar();

    }

    public static char getRandomSpecialChar() {
        Random random = new Random();
        String specialSymbols = "[$&+=?@#|/\"<>^*()%!]~`";
        return specialSymbols.toCharArray()[random.nextInt(specialSymbols.length())];
    }






}


