package nopCommerceAuto.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;
import eventListener.EventListener;

import java.net.MalformedURLException;
import java.net.URL;

public class DriverFactory {

    private DriverFactory() {}

    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    static {
        WebDriverManager.chromedriver().setup();
        WebDriverManager.edgedriver().setup();
        WebDriverManager.firefoxdriver().setup();
    }

    public static WebDriver createDriver() throws MalformedURLException {
        String browser = PropertiesReader.getProperty("browser");
        String remoteUrl = PropertiesReader.getProperty("remoteUrl");

        WebDriver rawDriver;

        if (remoteUrl != null) {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setBrowserName(browser);
            rawDriver = new RemoteWebDriver(new URL(remoteUrl), capabilities);
        } else {
            switch (browser) {
                case "chrome" -> rawDriver = new ChromeDriver();
                case "edge" -> rawDriver = new EdgeDriver();
                case "firefox" -> rawDriver = new FirefoxDriver();
                default -> throw new IllegalArgumentException("Unsupported browser: " + browser);
            }
        }

        EventListener listener = new EventListener();
        WebDriver decoratedDriver = new EventFiringDecorator<>(listener).decorate(rawDriver);

        driver.set(decoratedDriver);
        return decoratedDriver;
    }

    public static WebDriver getDriver() {
        if (driver.get() == null) {
            throw new IllegalStateException("WebDriver not initialized. Call createDriver() first.");
        }
        return driver.get();
    }

    public static void quitDriver() {
        try {
            if (driver.get() != null) {
                driver.get().quit();
            }
        } catch (Exception e) {
            System.err.println("Failed to quit driver: " + e.getMessage());
        } finally {
            driver.remove();
        }
    }
}


