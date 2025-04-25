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
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
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

    @FindBy(xpath = "//h2[@class='product-title']/a")
    private List<WebElement> productTitles;

    @FindBy(xpath = "//div[@class='picture']")
    private WebElement pictureImageLink;

    @FindBy(xpath = "//div[@class='product-item']")
    private List<WebElement> productInfo;

    @FindBy(xpath = "//a[@href='/compareproducts']")
    private WebElement compareProductListLink;

    @FindBy(xpath = "//div[@class='buttons']/button[@class='button-2 product-box-add-to-cart-button']")
    private List<WebElement> addToCartButtons;

    @FindBy(xpath = "//button[@class='button-2 add-to-wishlist-button']")
    private List<WebElement> addToWishlistButton;

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
            LOGGER.error("Could not check the 8GB box", e);
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
                    } catch (TimeoutException e) {
                        LOGGER.error("Notification did not appear or disappear in time");
                    }
                });
    }

    public void selectProduct(int productNum) {
        productTitles.stream()
                .skip(productNum)
                .limit(1)
                .findFirst()
                .ifPresentOrElse(button -> {
                    try {
                        fluentwait.until(ExpectedConditions.elementToBeClickable(button)).click();
                        LOGGER.info("Clicked product at index {}.", productNum);
                    } catch (TimeoutException e) {
                        LOGGER.error("Could not click the desired product at index " + productNum, e);
                        throw new RuntimeException("Could not click product at index " + productNum, e);
                    }}, () -> {
                    throw new RuntimeException("Product number " + productNum + " is out of bounds.");
                });
    }

    private String getProductTitle(int productIndex) {
        selectProduct(productIndex);
        ProductPage productPage = new ProductPage(driver);
        String title = productPage.getName();
        LOGGER.info("Collected title: {}", title);
        driver.navigate().back();
        return title;
    }

    // mapowanie listy indeksów na listę tytułów - rozbite na 2 metody żeby nie łamało SRP
    public List<String> getProductTitles(List<Integer> productIndexes) {
        return productIndexes.stream()
                .map(this::getProductTitle)
                .collect(Collectors.toList());
    }

    public CompareProductsPage openComparePage() {
        compareProductListLink.click();
        return new CompareProductsPage(driver);
    }

    public void clickAddToCartButton() {
        if (!addToCartButtons.isEmpty()) {
            WebElement button = addToCartButtons.get(1);
            fluentwait.until(ExpectedConditions.elementToBeClickable(button)).click();
            LOGGER.info("Product from {} has been added to cart.", this.getClass().getSimpleName());
        } else {
            LOGGER.error("Could not find the first button for Add To Cart");

        }
    }

    public void clickFirstWishlistButton() {
        try {
            addToWishlistButton.stream()
            .filter(WebElement::isDisplayed)
                    .skip(1)
                    .findFirst()
                    .ifPresent(WebElement::click);
        } catch (StaleElementReferenceException e) {
            LOGGER.error("Error clicking Wishlist button", e);
            throw e;
        }
    }

    public boolean isSuccessBannerVisible() {
       try {
           fluentwait.until(ExpectedConditions.visibilityOfElementLocated(
                   By.xpath("//div[@class='bar-notification success']")));
           fluentwait.until(ExpectedConditions.invisibilityOfElementLocated(
                   By.xpath("//div[@class='bar-notification success']")));
           LOGGER.info("Success banner appeared and then disappeared");
           return true;
       } catch (Exception e) {
           LOGGER.error("Success message banner is not visible");
       } return false;
    }
}
