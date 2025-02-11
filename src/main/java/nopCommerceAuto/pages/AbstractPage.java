package nopCommerceAuto.pages;
import lombok.Getter;
import nopCommerceAuto.model.ProductInfo;

import nopCommerceAuto.constants.Constants;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.*;
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
public abstract class AbstractPage implements Constants {

    @FindBy(xpath = "(//h1) | (//title)")
    private WebElement title;

    protected final Logger LOGGER = LogManager.getLogger(this.getClass());
    protected final WebDriver driver;
    protected final String titleText;
    protected String pageUrl;
    protected final WebDriverWait wait;
    protected final Wait<WebDriver> fluentwait;

    public AbstractPage(WebDriver driver, String title, String pageUrl){
        this.driver = driver;
        this.titleText = title;
        this.pageUrl = pageUrl;
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
}

