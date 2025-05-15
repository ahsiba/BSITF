package org.cardinality.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Call ConfigReader.init(...) exactly once, from your BaseTest or a @BeforeSuite.
 * After that, ConfigReader.get(key) will return values from whichever file you passed in.
 */
public class ConfigReader {
    private static final Properties props = new Properties();

    private ConfigReader() { /* no-op */ }

    /**
     * Load the given properties file from the classpath.
     * @param filename e.g. "registration.properties" or "env1.properties"
     */
    public static void init(String filename) {
        try (InputStream in = ConfigReader.class
                .getClassLoader()
                .getResourceAsStream(filename)) {
            if (in == null) {
                throw new RuntimeException(
                    "Could not find properties file on classpath: " + filename);
            }
            props.load(in);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load properties file: " + filename, e);
        }
    }

    /** 
     * Get a property. Will throw if you forgot to init(...) or the key is missing.
     */
    public static String get(String key) {
        String value = props.getProperty(key);
        if (value == null) {
            throw new IllegalStateException(
                "No property '" + key + "' found. "
              + "Did you call ConfigReader.init(...) first?");
        }
        return value;
    }
}