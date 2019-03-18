package setup;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import static io.appium.java_client.remote.MobileCapabilityType.*;
import static setup.KeysAndOptions.*;

/**
 * This class reads properties, initializes a driver with test properties
 * and a web driver wait object
 */
public class Driver {
    private static AppiumDriver driverSingleton;
    private static WebDriverWait waitSingleton;
    //TODO test properties field?

    // Properties to be read
    private static String AUT;
    public static String SUT;
    private static String TEST_PLATFORM;
    private static String DRIVER;
    private static String DEVICE;

    public static void readProperties(TestProperties properties) throws IOException {
        AUT = properties.getPropertyValue(AUT_KEY);
        SUT = properties.getPropertyValue(SUT_KEY);
        TEST_PLATFORM = properties.getPropertyValue(PLATFORM_KEY);
        DRIVER = properties.getPropertyValue(DRIVER_KEY);
        DEVICE = properties.getPropertyValue(DEVICE_KEY);
    }

    private Driver() {
    }

    /**
     * Initialize driver with appropriate capabilities depending on platform and application
     *
     * @throws IllegalArgumentException, IOException
     */
    public static void prepareDriver() throws IllegalArgumentException, IOException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(PLATFORM_NAME, TEST_PLATFORM);
        String browserName;

        // Setup test platform: Android or iOS. Browser also depends on a platform.
        switch (TEST_PLATFORM) {
            case ANDROID:
                capabilities.setCapability(DEVICE_NAME, DEVICE);
                browserName = CHROME;
                break;
            case iOS:
                browserName = SAFARI;
                break;
            default:
                throw new IllegalArgumentException("Unknown mobile platform");//TODO just exception?
        }

        // Setup type of application: mobile, web (or hybrid)
        if (AUT != null && SUT == null) {
            // Native
            File app = new File(AUT);
            capabilities.setCapability(APP, app.getAbsolutePath());
        } else if (SUT != null && AUT == null) {
            // Web
            capabilities.setCapability(BROWSER_NAME, browserName);
        } else {
            throw new IllegalArgumentException("Unknown type of mobile app");
        }

        driverSingleton = new AppiumDriver(new URL(DRIVER), capabilities);
        waitSingleton = new WebDriverWait(driverSingleton, 10);
    }

/**
 * Method works as a Getter for AppiumDriver driverSingleton field. If it is not initialized,
 * method initializes it prior to returning it.
 **/
    public static AppiumDriver driver() throws IOException {
        if (driverSingleton == null) prepareDriver();
        return driverSingleton;
    }

    /**
     * Works as a Getter for WebDriverWait waitSingleton field. If the field is not
     * initialized, method calls prepareDriver() and initializes AppiumDriver
     * driverSingleton field along with waitSingleton prior to returning waitSingleton.
     **/
    public static WebDriverWait driverWait() throws IOException {
        if (waitSingleton == null) prepareDriver();
        return waitSingleton;
    }
}

