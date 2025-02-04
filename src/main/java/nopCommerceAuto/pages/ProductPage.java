package nopCommerceAuto.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductPage extends AbstractPage {

    @FindBy(xpath ="//p[@class='content']")
    private WebElement greenBarProductAddedToCompareText;

    @FindBy(xpath = "//p[@class='content']/a[@href='/compareproducts']")
    private WebElement greenBarProductComparisonLink;

    public ProductPage(WebDriver driver) {
        super(driver, Urls.PRODUCT_PAGE_URL, PageTitles.PRODUCT_PAGE_TITLE);

    }
}
