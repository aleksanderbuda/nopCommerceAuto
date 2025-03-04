package nopCommerceAuto.pages;
import lombok.Getter;
import nopCommerceAuto.model.ProductInfo;

import nopCommerceAuto.constants.Constants;
import org.apache.commons.lang3.RandomStringUtils;
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
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.stream.Collectors;

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
        LOGGER.info("Opening the {} page", this.getClass().getSimpleName());
        driver.get(pageUrl);
        LOGGER.info("{} has opened succesfully", this.getClass().getSimpleName());
    }

    public boolean isPageOpened() {
        return getTitleText().equals(titleText) && getCurrentUrl().contains(pageUrl);
    }

    public CartPage openCartPage() {
        LOGGER.info("Opening the Cart Page...");
        actions.moveToElement(cartButton).perform();
        flyoutGoToCartButton.click();
        return new CartPage(driver);
    }

    public WishlistPage openWishlistPage() {
        LOGGER.info("Opening the Wishlist Page...");
        wishlistLink.click();
        return new WishlistPage(driver);
    }
}

