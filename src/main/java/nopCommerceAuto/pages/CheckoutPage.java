package nopCommerceAuto.pages;

import lombok.Getter;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Random;

@Getter
public class CheckoutPage extends AbstractPage {

    @FindBy(id = "BillingNewAddress_CountryId")
    private WebElement countryDropdown;

    @FindBy(id = "BillingNewAddress_City")
    private WebElement cityField;

    @FindBy(id = "BillingNewAddress_Address1")
    private WebElement addressLineOneField;

    @FindBy(id = "BillingNewAddress_ZipPostalCode")
    private WebElement zipCodeField;

    @FindBy(id = "BillingNewAddress_PhoneNumber")
    private WebElement phoneNumberField;

    @FindBy(xpath = "//button[@type='button']/following-sibling::button[1]")
    private WebElement continueButton;

    @FindBy(id = "billing-address-select")
    private WebElement savedBillingAddressInfo;

    private final WebDriverWait wait;
    private final Wait<WebDriver> fluentwait;
    private final Actions actions;

    public CheckoutPage(WebDriver driver) {
        super(driver, PageTitles.CHECKOUT_PAGE_TITLE, Urls.CHECKOUT_PAGE_URL);
        PageFactory.initElements(driver, this);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        this.actions = new Actions(driver, Duration.ofSeconds(5));
        this.fluentwait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofMillis(1000))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class);
    }

    public String selectRandomCountry() {
        LOGGER.info("Selecting a country...");
        countryDropdown.click();

        Select select = new Select(countryDropdown);
        List<WebElement> options = select.getOptions();
        int randomIndex = new Random().nextInt(options.size() - 1) + 1;
        WebElement randomOption = options.get(randomIndex);

        String selectedCountry = randomOption.getText();
        select.selectByVisibleText(randomOption.getText());
        return selectedCountry;
    }

    public String getSavedBillingAddressInfo() {
        savedBillingAddressInfo.click();
        Select select = new Select(savedBillingAddressInfo);
        List<WebElement> options = select.getOptions();
        return options.getFirst().getText();
    }

    public void fillCity(String city) {
        getCityField().sendKeys(city);
    }

    public void fillAddress(String address) {
        getAddressLineOneField().sendKeys(address);
    }

    public void fillZipCode(String zipcode) {
        getZipCodeField().sendKeys(zipcode);
    }

    public void fillPhoneNumber(String phone) {
        getPhoneNumberField().sendKeys(phone);
    }

    public void clickContinueButton() {
        try {
            continueButton.click();
            LOGGER.info("Continue button clicked successfully.");
        } catch (Exception e) {
            LOGGER.error("Failed to click Continue button: " + e.getMessage());
            throw e; //
        }
    }




}
