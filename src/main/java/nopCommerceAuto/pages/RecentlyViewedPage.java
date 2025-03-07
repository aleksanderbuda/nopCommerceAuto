package nopCommerceAuto.pages;

import lombok.Getter;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

@Getter
public class RecentlyViewedPage extends AbstractPage {

    @FindBy(xpath = "//h2[@class='product-title']")
    private List<WebElement> titles;

    @FindBy(xpath = "//span[@class='price actual-price']")
    private List<WebElement> prices;

    private final WebDriverWait wait;
    private final Wait<WebDriver> fluentwait;
    private final Actions actions;

    public RecentlyViewedPage(WebDriver driver) {
        super(driver, PageTitles.RECENTLY_VIEWED_PAGE_TITLE, Urls.RECENTLY_VIEWED_PAGE_URL);
        PageFactory.initElements(driver, this);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        this.actions = new Actions(driver, Duration.ofSeconds(5));
        this.fluentwait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class);
    }

    public String getRecName(int index) {
        return titles.stream()
                .skip(index)
                .limit(1)
                .map(WebElement::getText)
                .findFirst()
                .orElseThrow(() -> new IndexOutOfBoundsException("Index " + index + " is out of bounds"));
    }

    public String getRecPrice(int index) {
        return prices.stream()
                .skip(index)
                .limit(1)
                .map(WebElement::getText)
                .findFirst()
                .orElseThrow(() -> new IndexOutOfBoundsException("Index " + index + " is out of bounds"));
    }

}
