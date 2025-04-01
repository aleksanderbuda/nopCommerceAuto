package nopCommerceAuto.pages;

import lombok.Getter;
import nopCommerceAuto.model.ProductInfo;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Getter
public class CompareProductsPage extends AbstractPage {

    @FindBy(xpath = "//span[@class='close']")
    private WebElement greenBarCloseButton;

    @FindBy(xpath = "//div[@class='no-data']")
    private WebElement comparePageEmptyListText;

    @FindBy(xpath = "//a[@class='picutre']")
    private WebElement singlePicture;

    @FindBy(xpath = "//button[@class='button-2 remove-button']")
    private WebElement singleRemoveButton;

    private static final String TITLE_LOCATOR = "//tr[@class='product-name']//a";

    private static final String PRICE_LOCATOR = "//tr[@class='product-price']/td[@style]";

    private static final String CONTAINER_LOCATOR = ("//table[@class='compare-products-table']");

    private final WebDriverWait wait;
    private final Wait<WebDriver> fluentwait;

    public CompareProductsPage(WebDriver driver) {
        super(driver, PageTitles.COMPARE_PRODUCTS_PAGE_TITLE, Urls.COMPARE_PRODUCT_PAGE_URL);
        PageFactory.initElements(driver, this);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        this.fluentwait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class);
    }

    public List<ProductInfo> getProductInfoFromTable() {
        List<String> titles = driver.findElements(By.xpath(TITLE_LOCATOR))
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
        LOGGER.info("Found {} titles: {}", titles.size(), titles);

        List<String> prices = driver.findElements(By.xpath(PRICE_LOCATOR))
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
        LOGGER.info("Found {} prices: {}", prices.size(), prices);

        if (titles.size() != prices.size()) {
            LOGGER.error("Mismatch between product titles ({}) and prices ({}) count", titles.size(), prices.size());
            throw new IllegalStateException("Mismatch between product titles and prices count.");
        }
        return IntStream.range(0, titles.size())
                .mapToObj(i -> new ProductInfo(titles.get(i), prices.get(i)))
                .collect(Collectors.toList());
    }
}

