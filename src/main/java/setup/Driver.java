package setup;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import static io.appium.java_client.remote.AndroidMobileCapabilityType.*;
import static io.appium.java_client.remote.MobileCapabilityType.*;
import static setup.KeysAndOptions.*;

/**
 * Incapsulates AppiumDriver and WebDriverWait which act as singletons due to private
 * constructor of this class. Sets properties to use as driver capabilities, initializes
 * an AppiumDriver and a WebDriverWait object. Provides getters for these objects.
 */
public class Driver {
    private static AppiumDriver driverSingleton;
    private static WebDriverWait waitSingleton;

    // Properties for driver capabilities:
    public static String SUT;
    public static String BROWSER_TITLE;
    private static String AUT;
    private static String APP_PACK;
    private static String TEST_PLATFORM;
    private static String DRIVER_URL;
    private static String DEVICE;
    private static String DEVICE_UDID;
    private static String ACTIVITY;

    private Driver() {
    }

    /**
     * Reads properties from TestProperties parameter and assigns them to static fields
     * of this class.
     *
     * @param properties TestProperties that contain data for setting driver capabilities.
     * @throws IOException if path to property file is incorrect.
     * @see TestProperties#getPropertyValue(String key)
     */
    public static void setProperties(TestProperties properties) throws IOException {
        AUT = properties.getPropertyValue(AUT_KEY);
        SUT = properties.getPropertyValue(SUT_KEY);
        TEST_PLATFORM = properties.getPropertyValue(PLATFORM_KEY);
        DRIVER_URL = properties.getPropertyValue(DRIVER_KEY);
        DEVICE = properties.getPropertyValue(DEVICE_KEY);
        BROWSER_TITLE = properties.getPropertyValue(BROWSER_TITLE_KEY);
        APP_PACK = properties.getPropertyValue(APP_PACK_KEY);
        DEVICE_UDID = properties.getPropertyValue(UDID_KEY);
        ACTIVITY = properties.getPropertyValue(ACTIVITY_KEY);
    }

    /**
     * Initializes driver with appropriate capabilities depending on the platform and app type.
     *
     * @throws MalformedURLException If URL needed to instantiate driver is incorrect.
     */
    private static void prepareDriver() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        String browserName;

        // Setup test platform:
        switch (TEST_PLATFORM) {
            case ANDROID:
                browserName = CHROME;
                break;
            case iOS:
                browserName = SAFARI;
                break;
            default:
                throw new IllegalArgumentException("Unknown mobile platform: " + TEST_PLATFORM);
        }
        capabilities.setCapability(PLATFORM_NAME, TEST_PLATFORM);

        // Setup type of application:
        if (AUT != null && SUT == null) {
            // Native:
            capabilities.setCapability(APP_PACKAGE, APP_PACK);
            capabilities.setCapability(APP_ACTIVITY, ACTIVITY);
        } else if (SUT != null && AUT == null) {
            // Web:
            capabilities.setCapability(BROWSER_NAME, browserName);
        } else {
            throw new IllegalArgumentException("Unknown type of mobile app");
        }

        capabilities.setCapability(DEVICE_NAME, DEVICE);
        capabilities.setCapability(UDID, DEVICE_UDID);

        driverSingleton = new AppiumDriver(new URL(DRIVER_URL), capabilities);
        waitSingleton = new WebDriverWait(driverSingleton, 10);
    }

    /**
     * A getter for AppiumDriver field. If it is not initialized, method initializes it.
     *
     * @return initialized driver.
     * @throws MalformedURLException If URL needed to {@link #prepareDriver} is incorrect.
     */
    public static AppiumDriver getDriver() throws MalformedURLException {
        if (driverSingleton == null) prepareDriver();
        return driverSingleton;
    }

    /**
     * A getter for WebDriverWait field. If the field is not initialized, method calls
     * {@link #prepareDriver} and initializes driver along with WebDriverWait object.
     *
     * @return initialized WebDriverWait field.
     * @throws MalformedURLException if URL needed to {@link #prepareDriver} is incorrect.
     */
    public static WebDriverWait getDriverWait() throws MalformedURLException {
        if (waitSingleton == null) prepareDriver();
        return waitSingleton;
    }
}

