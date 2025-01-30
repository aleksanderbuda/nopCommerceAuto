package nopCommerceAuto.pages;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@Getter
public class RegisterPage extends AbstractPage {

    private final WebDriverWait wait;

    public RegisterPage(WebDriver driver) {
        super(driver, PageTitles.REGISTER_PAGE_TITLE, Urls.REGISTER_PAGE_URL);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }



}
