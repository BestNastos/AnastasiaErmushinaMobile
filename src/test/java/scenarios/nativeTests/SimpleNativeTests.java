package scenarios.nativeTests;

import contactManager.MainPage;
import contactManager.SaveContactPage;
import org.testng.annotations.Test;
import scenarios.Hooks;

/**
 * Native test(s). Test properties file path is passed to Hooks via XML configuration file.
 */
@Test(groups = "native")
public class SimpleNativeTests extends Hooks {

    /**
     * Clicks "Add contact" and checks if form is opened and virtual keyboard is present.
     */
    @Test(description = "Click 'Add Contact' and check result.")
    public void nativeTest() {

        // 1. Open app and click "Add contact".
        new MainPage(driver).addContact();

        // 2. Check if all fields are displayed on "Save Contact" page.
        SaveContactPage saveContactPage = new SaveContactPage(driver);
        saveContactPage.checkAllFieldsAreDisplayed();

        // 3. See if virtual keyboard pops up.
        saveContactPage.checkVirtualKeyboardIsDisplayed();

        System.out.println("Native test complete");
    }
}
