package scenarios;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import setup.TestProperties;

import java.io.IOException;
import java.net.MalformedURLException;

import static setup.Driver.*;

/**
 * Loads and reads properties to prepare driver for tests. Closes driver after test.
 */

@Test(groups = {"web", "native"})
public class Hooks {

    /**
     * Loads and reads properties to prepare driver for tests.
     *
     * @throws IOException If path to property file in #loadProperties() is incorrect
     *                     or URL needed to #prepareDriver() is incorrect.
     */
    @Parameters("property path")
    @BeforeSuite(description = "load properties and prepare driver for tests")
    public void setUp(String path) throws IOException {
        setProperties(new TestProperties(path).loadProperties());
        prepareDriver();
        System.out.println("Setup complete");
    }

    /**
     * Closes driver.
     *
     * @throws MalformedURLException if incorrect URL is passed to driver constructor in
     *                               #prepareDriver() method within #driver() method.
     */
    @AfterSuite(description = "Close driver after tests")
    public void tearDown() throws MalformedURLException {
        driver().quit();
        System.out.println("Teardown complete");
    }
}
