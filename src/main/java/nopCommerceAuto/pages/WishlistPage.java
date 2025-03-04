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

    @FindBy(className = "product-name")
    private WebElement productName;

    @FindBy(className = "product-unit-price")
    private WebElement unitPrice;

    @FindBy(className = "addtocartbutton")
    private WebElement addToCartButton;


    public WishlistPage(WebDriver driver) {
        super(driver, PageTitles.WISHLIST_PAGE_TITLE, Urls.WISHLIST_PAGE_URL);
        PageFactory.initElements(driver, this);
    }

    public void clickShareableWishlistUrl() {
        try {
            LOGGER.info("Clicking on shareable wishlist URL");
            shareLink.click();
        } catch (Exception e) {
            LOGGER.error("Could not click the Shareable wishlist URL", e);
            throw new RuntimeException("Failed to retrieve Shareable Wishlist Link", e);
        }
    }

    public String getShareableWishlistUrl () {
        try {
            String url = shareLink.getText();
            LOGGER.info("Retrieved shareable wishlist URL: " + url);
            return url;
        } catch (Exception e) {
            LOGGER.error("Could not find Wishlist URL", e);
            throw new RuntimeException("Failed to retrieve shareable wishlist URL", e);
        }
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
