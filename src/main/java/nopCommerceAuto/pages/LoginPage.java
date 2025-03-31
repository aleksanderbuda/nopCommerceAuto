package nopCommerceAuto.pages;

import lombok.Getter;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@Getter
public class LoginPage extends AbstractPage {

    @FindBy(id = "Email")
    private WebElement emailField;

    @FindBy(id = "Email-error")
    private WebElement emailFieldError;

    @FindBy(id = "Password")
    private WebElement passwordField;

    @FindBy(id = "Email-error")
    private WebElement passwordFieldError;

    @FindBy(xpath = "//button[@class='button-1 login-button']")
    private WebElement logInButton;

    @FindBy(xpath = "//div[@class='message-error validation-summary-errors']")
    private WebElement incorrectCredentialsError;

    private final WebDriverWait wait;
    private final Wait<WebDriver> fluentwait;
    private final Actions actions;

    public LoginPage(WebDriver driver) {
        super(driver, PageTitles.LOGIN_PAGE_TITLE, Urls.LOGIN_PAGE_URL);
        PageFactory.initElements(driver, this);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        this.actions = new Actions(driver, Duration.ofSeconds(5));
        this.fluentwait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofMillis(1000))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class);
    }

    public void fillEmailField(String email) {
        wait.until(ExpectedConditions.visibilityOf(emailField)).sendKeys(email);
        LOGGER.info("Entering the email: " + email);
    }

    public void fillPasswordField(String password) {
        wait.until(ExpectedConditions.visibilityOf(passwordField)).sendKeys(password);
        LOGGER.info("Entering the password: " + password);
    }

    public void clickLogInButton() {
        logInButton.click();
    }

    public boolean isIncorrectCredentialsErrorVisible() {
        return wait.until(ExpectedConditions.visibilityOf(incorrectCredentialsError)).isDisplayed();
    }

    public boolean isEmailFieldErrorVisible() {
        return fluentwait.until(ExpectedConditions.visibilityOf(emailFieldError)).isDisplayed();
    }
}
