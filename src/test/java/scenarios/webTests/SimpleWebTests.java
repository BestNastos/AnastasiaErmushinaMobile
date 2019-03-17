package scenarios.webTests;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;
import scenarios.Hooks;

import static setup.Driver.*;
import static setup.Driver.SUT;
import static setup.PropertyFile.WEB;

@Test(groups = "web")
public class SimpleWebTests extends Hooks {

    SimpleWebTests(){
        super(WEB.toString());
    }

    @Test(description = "Open website")
    public void webTest() throws Exception {
        driverSingleton().get(SUT);
        driverWait().until(ExpectedConditions.urlToBe(SUT+"/"));

        //TODO regex
        //TODO what is default timeout?
        //TODO status check

        //check browser title
        System.out.println("Site opening done");
    }
}
