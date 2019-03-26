package scenarios.nativeTests;

import appObjects.MainPage;
import appObjects.NewContactPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

import static org.testng.Assert.*;
import static setup.Driver.APP_PACK;
import static setup.Driver.driver;

/**
 * Native test(s). Test properties file path is passed to the test via XML configuration file.
 */
@Test(groups = "native")
public class SimpleNativeTests {

    /**
     * Clicks "Add contact" and checks if form is opened and virtual keyboard is present.
     *
     * @throws MalformedURLException If incorrect URL is passed to driver constructor
     * @see setup.Driver#driver() method.
     */
    @Test(description = "Click 'Add Contact' and check result")
    public void nativeTest() throws MalformedURLException {

        MainPage mainPage = new MainPage(driver());
        NewContactPage newContactPage = new NewContactPage(driver());

        mainPage.addContact();
        newContactPage.checkAllFieldsAreDisplayed();

        System.out.println("Native test complete");
    }
}
