package nopCommerceAuto.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;
import eventListener.EventListener;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class DriverFactory {

    private static final Logger LOGGER = LogManager.getLogger();

    private DriverFactory() {}

    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private static boolean runInHeadlessMode = false;

    static {
        WebDriverManager.chromedriver().setup();
        WebDriverManager.edgedriver().setup();
        WebDriverManager.firefoxdriver().setup();
    }

    private static boolean isSeleniumServerRunning(String urlString) {
        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(2000);
            connection.connect();
            return connection.getResponseCode() == 200;
        } catch (Exception e) {
            LOGGER.info("Selenium server not available at " + urlString + ". Using local driver.");
            return false;
        }
    }

    private static MutableCapabilities getBrowserOptions(String browser) {
        switch (browser.toLowerCase()) {
            case "chrome": {
                ChromeOptions options = new ChromeOptions();
                if (runInHeadlessMode) {
                    options.addArguments("--headless=new");
                    LOGGER.info("Chrome options: headless enabled");
                } else {
                    LOGGER.info("Chrome options: normal enabled");
                }
                return options;
            }
            case "edge": {
                EdgeOptions options = new EdgeOptions();
                if (runInHeadlessMode) {
                    options.addArguments("--headless=new");
                    LOGGER.info("Edge options: headless enabled");
                } else {
                    LOGGER.info("Edge options: normal enabled");
                }
                return options;
            }
            case "firefox": {
                FirefoxOptions options = new FirefoxOptions();
                if (runInHeadlessMode) {
                    options.addArguments("-headless");
                    LOGGER.info("Firefox options: headless enabled");
                } else {
                    LOGGER.info("Firefox options: normal enabled");
                }
                return options;
            }
            default:
                throw new IllegalArgumentException("Unsupported browser: " + browser);
        }
    }

    public static WebDriver createDriver() throws MalformedURLException {
        String browser = PropertiesReader.getProperty("browser");
        String remoteUrl = PropertiesReader.getProperty("remoteUrl");
        String headlessProperty = PropertiesReader.getProperty("headless");

        runInHeadlessMode = Boolean.parseBoolean(headlessProperty);
        LOGGER.info(("Headless mode from properties: " + runInHeadlessMode));

        boolean useRemote = runInHeadlessMode && !remoteUrl.trim().isEmpty() && isSeleniumServerRunning(remoteUrl);
        MutableCapabilities options = getBrowserOptions(browser);

        WebDriver rawDriver;
        if (useRemote) {
            LOGGER.info("Using remote WebDriver at: " + remoteUrl);
            rawDriver = new RemoteWebDriver(new URL(remoteUrl), options);
        } else {
            System.out.println("Using local WebDriver for: " + browser);
            switch (browser.toLowerCase()) {
                case "chrome" -> rawDriver = new ChromeDriver((ChromeOptions) options);
                case "edge" -> rawDriver = new EdgeDriver((EdgeOptions) options);
                case "firefox" -> rawDriver = new FirefoxDriver((FirefoxOptions) options);
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
            LOGGER.error("Failed to quit driver: " + e.getMessage());
        } finally {
            driver.remove();
        }
    }
}
