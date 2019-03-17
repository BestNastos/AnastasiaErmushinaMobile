package scenarios;

import io.appium.java_client.AppiumDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.MalformedURLException;

import static setup.Driver.driverSingleton;

@Test(groups = {"web", "native"}) //TODO testng config?
public class Hooks {
    protected AppiumDriver driver;

    /**
     * Required variables will be initialized by inherited constructor
     * @throws IOException
     */
    protected Hooks() throws IOException {//TODO need super()?
//        super();
    }

    @BeforeSuite(description = "Prepare driver to run test(s)", groups ={"web", "native"})
    public void setUp() throws IOException {
        driver = driverSingleton();
        System.out.println("Setup complete");
    }

    @AfterSuite(description = "Close driver on all tests completion", groups ={"web", "native"})
    public void tearDown() throws MalformedURLException {
        driver.quit();
        System.out.println("Teardown complete");
    }
}
