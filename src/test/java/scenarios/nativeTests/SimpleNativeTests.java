package scenarios.nativeTests;

import contactManager.MainPage;
import contactManager.SaveContactPage;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

import static setup.Driver.driver;

/**
 * Native test(s). Test properties file path is passed to the test via XML configuration file.
 */
@Test(groups = "native")
public class SimpleNativeTests {

    /**
     * Clicks "Add contact" and checks if form is opened and virtual keyboard is present.
     *
     * @throws MalformedURLException if incorrect URL is passed to driver constructor
     *                               within {@link setup.Driver#driver()} method.
     */
    @Test(description = "Click 'Add Contact' and check result")
    public void nativeTest() throws MalformedURLException {

        // 1. Open app and click "Add contact".
        new MainPage(driver()).addContact();

        // 2. Check if all fields are displayed on "Save Contact" page.
        SaveContactPage saveContactPage = new SaveContactPage(driver());
        saveContactPage.checkAllFieldsAreDisplayed();

        // 3. See if virtual keyboard pops up.
        saveContactPage.checkVirtualKeyboardIsDisplayed();

        System.out.println("Native test complete");
    }
}
