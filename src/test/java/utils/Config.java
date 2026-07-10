package utils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

public class Config {

    private static final Properties properties = new Properties();

    static {
        try (InputStream input = Config.class
                .getClassLoader()
                .getResourceAsStream("config.properties")) {

            properties.load(
                    new java.io.InputStreamReader(
                            input,
                            StandardCharsets.UTF_8
                    )
            );

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static String get(String key) {
        return properties.getProperty(key);
    }
}