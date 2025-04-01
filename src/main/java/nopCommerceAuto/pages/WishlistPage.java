package nopCommerceAuto.pages;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Getter
public class WishlistPage extends AbstractPage {

    @FindBy(className = "share-link")
    private WebElement shareLink;

    @FindBy(name = "addtocartbutton")
    private WebElement addToCartButton;

    @FindBy(name = "addtocart")
    private WebElement addToCartCheckbox;

    @FindBy(xpath = "//h1")
    private WebElement tableTitle;


    public WishlistPage(WebDriver driver) {
        super(driver, PageTitles.WISHLIST_PAGE_TITLE, Urls.WISHLIST_PAGE_URL);
        PageFactory.initElements(driver, this);
    }

    public void clickShareableWishlistUrl() {
        try {
            shareLink.click();
        } catch (Exception e) {
            LOGGER.error("Could not click the Shareable wishlist URL", e);
            throw new RuntimeException("Failed to retrieve Shareable Wishlist Link", e);
        }
    }

    public String getTableTitle() {
        return tableTitle.getText();
    }

    public void clickAddToCartCheckbox() {
        addToCartCheckbox.click();
    }

    public String getShareableWishlistUrl () {
        try {
            String url = shareLink.getText();
            return url;
        } catch (Exception e) {
            LOGGER.error("Could not find Wishlist URL", e);
            throw new RuntimeException("Failed to retrieve shareable wishlist URL", e);
        }
    }

    public CartPage clickAddToCartButton() {
        try {
            addToCartButton.click();
        } catch (Exception e) {
            LOGGER.error("Could not click the Add to Cart", e);
            throw new RuntimeException("Failed to retrieve Add to Cart button", e);
        }
        return new CartPage(driver);
    }
}
