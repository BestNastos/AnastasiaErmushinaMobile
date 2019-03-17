package setup;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import static io.appium.java_client.remote.MobileCapabilityType.*;

/**
 * Initialize a driver with test properties
 */
public class Driver extends TestProperties { //TODO singleton
    private static AppiumDriver driverSingleton;
    private static WebDriverWait waitSingleton;

    // Properties to be read
    private static String AUT; // (mobile) app under testing //TODO private all of them?
    public static String SUT; // site under testing //TODO enum?
    private static String TEST_PLATFORM;
    private static String DRIVER;

    private static void readProperties() throws IOException {
        TestProperties properties = new TestProperties();
        AUT = properties.getProp("aut");
        String t_sut = properties.getProp("sut");
        SUT = t_sut == null ? null : "http://" + t_sut;
        TEST_PLATFORM = properties.getProp("platform");
        DRIVER = properties.getProp("driver");
    }

    private Driver() {
    }

    /**
     * Initialize driver with appropriate capabilities depending on platform and application
     *
     * @throws IllegalArgumentException, MalformedURLException
     */
    private static void prepareDriver() throws IllegalArgumentException, IOException {
        readProperties();
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(PLATFORM_NAME, TEST_PLATFORM);
        String browserName;

        // Setup test platform: Android or iOS. Browser also depends on a platform.
        switch (TEST_PLATFORM) {
            case "Android":
                capabilities.setCapability(DEVICE_NAME, "5200b9bffeb8453b"); //TODO to props?
                browserName = "Chrome";
                break;
            case "iOS":
                browserName = "Safari";
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
            throw new IllegalArgumentException("Unknown type of mobile app");//TODO change exception?
        }

        driverSingleton = new AppiumDriver(new URL(DRIVER), capabilities); //TODO put 'if' back?
        waitSingleton = new WebDriverWait(driverSingleton, 10); //TODO put 'if' back?
    }

    public static AppiumDriver driverSingleton() throws IOException {
        if (driverSingleton == null) prepareDriver();
        return driverSingleton;
    }

    public static WebDriverWait driverWait() throws IOException {
        if (waitSingleton == null) prepareDriver();
        return waitSingleton;
    }
}
//TODO slide 31
//TODO more docs

