package nopCommerceAuto.pages;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.NoSuchElementException;

public class NotebooksPage extends AbstractPage {

    private final WebDriverWait wait;
    private final Wait<WebDriver> fluentwait;

    public NotebooksPage(WebDriver driver) {
        super(driver, Urls.NOTEBOOKS_PAGE_URL, PageTitles.NOTEBOOKS_PAGE_TITLE);
        PageFactory.initElements(driver, this);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        this.fluentwait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class);
    }
}
