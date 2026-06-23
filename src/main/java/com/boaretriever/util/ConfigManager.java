package com.boaretriever.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

/**
 * Singleton class for managing application properties.
 * Properties are loaded once at application startup and can be accessed from any class.
 */
public class ConfigManager {
    private static ConfigManager instance;
    private Properties properties;

    private ConfigManager() throws IOException {
        properties = new Properties();
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        InputStream stream = loader.getResourceAsStream("application.properties");
        if (stream == null) {
            throw new IOException("application.properties not found in classpath");
        }
        try (InputStreamReader reader = new InputStreamReader(stream, StandardCharsets.UTF_8)) {
            properties.load(reader);
        }
    }

    /**
     * Gets the singleton instance. Initialize only once.
     * @return ConfigManager instance
     * @throws IOException if properties file cannot be loaded
     */
    public static ConfigManager getInstance() throws IOException {
        if (instance == null) {
            synchronized (ConfigManager.class) {
                if (instance == null) {
                    instance = new ConfigManager();
                }
            }
        }
        return instance;
    }

    /**
     * Gets a property value by key
     * @param key property key
     * @return property value or null if not found
     */
    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    /**
     * Gets a property value with a default fallback
     * @param key property key
     * @param defaultValue default value if key not found
     * @return property value or defaultValue
     */
    public String getProperty(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }
}
