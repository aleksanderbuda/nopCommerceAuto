package nopCommerceAuto.pages;
import nopCommerceAuto.model.ProductInfo;

import lombok.Getter;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Getter
public class NotebooksPage extends AbstractPage {

    @FindBy(id = "attribute-option-9")
    private WebElement memoryCheckbox8Gb;

    @FindBy(xpath = "//div[@class='product-item']")
    private List<WebElement> productItems;

    @FindBy(xpath = "//button[@class='button-2 add-to-compare-list-button']")
    private List<WebElement> compareButtons;

    @FindBy(xpath = "//div[@class=\"bar-notification success\"]")
    private WebElement successBar;

    @FindBy(xpath = "//h2[@class='product-title']/a")
    private WebElement productTitle;

    @FindBy(xpath = "//div[@class='picture']")
    private WebElement pictureImageLink;

    @FindBy(xpath = "//span[@class='price actual-price']")
    private WebElement productPrice;

    @FindBy(xpath = "//div[@class='product-item']")
    private List<WebElement> productInfo;

    @FindBy(xpath = "//a[@href='/compareproducts']")
    private WebElement compareProductListLink;

    @FindBy(xpath = "//div[@class='buttons']/button[@class='button-2 product-box-add-to-cart-button']")
    private List<WebElement> addToCartButtons;

    private static final String TITLE_LOCATOR = (".//h2[@class='product-title']//a");

    private static final String PRICE_LOCATOR = (".//span[@class='price actual-price']");

    private static final String CONTAINER_LOCATOR = ("//div[@class='product-item']");

    private final WebDriverWait wait;
    private final Wait<WebDriver> fluentwait;
    private final Actions actions;

    public NotebooksPage(WebDriver driver) {
        super(driver, PageTitles.NOTEBOOKS_PAGE_TITLE, Urls.NOTEBOOKS_PAGE_URL);
        PageFactory.initElements(driver, this);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        this.actions = new Actions(driver, Duration.ofSeconds(5));
        this.fluentwait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class);
    }

    public List<ProductInfo> getProducts() {
        return getProductInfo(CONTAINER_LOCATOR, TITLE_LOCATOR, PRICE_LOCATOR);
    }

    public List<ProductInfo> getProductInfo(String containerLocator, String titleLocator, String priceLocator) {
        List<WebElement> productElements = driver.findElements(By.xpath(containerLocator));

        List<ProductInfo> products = productElements.stream()
                .map(element -> {
                    try {
                        String title = element.findElement(By.xpath(titleLocator)).getText().trim();
                        String price = element.findElement(By.xpath(priceLocator)).getText().trim();
                        LOGGER.debug("Creating ProductInfo: title='{}', price='{}'", title, price);
                        return new ProductInfo(title, price);
                    } catch (Exception e) {
                        LOGGER.error("Error extracting product info: {}", e.getMessage());
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        LOGGER.info("Collecting information from list of {} products", products.size());
        return products;
    }

    public void click8GbMemoryCheckbox() {
        LOGGER.info("Checkbox for 8 GB has been selected");
        memoryCheckbox8Gb.click();
    }

    public boolean isCheckbox8GbClicked() {
        try {
            return wait.until(ExpectedConditions.urlToBe(Urls.NOTEBOOKS_8GB_URL));
        } catch (TimeoutException e) {
        }
        return false;
    }

    public void clickCompareButton(int products) {
        if (productItems.isEmpty()) {
            LOGGER.error("There are no available products");
            return;
        }
        compareButtons.stream()
                .limit(products)
                .forEach(button -> {
                    try {
                        fluentwait.until(ExpectedConditions.elementToBeClickable(button)).click();
                        fluentwait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.xpath("//div[@class='bar-notification success']")));
                        fluentwait.until(ExpectedConditions.invisibilityOfElementLocated(
                                By.xpath("//div[@class='bar-notification success']")));
                        LOGGER.info("Product from {} has been added to comparison list.", this.getClass().getSimpleName());
                    } catch (TimeoutException e) {
                        LOGGER.error("Notification did not appear or disappear in time");
                    }

                });
    }

    public CompareProductsPage openComparePage() {
        LOGGER.info("Opening Compare Products Page...");
        compareProductListLink.click();
        return new CompareProductsPage(driver);
    }

    public void clickFirstAddToCartButton() {
        if (!addToCartButtons.isEmpty()) {
            WebElement firstButton = addToCartButtons.get(1);
            fluentwait.until(ExpectedConditions.elementToBeClickable(firstButton)).click();
            fluentwait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//div[@class='bar-notification success']")));
            fluentwait.until(ExpectedConditions.invisibilityOfElementLocated(
                    By.xpath("//div[@class='bar-notification success']")));
            LOGGER.info("Product from {} has been added to cart.", this.getClass().getSimpleName());

        } else {
            LOGGER.error("Could not find the first button for Add To Cart");

        }
    }


}
