package nopCommerceAuto.pages;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@Getter
public class HomePage extends AbstractPage {

    private final WebDriverWait wait;

    public HomePage(WebDriver driver) {
        super(driver, PageTitles.HOME_PAGE_TITLE, Urls.HOME_PAGE_URL);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

}
