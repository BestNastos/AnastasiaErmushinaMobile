package scenarios.webTests;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;
import scenarios.Hooks;

import static setup.Driver.*;
import static setup.Driver.SUT;

@Test(groups = "web")
public class SimpleWebTests extends Hooks {

    @Test(description = "Open website")
    public void webTest() throws Exception {
        driver.get(SUT);
        driverWait().until(ExpectedConditions.urlToBe(SUT+"/"));

        //TODO regex
        //TODO what is default timeout?
        //TODO status check

        System.out.println("Site opening done");
    }
}
