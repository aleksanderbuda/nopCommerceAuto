package nopCommerceAuto.pages;

import lombok.Getter;
import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
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

    @FindBy(xpath = "//div[@id=\"nivo-slider\"]")
    private WebElement carousel;

    @FindBy(xpath = "(//ul[@class='sublist first-level']//a)[2]")
    private WebElement notebooksLink;

    @FindBy(xpath = "//a[@href='/computers']")
    private WebElement computersNavBar;

    private final WebDriverWait wait;
    private final Wait<WebDriver> fluentwait;
    private final Actions actions;

    public HomePage(WebDriver driver) {
        super(driver, PageTitles.HOME_PAGE_TITLE, Urls.HOME_PAGE_URL);
        PageFactory.initElements(driver, this);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        this.actions = new Actions(driver, Duration.ofSeconds(5));
        this.fluentwait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofMillis(1000))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class);
    }

    public void waitForCarouselInitialization() {
        fluentwait.until(driver -> !sliderImageLinks.isEmpty() &&
                sliderImageLinks.stream()
                        .anyMatch(link -> "block".equals(link.getCssValue("display"))));
    }

    public String getActiveImageUrl() {
        try {
            WebElement activeSliderLink = fluentwait.until(driver ->
                    sliderImageLinks.stream()
                            .filter(link -> "block".equals(link.getCssValue("display")))
                            .findFirst()
                            .orElseThrow(() -> new RuntimeException("No active slider link found"))
            );
            WebElement img = activeSliderLink.findElement(By.tagName("img"));
            String src = img.getAttribute("src");
            LOGGER.info("Active slider image src detected: {}", src);
            return URLDecoder.decode(src, StandardCharsets.UTF_8);
        } catch (Exception e) {
            LOGGER.error("Error finding active slider image: {}", e.getMessage());
            throw new RuntimeException("Unable to find active slider image", e);
        }
    }

    public boolean waitForCarouselImage(String expectedImageUrl) {
        try {
            return fluentwait.until(driver -> {
                String currentSrc = getActiveImageUrl();
                LOGGER.info("Waiting for slider image to change. Expected: {}, Current {}",
                        expectedImageUrl, currentSrc);
                return currentSrc.trim().equalsIgnoreCase(expectedImageUrl.trim());
            });
        } catch (TimeoutException e) {
            String currentImage = URLDecoder.decode(getActiveImageUrl(), StandardCharsets.UTF_8);
            LOGGER.error("Timeout waiting for image to change. Expected: {}, Current {}",
                    expectedImageUrl, currentImage);
            return false;
        } catch (Exception e) {
            LOGGER.error("Error waiting for slider image: {}",  e.getMessage());
            return false;
        }
    }

    public void switchToNextImage() {
        LOGGER.info("Switching to the next slider image");
        new WebDriverWait(driver, Duration.ofMillis(1500));
        wait.until(ExpectedConditions.elementToBeClickable(inactiveSliderButton)).click();
    }

    public NotebooksPage openNotebooksPage() {
        LOGGER.info("Opening the Notebooks Page...");
        actions.moveToElement(computersNavBar).perform();
        notebooksLink.click();
        return new NotebooksPage(driver);
    }
}
