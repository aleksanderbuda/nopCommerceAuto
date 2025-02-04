package nopCommerceAuto.pages;

import lombok.Getter;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.NoSuchElementException;

@Getter
public class CompareProductsPage extends AbstractPage {


    @FindBy(xpath = "//span[@class='close']")
    private WebElement greenBarCloseButton;

    @FindBy(xpath = "//div[@class='no-data']")
    private WebElement comparePageEmptyListText;

    @FindBy(xpath = "//tr[@class='product-price']//td[@style='width:90%']")
    private WebElement compareProductPrice;

    @FindBy(xpath = "//a[@class='picutre']")
    private WebElement singlePicture;

    @FindBy(xpath = "//tr[@class='product-name']//td[@style='width:90%']/a")
    private WebElement productName;

    @FindBy(xpath = "//button[@class='button-2 remove-button']")
    private WebElement singleRemoveButton;


    private final WebDriverWait wait;
    private final Wait<WebDriver> fluentwait;

    public CompareProductsPage(WebDriver driver) {
        super(driver, Urls.COMPARE_PRODUCT_PAGE_URL, PageTitles.COMPARE_PRODUCTS_PAGE_TITLE);
        PageFactory.initElements(driver, this);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        this.fluentwait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class);
    }














}
