package nopCommerceAuto.pages;
import lombok.Getter;

import nopCommerceAuto.constants.Constants;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.NoSuchElementException;

@Getter
public class AbstractPage implements Constants {

    @FindBy(xpath = "(//h1) | (//title)")
    private WebElement title;

    @FindBy(className = "cart-label")
    private WebElement cartButton;

    @FindBy(xpath = "//button[@class='button-1 cart-button']")
    private WebElement flyoutGoToCartButton;

    @FindBy(linkText = "Wishlist")
    private WebElement wishlistLink;

    @FindBy(className = "product-name")
    private WebElement productName;

    @FindBy(className = "product-unit-price")
    private WebElement unitPrice;

    @FindBy(linkText = "Recently viewed products")
    private WebElement recentlyViewedLink;

    @FindBy(xpath = "(//ul[@class='sublist first-level']//a)[2]")
    private WebElement notebooksLink;

    @FindBy(xpath = "//a[@href='/computers']")
    private WebElement computersNavBar;

    protected final Logger LOGGER = LogManager.getLogger(this.getClass());
    protected final WebDriver driver;
    protected final String titleText;
    protected final String pageUrl;
    protected final WebDriverWait wait;
    protected final Wait<WebDriver> fluentwait;
    protected final Actions actions;

    public AbstractPage(WebDriver driver, String title, String pageUrl){
        this.driver = driver;
        this.titleText = title;
        this.pageUrl = pageUrl;
        this.actions = new Actions(driver, Duration.ofSeconds(5));
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        this.fluentwait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class);
    }

    public String getTitleText() {
        return driver.getTitle();
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public void open() {
        driver.get(pageUrl);
    }

    public boolean isPageOpened() {
        return getTitleText().equals(titleText) && getCurrentUrl().contains(pageUrl);
    }

    public CartPage openCartPage() {
        actions.moveToElement(cartButton).perform();
        flyoutGoToCartButton.click();
        return new CartPage(driver);
    }

    public WishlistPage openWishlistPage() {
        wishlistLink.click();
        return new WishlistPage(driver);
    }

    public RecentlyViewedPage openRecentlyViewed() {
        recentlyViewedLink.click();
        return new RecentlyViewedPage(driver);
    }

    public NotebooksPage openNotebooksPage() {
        actions.moveToElement(computersNavBar).perform();
        fluentwait.until(ExpectedConditions.visibilityOf(notebooksLink)).click();
        return new NotebooksPage(driver);
    }

    public String getProductName() {
        try {
            String name = productName.getText();
            LOGGER.info("Retrieved product name: " + name);
            return name;
        } catch (Exception e) {
            LOGGER.error("Could not find Product name");
            throw new RuntimeException("Failed to retrieve the product name", e);
        }
    }

    public String getProductPrice() {
        try {
            String price = unitPrice.getText();
            LOGGER.info("Retrieved unit price: " + price);
            return price;
        } catch (Exception e) {
            LOGGER.error("Could not find Product price");
            throw new RuntimeException("Failed to retrieve product price", e);
        }
    }
}

