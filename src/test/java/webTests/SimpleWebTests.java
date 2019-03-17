package webTests;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;
import setup.Driver;

import java.io.IOException;

@Test(groups = "web")
public class SimpleWebTests extends Driver {

    protected SimpleWebTests() throws IOException {
    }

    @Test(description = "Open website")
    public void webTest() throws Exception {
        driver().get(SUT);
        driverWait().until(ExpectedConditions.urlToBe(SUT+"/"));

        //TODO regex
        //TODO what is default timeout?
        //TODO status check

        System.out.println("Site opening done");
    }
}
