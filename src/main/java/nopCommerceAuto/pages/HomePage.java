package nopCommerceAuto.pages;

import lombok.Getter;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.List;

@Getter
public class HomePage extends AbstractPage {

    @FindBy(xpath = "//a[@class=\"nivo-control active\"]")
    private WebElement activeSliderButton;

    @FindBy(xpath = "//a[@class=\"nivo-control\"]")
    private WebElement inactiveSliderButton;

    @FindBy(xpath = "//a[@class='nivo-imageLink' and contains(@style, 'display: block')]")
    private WebElement activeImageLink;

    @FindBy(xpath = "//a[@class='nivo-imageLink' and contains(@style, 'display: none')]")
    private WebElement inactiveImageLink;

    @FindBy(xpath = "//a[contains(@class, 'nivo-imageLink')]")
    private List<WebElement> sliderImageLinks;

    private final WebDriverWait wait;
    private final Wait<WebDriver> fluentwait;

    public HomePage(WebDriver driver) {
        super(driver, PageTitles.HOME_PAGE_TITLE, Urls.HOME_PAGE_URL);
        PageFactory.initElements(driver, this);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        this.fluentwait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class);
    }

    public String getActiveImageUrl() {
        try {
            WebElement activeSliderLink = fluentwait.until(driver -> {
                for (WebElement link : sliderImageLinks) {
                    String display = link.getCssValue("display");
                    if (display.equals("block")) {
                        return link;
                    }}
                return null;
            });
            if (activeSliderLink != null) {
                WebElement img = activeSliderLink.findElement(By.tagName("img"));
                String src = img.getAttribute("src");
                LOGGER.info("Active slider image src detected: " + src);
                return src;
            }
            throw new RuntimeException("No active slider link found");
        } catch (Exception e) {
            LOGGER.error("Error finding active slider image: " + e.getMessage());
            throw new RuntimeException("Unable to find active slider image", e);
        }
    }

    public boolean waitForSliderImage(String expectedImageUrl) {
        try {
            return fluentwait.until(driver -> {
                String currentSrc = getActiveImageUrl();
                String decodedCurrentSrc = URLDecoder.decode(currentSrc, StandardCharsets.UTF_8);
                LOGGER.info("Waiting for slider image to change. Expected: " + expectedImageUrl +
                        ", Current: " + decodedCurrentSrc);
                return decodedCurrentSrc.equals(expectedImageUrl);
            });
        } catch (TimeoutException e) {
            String currentImage = getActiveImageUrl();
            String decodedCurrentImage = URLDecoder.decode(currentImage, StandardCharsets.UTF_8);
            LOGGER.error("Timeout waiting for image to change. Expected: " + expectedImageUrl +
                    ", Current: " + decodedCurrentImage);
            return false;
        } catch (Exception e) {
            LOGGER.error("Error waiting for slider image: " + e.getMessage());
            return false;
        }
    }

    public void switchToNextImage() {
        LOGGER.info("Switching to the next slider image");
        wait.until(ExpectedConditions.elementToBeClickable(inactiveSliderButton)).click();
    }
}
