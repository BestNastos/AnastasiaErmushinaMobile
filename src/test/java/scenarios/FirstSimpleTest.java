package scenarios;

import org.openqa.selenium.By;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class FirstSimpleTest extends DriverSetup{
    @BeforeClass
    public void setUp() throws Exception {
//        prepareNative();
        prepareWeb();
    }

    @Test(description = "press add contact button")
    public void nativeTest() throws InterruptedException {
        String app_package_name = "com.example.android.contactmanager:id/";
        By add_btn = By.id(app_package_name + "addContactButton");
        driver.findElement(add_btn).click();
        System.out.println("Simplest Appium test done");
    }

    @Test(description = "Open website")
    public void webTest() throws InterruptedException {
        driver.get("http://iana.org");
        Thread.sleep(5000);
        System.out.println("Site opening done");
    }

    @AfterClass
    public void tearDown() throws Exception {
        driver.quit();
    }

}
