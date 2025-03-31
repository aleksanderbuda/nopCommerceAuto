package nopCommerceAuto;

import nopCommerceAuto.pages.CartPage;
import nopCommerceAuto.utils.DriverFactory;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.net.MalformedURLException;
import java.util.Random;

public class AbstractPageTest {

    protected WebDriver driver;

    protected final Logger LOGGER = LogManager.getLogger(this.getClass());

    @BeforeMethod
    public void SetUp() throws MalformedURLException {
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

    public static String createRandomAddress() {
        int streetNumber = new Random().nextInt(8999) + 1000; // 1000 to 9999
        String streetName = StringUtils.capitalize(RandomStringUtils.randomAlphabetic(10).toLowerCase());
        return String.format("%d %s St", streetNumber, streetName);
    }

    public static char getRandomSpecialChar() {
        Random random = new Random();
        String specialSymbols = "[$&+=?@#|/\"<>^*()%!]~`";
        return specialSymbols.toCharArray()[random.nextInt(specialSymbols.length())];
    }


}


