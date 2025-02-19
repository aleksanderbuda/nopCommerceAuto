package nopCommerceAuto.pages;

import lombok.Getter;
import org.openqa.selenium.NoSuchElementException;
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

@Getter
public class CheckoutPage extends AbstractPage {

    @FindBy(id = "termsofservice")
    private WebElement termsOfServiceCheckbox;

    @FindBy(id = "checkout")
    private WebElement checkoutButton;


    private final WebDriverWait wait;
    private final Wait<WebDriver> fluentwait;
    private final Actions actions;

    public CheckoutPage(WebDriver driver) {
        super(driver, PageTitles.CHECKOUT_PAGE_TITLE, Urls.CHECKOUT_PAGE_URL);
        PageFactory.initElements(driver, this);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        this.actions = new Actions(driver, Duration.ofSeconds(5));
        this.fluentwait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofMillis(1000))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class);
    }
}
