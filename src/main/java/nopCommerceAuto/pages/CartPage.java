package nopCommerceAuto.pages;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Getter
public class CartPage extends AbstractPage {

    @FindBy(id = "termsofservice")
    private WebElement tosCheckbox;

    @FindBy(id = "checkout")
    private WebElement checkoutSubmitBtn;

    public CartPage(WebDriver driver) {
        super(driver, PageTitles.CART_PAGE_TITLE, Urls.CART_PAGE_URL);
        PageFactory.initElements(driver, this);
    }

    public void clickTOSCheckbox() {
        try {
            tosCheckbox.click();
            LOGGER.info("Continue button clicked successfully.");
        } catch (Exception e) {
            LOGGER.error("Failed to click Continue button: " + e.getMessage());
            throw e;
        }
    }

        public CheckoutPage openCheckoutPage() {
            LOGGER.info("Opening Checkout page...");
            checkoutSubmitBtn.click();
            return new CheckoutPage(driver);
        }
    }
