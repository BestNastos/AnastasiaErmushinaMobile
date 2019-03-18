package scenarios.webTests;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;
import scenarios.Hooks;

import static org.testng.Assert.*;
import static setup.Driver.*;
import static setup.Driver.SUT;
import static setup.PropertyFile.WEB;

@Test(groups = "web")
public class SimpleWebTests extends Hooks {

    SimpleWebTests() {
        super(WEB.toString());
    }

    @Test(description = "Open website and assert it is opened")
    public void webTest() throws Exception {

        // 1. Open Website
        driver().get(SUT);

        // 2. Assert the page is loaded
        driverWait().until(ExpectedConditions.urlToBe(SUT + "/"));

        // 3. Assert the browser title is correct
        assertEquals(driver().getTitle(), "Internet Assigned Numbers Authority");

        System.out.println("Site opening done");

        //TODO what is default timeout?
        //TODO status check
        //TODO regex
    }
}
