package scenarios;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import setup.TestProperties;

import java.io.IOException;
import java.net.MalformedURLException;

import static setup.Driver.*;

//@Test(groups = {"web", "native"}) //TODO testng config?
public class Hooks {
    private TestProperties properties;

    protected Hooks(String path) {
        properties = new TestProperties(path);
    }

    /**
     * Loads and reads properties to prepare driver for tests.
     *
     * @throws IOException If path to property file in #loadProperties() is incorrect
     *                     or url needed to #prepareDriver() is incorrect.
     */
    @BeforeSuite(description = "load properties and prepare driver for tests", groups = {"web", "native"})
    public void setUp() throws IOException {
        readProperties(properties.loadProperties());
        prepareDriver();
        System.out.println("Setup complete");
    }

    //TODO add docs?
    //TODO exception because we try to init driver. fix?
    @AfterSuite(description = "Close driver after tests", groups = {"web", "native"})
    public void tearDown() throws MalformedURLException {
        driver().quit();
        System.out.println("Teardown complete");
    }
}
