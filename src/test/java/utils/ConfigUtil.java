package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public abstract class ConfigUtil {

    public static void loadProperties() {
        Properties configProps = new Properties();
        try {
            configProps.load(new FileInputStream("src/test/resources/config.properties"));
            configProps.putAll(System.getProperties());

            //load configuration based on env
            configProps.load(new FileInputStream(String.format("src/test/resources/configs/%s.properties",configProps.getProperty("env"))));
            System.setProperties(configProps);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
