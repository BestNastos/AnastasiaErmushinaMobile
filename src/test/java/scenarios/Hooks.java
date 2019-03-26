package scenarios;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import setup.Driver;
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
     * @throws IOException if path to property file is incorrect in
     *                     {@link TestProperties#loadProperties()} or if
     *                     URL needed to instantiate driver is incorrect
     *                     in {@link Driver#prepareDriver()}
     */
    @Parameters("property path")
    @BeforeSuite(description = "load properties and prepare driver for tests")
    public void setUp(String path) throws IOException {
        setProperties(new TestProperties(path).loadProperties());
        prepareDriver();
        System.out.println("Setup complete");
    }

    /**
     * Closes app instead of calling {@link RemoteWebDriver#quit()} to avoid switching off the device.
     *
     * @throws MalformedURLException if incorrect URL is passed to driver constructor
     *                               in {@link Driver#driver()}.
     */
    @AfterSuite(description = "Close driver after tests")
    public void tearDown() throws MalformedURLException {
        driver().closeApp();
        System.out.println("Teardown complete");
    }
}
