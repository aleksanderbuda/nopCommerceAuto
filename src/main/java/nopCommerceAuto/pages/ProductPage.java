package nopCommerceAuto.pages;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Getter
public class ProductPage extends AbstractPage {

    @FindBy(xpath ="//p[@class='content']")
    private WebElement greenBarProductAddedToCompareText;

    @FindBy(xpath = "//p[@class='content']/a[@href='/compareproducts']")
    private WebElement greenBarProductComparisonLink;

    public ProductPage(WebDriver driver) {
        super(driver,  PageTitles.PRODUCT_PAGE_TITLE, Urls.PRODUCT_PAGE_URL);
        PageFactory.initElements(driver, this);


    }
}
