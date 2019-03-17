package setup;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class TestProperties {

    private Properties currentProps = new Properties();
    private String propertyPath;

    public TestProperties(String path) {
        this.propertyPath = path;
    }

    public Properties loadProperties() throws IOException {
        FileInputStream in = new FileInputStream(propertyPath);
        currentProps.load(in);
        in.close();
        return currentProps;
    }

    String getPropertyValue(String key) throws IOException {
        if (!currentProps.containsKey(key)) currentProps = loadProperties();
        // "null" as second parameter (defaultValue) seems redundant:
        return currentProps.getProperty(key);
    }
}
