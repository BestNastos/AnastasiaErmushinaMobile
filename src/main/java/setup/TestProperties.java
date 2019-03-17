package setup;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static setup.PropertyFile.*;

public class TestProperties {

    Properties currentProps = new Properties();
    String propertyFileName = "";
    //TODO - test has driver, driver has this string and passes it to getCurrentProps()

    Properties getCurrentProps(/* TODO propertyFileName */) throws IOException {
        FileInputStream in = new FileInputStream(WEB.toString());
        currentProps.load(in);
        in.close();
        return currentProps;
    }
    protected String getProp(String propKey) throws IOException {
        if(!currentProps.containsKey(propKey)) currentProps = getCurrentProps();
        // "null" as second parameter (defaultValue) seems redundant:
        return currentProps.getProperty(propKey);
    }
}
