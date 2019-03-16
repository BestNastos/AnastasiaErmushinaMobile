package scenarios;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class DriverSetup {

    AndroidDriver driver;


    void prepareNative() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        //capabilities.setCapability("device","Android");
        capabilities.setCapability("deviceName", "5200b9bffeb8453b");
        capabilities.setCapability("platformName", "Android");

        // Copy the application (.apk), which will become AUT, to the specified location,
        // e.g. "resources" folder of the project
        File appDir = new File("C:\\Users\\Анастасия\\IdeaProjects\\" +
                "AnastasiaErmushinaMobile\\src\\main\\resources");
        File app = new File(appDir, "ContactManager.apk");

        capabilities.setCapability("app", app.getAbsolutePath());

        // Init driver for local Appium server with capabilities have been set
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }

        void prepareWeb() throws MalformedURLException {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("deviceName", "5200b9bffeb8453b");
            capabilities.setCapability("platformName", "Android");
            capabilities.setCapability("browserName", "Chrome");
            driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }
}
