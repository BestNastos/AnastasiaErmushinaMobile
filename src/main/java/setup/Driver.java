package setup;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import static io.appium.java_client.remote.MobileCapabilityType.*;

/**
 * Initialize a driver with test properties
 */
public class Driver extends TestProperties { //TODO singleton
    protected AppiumDriver driverSingle; //TODO private
    protected DesiredCapabilities capabilities;//TODO private
    protected WebDriverWait waitSingle;//TODO private

    // Properties to be read
    protected static String AUT; // (mobile) app under testing //TODO private all of them?
    protected static String SUT; // site under testing //TODO enum?
    protected static String TEST_PLATFORM;
    protected static String DRIVER;

    // Constructor initializes properties on driver creation
    protected Driver() throws IOException {
        AUT = getProp("aut");
        String t_sut = getProp("sut");
        SUT = t_sut == null ? null : "http://" + t_sut;
        TEST_PLATFORM = getProp("platform");
        DRIVER = getProp("driver");
    }

    /**
     * Initialize driver with appropriate capabilities depending on platform and application
     *
     * @throws IllegalArgumentException, MalformedURLException
     */
    protected void prepareDriver() throws IllegalArgumentException, MalformedURLException {
        capabilities = new DesiredCapabilities();
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

        // Init driver for local Appium server with capabilities have been set
        // TODO why if? it's not a getter, it's 'prepare driver method
        if (driverSingle == null) driverSingle = new AppiumDriver(new URL(DRIVER), capabilities);

        // Set an object to handle timeouts
        // TODO why if? it's not a getter, it's 'prepare driver method, what wait has to do with it?
        if (waitSingle == null) waitSingle = new WebDriverWait(driverSingle, 10);
    }

    protected AppiumDriver driver()throws MalformedURLException {
        if(driverSingle == null) prepareDriver();
        return driverSingle;
    }

    protected WebDriverWait driverWait() { // why throws Exception?
        return waitSingle;
        //TODO is it really going to be initialized by now?
        //TODO call prepare driver method or init here?
    }
}
//TODO slide 31
//TODO more docs

