package nopCommerceAuto.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {

    private DriverFactory() {}

    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    static {
        WebDriverManager.chromedriver().setup();
        WebDriverManager.edgedriver().setup();
        WebDriverManager.firefoxdriver().setup();
    }

    public static WebDriver createDriver() {
        String browser = PropertiesReader.getProperty("browser");

        switch (browser) {
            case "chrome" -> driver.set(new ChromeDriver());
            case "edge" -> driver.set(new EdgeDriver());
            case "firefox" -> driver.set(new FirefoxDriver());
            default -> throw new IllegalArgumentException("Unsupported browser: " + browser);
        }
        return driver.get();
    }

    public static WebDriver getDriver() {
        if (driver.get() == null) {
            throw new IllegalStateException("WebDriver not initialized. Call createDriver() first.");
        }
        return driver.get();
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}


