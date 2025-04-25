package cucumber.context;

import lombok.Getter;
import lombok.Setter;
import nopCommerceAuto.utils.DriverFactory;
import org.openqa.selenium.WebDriver;

import java.net.MalformedURLException;

@Getter
@Setter
public class TestContext {
    public String firstName;
    public String lastName;
    public String email;
    public String password;

    public WebDriver getDriver() {
        try {
            DriverFactory.getDriver();
        } catch (IllegalStateException e) {
            try {
                DriverFactory.createDriver();
            } catch (MalformedURLException ex) {
                throw new RuntimeException("Failed to create WebDriver", ex);
            }
        }
        return DriverFactory.getDriver();
    }

    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
