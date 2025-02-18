package nopCommerceAuto.pages;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

@Getter
public class CartPage extends AbstractPage {

    public CartPage(WebDriver driver) {
        super(driver, PageTitles.CART_PAGE_TITLE, Urls.CART_PAGE_URL);
        PageFactory.initElements(driver, this);
    }
}
