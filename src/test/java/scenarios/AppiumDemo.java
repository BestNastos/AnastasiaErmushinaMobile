package scenarios;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.FluentWait;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import static io.appium.java_client.remote.AndroidMobileCapabilityType.APP_ACTIVITY;
import static io.appium.java_client.remote.AndroidMobileCapabilityType.APP_PACKAGE;
import static io.appium.java_client.remote.MobileCapabilityType.*;
import static java.lang.String.format;
import static java.time.Duration.ofMinutes;
import static java.time.Duration.ofSeconds;
import static java.util.Optional.ofNullable;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.openqa.selenium.By.className;


public class AppiumDemo {

    private static final String PROJECT_NAME = "EPM-TSTF";

    private static final String API_KEY = "b01d96e5-debe-4d64-ae8e-ae3fa5a47ce7";
    private static final String APPIUM_HUB = "mobilecloud.epam.com:8080";
    private static final String PLATFORM_NAME = "Android";
    private static final String PLATFORM_VERSION = "8.1.0";
    private static final String BROWSER_NAME = "chrome";
    private static final String DEVICE_NAME = "HMD GLOBAL  1";

    private final DesiredCapabilities capabilities;

    private AppiumDriver driver = null;

    public AppiumDemo() {
        capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", PLATFORM_NAME);
        capabilities.setCapability("platformVersion", PLATFORM_VERSION);
//        capabilities.setCapability("browserName", BROWSER_NAME);
        capabilities.setCapability("deviceName", DEVICE_NAME);
        capabilities.setCapability(APP_PACKAGE, "com.example.android.contactmanager");
        capabilities.setCapability(APP_ACTIVITY, "com.example.android.contactmanager.ContactManager");
        capabilities.setCapability(APP, new File("./src/main/resources/ContactManager.apk").getAbsolutePath());//TODO unnecessary

    }

    @Before
    public void before() throws MalformedURLException {
        driver = new AndroidDriver(
                new URL(format("http://%s:%s@%s/wd/hub", PROJECT_NAME, API_KEY, APPIUM_HUB)), capabilities);

        // For devices with low performance
        driver.manage().timeouts()
                .pageLoadTimeout(5, TimeUnit.MINUTES)
                .implicitlyWait(90, TimeUnit.SECONDS);
    }

    @Test
    public void demoTest() throws InterruptedException {
        final String epamUrl = "https://www.epam.com/";

//        assertTrue(format("Focus is not on '%s'", BROWSER_NAME), driver.isBrowser());

//        driver.get(epamUrl);
        Thread.sleep(5000);

        new FluentWait<>(driver).withMessage("Page was not loaded")
                .pollingEvery(ofSeconds(1))
                .withTimeout(ofMinutes(1))
                .until(driver -> driver.findElements(className("header__logo")).size() > 0);

//        assertEquals("Current url is incorrect", epamUrl, driver.getCurrentUrl());
//        assertEquals("Page title is incorrect", "EPAM | Software Product Development Services", driver.getTitle());
    }

    @After
    public void after() {
//        ofNullable(driver).ifPresent(RemoteWebDriver::quit);
    }
}
