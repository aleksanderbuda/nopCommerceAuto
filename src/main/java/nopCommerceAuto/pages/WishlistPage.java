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


    public WishlistPage(WebDriver driver) {
        super(driver, PageTitles.WISHLIST_PAGE_TITLE, Urls.WISHLIST_PAGE_URL);
        PageFactory.initElements(driver, this);
    }

    public String getSharingUrl() {
        return shareLink.getText();
    }

}
