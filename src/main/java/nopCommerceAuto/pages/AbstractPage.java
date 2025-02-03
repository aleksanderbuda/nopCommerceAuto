package nopCommerceAuto.pages;

import nopCommerceAuto.constants.Constants;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public abstract class AbstractPage implements Constants {

    @FindBy(xpath = "//title")
    private WebElement title;

    protected final Logger LOGGER = LogManager.getLogger(this.getClass());
    protected final WebDriver driver;
    protected final String titleText;
    protected String pageUrl;

    public AbstractPage(WebDriver driver, String title, String pageUrl){
        this.driver = driver;
        this.titleText = title;
        this.pageUrl = pageUrl;
    }

    public String getTitleText() {
        return driver.getTitle();
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public void open() {
        LOGGER.info("Opening the {} page", this.getClass().getSimpleName());
        driver.get(pageUrl);
        LOGGER.info("{} has opened succesfully", this.getClass().getSimpleName());
    }

    public boolean isPageOpened() {
        return getTitleText().equals(titleText) && getCurrentUrl().contains(pageUrl);
    }

}
