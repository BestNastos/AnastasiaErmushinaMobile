package scenarios;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import setup.TestProperties;

import java.io.IOException;

import static setup.Driver.*;
import static setup.Driver.driverSingleton;

@Test(groups = {"web", "native"}) //TODO testng config?
public class Hooks {
    private TestProperties properties;

    protected Hooks(String path) {
        properties = new TestProperties(path);
    }

    /**
     * Required variables will be initialized by inherited constructor
     *
     * @throws IOException
     */
    //TODO need constructor with super()?
    @BeforeSuite(description = "Prepare driver to run test(s)", groups = {"web", "native"})
    public void setUp() throws IOException {
        properties.loadProperties();
        readProperties(properties);
        System.out.println("Setup complete");
    }

    @AfterSuite(description = "Close driver on all tests completion", groups = {"web", "native"})
    public void tearDown() throws IOException {
        driverSingleton().quit();
        System.out.println("Teardown complete");
    }
}
