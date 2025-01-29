package nopCommerceAuto.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;

public class HomePage extends AbstractPage {

    private final WebDriverWait wait;


    public HomePage(WebDriver driver) {
        super(driver, PageTitles.HOME_PAGE_TITLE);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public void open() {
        LOGGER.info("Opening the Home Page");
        driver.get(Urls.BASE_URL);
        LOGGER.info("Landing page has opened successfully");
    }


}
