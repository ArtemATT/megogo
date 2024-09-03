package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigLoader {

    private static final String PROPERTIES_FILE = "env.properties";

    private final Properties properties;

    private ConfigLoader() {
        properties = new Properties();
        loadProperties();
    }

    private static class InstanceHolder {
        private static final ConfigLoader INSTANCE = new ConfigLoader();
    }

    public static String getBaseUrl() {
        return getInstance().getProperty("base.url");
    }

    public static ConfigLoader getInstance() {
        return InstanceHolder.INSTANCE;
    }

    private void loadProperties() {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream(PROPERTIES_FILE)) {
            if (input == null) {
                System.out.printf("Sorry, unable to find %s%n", PROPERTIES_FILE);
                return;
            }

            properties.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private String getProperty(String key) {
        return properties.getProperty(key);
    }
}
