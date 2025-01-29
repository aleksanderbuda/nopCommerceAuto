package nopCommerceAuto.pages;

import nopCommerceAuto.constants.Constants;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.WebDriver;

public class AbstractPage implements Constants {

    protected final Logger LOGGER = LogManager.getLogger(this.getClass());

    protected final WebDriver driver;
    protected final String titleText;

    public AbstractPage(WebDriver driver, String title){
        this.driver = driver;
        this.titleText = title;
    }

}
