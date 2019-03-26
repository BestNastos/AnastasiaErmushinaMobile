package scenarios;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import setup.Driver;
import setup.TestProperties;

import java.io.IOException;

import static setup.Driver.*;

/**
 * Loads and reads properties to prepare driver for tests. Closes driver after test.
 */

@Test(groups = {"web", "native"})
public abstract class Hooks {

    protected AppiumDriver driver;
    protected WebDriverWait wait;

    /**
     * Loads and reads properties to prepare driver and WebDriverWait object for tests.
     *
     * @throws IOException in case of incorrect path to property file in
     *                     {@link TestProperties#loadProperties} or incorrect URL
     *                     for driver initialization in {@link Driver#getDriver}
     *                     or {@link Driver#getDriverWait}
     */
    @Parameters("property path")
    @BeforeSuite(description = "load properties and prepare driver for tests")
    public void setUp(String path) throws IOException {
        setProperties(new TestProperties(path).loadProperties());
        driver = getDriver();
        wait = getDriverWait();
        System.out.println("Setup complete");
    }

    /**
     * Closes app instead of calling {@link RemoteWebDriver#quit} to avoid switching off the device.
     */
    @AfterSuite(description = "Close driver after tests")
    public void tearDown() {
        driver.closeApp();
        System.out.println("Teardown complete");
    }
}
