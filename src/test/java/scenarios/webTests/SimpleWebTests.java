package scenarios.webTests;

import org.testng.annotations.Test;
import scenarios.Hooks;

import java.net.MalformedURLException;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;
import static org.testng.Assert.*;
import static setup.Driver.*;
import static setup.Driver.SUT;

@Test(groups = "web")
public class SimpleWebTests extends Hooks {

    @Test(description = "Open website and assert it is opened", groups = "web")
    public void webTest() throws MalformedURLException {

        // 1. Open Website
        driver().get(SUT);

        // 2. Assert the page is loaded
        driverWait().until(urlToBe(SUT + "/"));

        // 3. Assert the browser title is correct
        assertEquals(driver().getTitle(), BROWSER_TITLE);

        System.out.println("Web test complete");

        //TODO status check
        //TODO regex
    }
}
