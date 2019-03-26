package setup;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Incapsulates Properties collection, loads in it properties from a file and
 * retrieves property values by keys.
 */

public class TestProperties {
    private Properties currentProps = new Properties();
    private String propertyPath;

    public TestProperties(String path) {
        this.propertyPath = path;
    }

    /**
     * Loads properties from file into Properties variable.
     *
     * @return this object.
     * @throws IOException if path to property file is incorrect.
     */
    public TestProperties loadProperties() throws IOException {
        FileInputStream in = new FileInputStream(propertyPath);
        currentProps.load(in);
        in.close();
        return this;
    }

    /**
     * Returns property value by key. If key is missing, loads properties from file beforehand.
     *
     * @param key the key of the value to return.
     * @return the value corresponding to the key.
     * @throws IOException if path to property file in {@link #loadProperties()} is incorrect.
     */
    String getPropertyValue(String key) throws IOException {
        if (!currentProps.containsKey(key)) loadProperties();
        return currentProps.getProperty(key);
    }
}
