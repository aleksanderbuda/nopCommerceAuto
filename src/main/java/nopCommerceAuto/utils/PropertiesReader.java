package nopCommerceAuto.utils;

import lombok.extern.java.Log;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;

@Log
public class PropertiesReader {
    private static Properties properties;

    static {
        try {
            properties = new Properties();
            FileInputStream input = new FileInputStream("src/main/resources/config.properties");
            properties.load(input);
            input.close();
        } catch (IOException e) {
            log.log(Level.WARNING, e.getMessage(), e);
        }
    }

    public static String getProperty(String key) {
        return properties.get(key).toString().toLowerCase();
    }
}
