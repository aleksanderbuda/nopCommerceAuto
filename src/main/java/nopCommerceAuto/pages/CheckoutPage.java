package nopCommerceAuto.pages;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

@Getter
public class CheckoutPage extends AbstractPage {

    public CheckoutPage(WebDriver driver) {
        super(driver, PageTitles.CHECKOUT_PAGE_TITLE, Urls.CHECKOUT_PAGE_URL);
        PageFactory.initElements(driver, this);

    }
}
