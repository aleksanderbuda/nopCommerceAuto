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
import java.util.NoSuchElementException;

@Getter
public class ProductPage extends AbstractPage {

    @FindBy(xpath ="//p[@class='content']")
    private WebElement greenBarProductAddedToCompareText;

    @FindBy(xpath = "//p[@class='content']/a[@href='/compareproducts']")
    private WebElement greenBarProductComparisonLink;

    @FindBy(xpath = "//div[@class='product-price']/span")
    private WebElement productPagePrice;

    @FindBy(xpath = "//div[@class='product-name']")
    private WebElement productPageName;

    private final WebDriverWait wait;
    private final Wait<WebDriver> fluentwait;
    private final Actions actions;

    public ProductPage(WebDriver driver) {
        super(driver,  PageTitles.PRODUCT_PAGE_TITLE, Urls.PRODUCT_PAGE_URL);
        PageFactory.initElements(driver, this);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        this.actions = new Actions(driver, Duration.ofSeconds(5));
        this.fluentwait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class);
    }

    public String getPrice() {
        return productPagePrice.getText();
    }

    public String getName() {
        return productPageName.getText();
    }
}
