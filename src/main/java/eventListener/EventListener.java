package eventListener;

import org.openqa.selenium.*;
import org.openqa.selenium.support.events.WebDriverListener;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.*;
import java.util.stream.Stream;

public class EventListener implements WebDriverListener {
    private static final Logger LOGGER = Logger.getLogger(EventListener.class.getName());
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    static {
        configureLoggerFormat();
    }

    private static void configureLoggerFormat() {
        LOGGER.setUseParentHandlers(false);
        ConsoleHandler handler = new ConsoleHandler();
        handler.setFormatter(new SimpleFormatter() {
            @Override
            public String format(LogRecord record) {
                return String.format("%s [%s] %s : %s%n",
                        DATE_FORMAT.format(new Date(record.getMillis())),
                        Thread.currentThread().getName(),
                        record.getLevel(),
                        record.getMessage());
            }
        });
        LOGGER.addHandler(handler);
    }


    @Override
    public void beforeGet(WebDriver driver, String url) {
        LOGGER.info("Navigating to: " + url);
    }

    @Override
    public void afterGet(WebDriver driver, String url) {
        LOGGER.info("Page opened: " + url);
    }

    @Override
    public void beforeAnyWebElementCall(WebElement element, Method method, Object[] args) {
        String methodName = method.getName();

        if (methodName.equals("click")) {
            LOGGER.info("Attempting click: " + getElementDescription(element));
        }
    }

    @Override
    public void afterAnyWebElementCall(WebElement element, Method method, Object[] args, Object result) {
        String methodName = method.getName();

        if (methodName.equals("isDisplayed")) {
            LOGGER.info("Visibility check - " + getElementDescription(element) + ": " + result);
        }
        if (methodName.equals("click")) {
            LOGGER.info("Successfully clicked: " + getElementDescription(element));
        }
    }

    private String getElementDescription(WebElement element) {
        try {
            Map<String, String> attributes = getElementAttributes(element);
            String tag = element.getTagName().toLowerCase();

            return Stream.of(
                            attributes.get("id"),
                            attributes.get("name"),
                            attributes.get("aria-label"),
                            element.getText().trim())
                    .filter(s -> !s.isEmpty())
                    .findFirst()
                    .map(id -> String.format("%s '%s'", tag, id))
                    .orElse(tag);
        } catch (StaleElementReferenceException e) {
            return "element";
        }
    }

    private Map<String, String> getElementAttributes(WebElement element) {
        return new LinkedHashMap<String, String>() {{
            put("id", getSafeAttribute(element, "id"));
            put("name", getSafeAttribute(element, "name"));
            put("aria-label", getSafeAttribute(element, "aria-label"));
        }};
    }

    private String getSafeAttribute(WebElement element, String attribute) {
        try {
            String value = element.getAttribute(attribute);
            return value != null ? value : "";
        } catch (StaleElementReferenceException e) {
            return "";
        }
    }
}