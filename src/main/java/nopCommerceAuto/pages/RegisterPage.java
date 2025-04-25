package nopCommerceAuto.pages;

import lombok.Getter;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@Getter
public class RegisterPage extends AbstractPage {

    @FindBy(id = "gender-male")
    private WebElement genderRadioBtn;

    @FindBy(id = "FirstName")
    private WebElement firstNameField;

    @FindBy(id = "LastName")
    private WebElement lastNameField;

    @FindBy(id = "Email")
    private WebElement emailField;

    @FindBy(id = "Company")
    private WebElement companyField;

    @FindBy(id = "Newsletter")
    private WebElement newsletterCheckbox;

    @FindBy(id = "Password")
    private WebElement passwordField;

    @FindBy(id = "ConfirmPassword")
    private WebElement confirmPasswordField;

    @FindBy(className = "required")
    private WebElement requiredAsterisk;

    @FindBy(id = "register-button")
    private WebElement registerButton;

    @FindBy(className = "result")
    private WebElement resultText;

    @FindBy(xpath = "//a[@class='button-1 register-continue-button']")
    private WebElement continueButton;

    @FindBy(id = "FirstName-error")
    private WebElement firstNameError;

    @FindBy(id = "LastName-error")
    private WebElement lastNameError;

    @FindBy(id = "Email-error")
    private WebElement emailError;

    @FindBy(id = "ConfirmPassword-error")
    private WebElement passwordError;

    private final WebDriverWait wait;

    public RegisterPage(WebDriver driver) {
        super(driver, PageTitles.REGISTER_PAGE_TITLE, Urls.REGISTER_PAGE_URL);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        PageFactory.initElements(driver, this);
    }

    public void fillName(String name) {
        getFirstNameField().sendKeys(name);
    }

    public void fillLastName(String lastName) {
        getLastNameField().sendKeys(lastName);
    }

    public void fillEmail(String email) {
        getEmailField().sendKeys(email);
    }

    public void fillCompanyName(String companyName) {
        getCompanyField().sendKeys(companyName);
    }

    public void fillPassword(String password) {
        getPasswordField().sendKeys(password);
    }

    public void fillConfirmPassword(String confirmPassword) {
        getConfirmPasswordField().sendKeys(confirmPassword);
    }

    public void clickGenderButton() {
        genderRadioBtn.click();
    }

    public void clickRegisterButton() {
        registerButton.click();
    }

    public boolean isRegistrationSuccess() {
        wait.until(ExpectedConditions.visibilityOf(resultText));
        return resultText.getText().equals("Your registration completed");
    }

    public void clickContinueButton() {
        continueButton.click();
    }

    public boolean isFirstNameErrorVisible() {
        wait.until(ExpectedConditions.visibilityOf(firstNameError));
        return firstNameError.getText().equals("First name is required.");
    }

    public boolean isFirstNameErrorNotVisible() {
        try {
            wait.until(ExpectedConditions.invisibilityOf(firstNameError));
            return true;
            } catch (Exception e) {
            return false;
        }
    }

    public boolean isLastNameErrorVisible() {
        wait.until(ExpectedConditions.visibilityOf(lastNameError));
        return lastNameError.getText().equals("Last name is required.");
    }
    public boolean isLastNameErrorNotVisible() {
        try {
            wait.until(ExpectedConditions.invisibilityOf(lastNameError));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isEmailErrorVisible() {
        try {
            wait.until(ExpectedConditions.visibilityOf(emailError));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public boolean isEmailErrorNotVisible() {
        try {
            wait.until(ExpectedConditions.invisibilityOf(emailError));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isPasswordErrorVisible() {
        wait.until(ExpectedConditions.visibilityOf(passwordError));
        return passwordError.getText().equals("Password is required.");
    }

    public boolean isPasswordErrorNotVisible() {
        try {
            wait.until(ExpectedConditions.invisibilityOf(passwordError));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean areRegisterFieldsRequired() {
        try {
            boolean isFirstNameErrorCorrect = isFirstNameErrorVisible();
            boolean isLastNameErrorCorrect = isLastNameErrorVisible();
            boolean isEmailErrorCorrect = isEmailErrorVisible();
            boolean isPasswordErrorCorrect = isPasswordErrorVisible();

            return isFirstNameErrorCorrect && isLastNameErrorCorrect
                    && isEmailErrorCorrect && isPasswordErrorCorrect;

        } catch (TimeoutException e) {
            LOGGER.error("Required fields did not appear in time");
            return false;
        }
    }
}

