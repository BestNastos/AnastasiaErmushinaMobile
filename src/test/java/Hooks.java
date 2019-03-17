import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import setup.Driver;

import java.io.IOException;
import java.net.MalformedURLException;

@Test(groups = {"web", "native"}) //TODO testng config?
public class Hooks extends Driver {
    /**
     * Required variables will be initialized by inherited constructor
     * @throws IOException
     */
    protected Hooks() throws IOException {//TODO need super()?
//        super();
    }

    @BeforeSuite(description = "Prepare driver to run test(s)", groups ={"web", "native"})
    public void setUp() throws MalformedURLException {
        prepareDriver();
        System.out.println("Setup complete");
    }

    @AfterSuite(description = "Close driver on all tests completion", groups ={"web", "native"})
    public void tearDown() throws MalformedURLException {
        driverSingleton().quit();
        System.out.println("Teardown complete");
    }
}
