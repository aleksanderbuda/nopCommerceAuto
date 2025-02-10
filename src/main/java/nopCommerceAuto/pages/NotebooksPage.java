package nopCommerceAuto.pages;
import nopCommerceAuto.model.ProductInfo;


import lombok.Getter;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.sql.Time;
import java.time.Duration;
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

    @FindBy(xpath = "//div[@class='picture']")
    private WebElement pictureImageLink;

    @FindBy(xpath = "//span[@class='price actual-price']")
    private WebElement productPrice;

    @FindBy(xpath = "//div[@class='product-item']")
    private List<WebElement> productInfo;

    @FindBy(xpath = "//a[@href='/compareproducts']")
    private WebElement compareProductListLink;

    private final WebDriverWait wait;
    private final Wait<WebDriver> fluentwait;

    public NotebooksPage(WebDriver driver) {
        super(driver, PageTitles.NOTEBOOKS_PAGE_TITLE, Urls.NOTEBOOKS_PAGE_URL);
        PageFactory.initElements(driver, this);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        this.fluentwait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class);
    }
    public void click8GbMemoryCheckbox() {
        memoryCheckbox8Gb.click();
    }
    public boolean isCheckbox8GbClicked() {
        try {
        return wait.until(ExpectedConditions.urlToBe(Urls.NOTEBOOKS_8GB_URL));
    } catch (TimeoutException e) {
        } return false;
    }

    public void clickCompareButtonForProducts(int products) {
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
                        LOGGER.info("Products from {} has been added to comparison", this.getClass().getSimpleName());
                    } catch (TimeoutException e) {
                        LOGGER.error("Notification did not appear or disappear in time");
                    }

                });
    }
    public List<ProductInfo> getProductInfo() {
        return productInfo.stream()
                .map(element -> {
                    try {
                        WebElement titleElement = fluentwait.until(ExpectedConditions.visibilityOf(
                                element.findElement(By.xpath(".//h2[@class='product-title']//a"))));
                        WebElement priceElement = fluentwait.until(ExpectedConditions.visibilityOf(
                                element.findElement(By.xpath(".//span[@class='price actual-price']"))));

                        return new ProductInfo(titleElement.getText(), priceElement.getText());

                    } catch (TimeoutException | NoSuchElementException e) {
                        LOGGER.error("Error while extracting product info: " + e.getMessage(), e);
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    public CompareProductsPage openCompareProductPage() {
        compareProductListLink.click();
        CompareProductsPage compareProductsPage = new CompareProductsPage(driver);
        compareProductsPage.isPageOpened();
        return compareProductsPage;
    }



}
