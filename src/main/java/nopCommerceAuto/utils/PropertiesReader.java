package nopCommerceAuto.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesReader {
    protected static final Logger LOGGER = LogManager.getLogger(PropertiesReader.class);
    private static Properties properties;

    static {
        try {
            properties = new Properties();
            FileInputStream input = new FileInputStream("src/main/resources/config.properties");
            properties.load(input);
            input.close();
        } catch (IOException e) {
            LOGGER.warn("Error loading properties file", e);
        }
    }

    public static String getProperty(String key) {
        return properties.get(key).toString().toLowerCase();
    }
}
