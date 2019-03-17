package setup;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static setup.PropertyFile.*;

public class TestProperties {

    private Properties currentProps = new Properties();
    private String propertyPath;

//    TestProperties(PropertyFile path){
//        propertyPath = path.toString();
//    }

    Properties getCurrentProps() throws IOException {
        FileInputStream in = new FileInputStream(NATIVE.toString());
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
