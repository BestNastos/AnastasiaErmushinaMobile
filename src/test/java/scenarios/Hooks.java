package scenarios;

import io.appium.java_client.AppiumDriver;
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
 * Loads and reads properties to prepare driver for test(s). Closes driver after test(s).
 * Path to .properties file is passed to {@link #setUp} via XML config file.
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
    @BeforeSuite(description = "Load properties and prepare driver for tests.")
    public void setUp(String path) throws IOException {
        setProperties(new TestProperties(path).loadProperties());
        driver = getDriver();
        wait = getDriverWait();
        System.out.println("Setup complete");
    }

    /**
     * Finishes work with app.
     */
    @AfterSuite(description = "Close driver after tests.")
    public void tearDown() {
        // NOTE: Method closeApp() doesn't release the resources, but it also doesn't cause device
        // to shut down. If you are done working with the device, call driver.quit() instead.
        driver.closeApp();
        System.out.println("Teardown complete.");
    }
}
