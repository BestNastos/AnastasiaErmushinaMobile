package setup;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import static io.appium.java_client.remote.AndroidMobileCapabilityType.*;
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
    private static String SERVER_URL;
    private static String ACTIVITY;
    private static String DEVICE_NAME;

    //paths
    private final static File nodeJSPathFile = new File("C:\\Program Files\\nodejs\\node.exe");
    private final static File appiumPathFile = new File("C:\\Program Files\\Appium\\resources\\app\\" +
            "node_modules\\appium\\build\\lib\\main.js");
    private final static String ipLocalhost = "127.0.0.1";
    private final static int port = 4723;
    private final static File logFile = new File("AppiumLog.txt");

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
        SERVER_URL = properties.getPropertyValue(SERVER_KEY);
        BROWSER_TITLE = properties.getPropertyValue(BROWSER_TITLE_KEY);
        APP_PACK = properties.getPropertyValue(APP_PACK_KEY);
        ACTIVITY = properties.getPropertyValue(ACTIVITY_KEY);
        DEVICE_NAME = properties.getPropertyValue(DEVICE_NAME_KEY);
    }

    public static void startServer(){
        AppiumServiceBuilder appiumBuilder = new AppiumServiceBuilder();
        appiumBuilder
                .usingDriverExecutable(nodeJSPathFile)
                .withAppiumJS(appiumPathFile)
                .withIPAddress(ipLocalhost)
                .usingPort(port)
                //if this base path doesn't work, try not setting it or use "/":
                .withArgument(GeneralServerFlag.BASEPATH, "/wd/hub")
                .withLogFile(logFile);

        AppiumDriverLocalService service = AppiumDriverLocalService.buildService(appiumBuilder);
        service.start();
    }

    /**
     * Initializes driver with appropriate capabilities depending on the platform and app type.
     *
     * @throws MalformedURLException If URL needed to instantiate driver is incorrect.
     */
    private static void prepareDriver() throws MalformedURLException {

        startServer();

        DesiredCapabilities capabilities = new DesiredCapabilities();
        String browserName;
//        installApp();

        capabilities.setCapability(PLATFORM_NAME, TEST_PLATFORM);

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

        // Setup type of application:
        if (AUT != null && SUT == null) {
            // Native:
            capabilities.setCapability(APP_PACKAGE, APP_PACK);
            capabilities.setCapability(APP_ACTIVITY, ACTIVITY);
            capabilities.setCapability(MobileCapabilityType.APP, AUT);
        } else if (SUT != null && AUT == null) {
            // Web:
            capabilities.setCapability(BROWSER_NAME, browserName);
        } else {
            throw new IllegalArgumentException("Unknown type of mobile app");
        }

        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, DEVICE_NAME);

        driverSingleton = new AppiumDriver(new URL(SERVER_URL), capabilities);
        // Place breakpoint on next line - some actions will need to be done on devices' UI manually
        waitSingleton = new WebDriverWait(driverSingleton, Duration.ofSeconds(15));
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

//    public static Response stopUsingDevice() {
//        return RestAssured
//                .given()
//                .header("Authorization", "Bearer " + "b01d96e5-debe-4d64-ae8e-ae3fa5a47ce7",
//                        "Content-Type", ContentType.JSON)
//                .delete("http://mobilecloud.epam.com/automation/api/device/" + DEVICE_UDID)
//                .prettyPeek();
//    }
//
//    public static Response installApp() {
//        return RestAssured
//                .given()
//                .header("Authorization", "Bearer " + "b01d96e5-debe-4d64-ae8e-ae3fa5a47ce7")
//                .body("file=@ContactManager.apk")
//                .post("http://mobilecloud.epam.com/automation/api/storage/install/" + DEVICE_UDID)
//                .prettyPeek();
//    }
}

