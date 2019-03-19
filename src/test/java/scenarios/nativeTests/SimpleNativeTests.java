package scenarios.nativeTests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import scenarios.Hooks;

import java.net.MalformedURLException;

import static org.testng.Assert.*;
import static setup.Driver.APP_PACK;
import static setup.Driver.driver;

/**
 * Native test(s). Test properties file path is passed to the test via XML configuration file.
 */
@Test(groups = "native")
public class SimpleNativeTests extends Hooks {

    /**
     * Clicks on 'Add contact' button and checks that form is opened and virtual keyboard
     * is present.
     *
     * @throws MalformedURLException If incorrect URL is passed to driver constructor in
     *                               #prepareDriver() method within #driver() method.
     */
    @Test(description = "Click 'Add Contact' and check result")
    public void nativeTest() throws MalformedURLException {

        // 1. Click "Add Contact"
        WebElement abbButton = driver().findElement(By.id(APP_PACK + ":id/addContactButton"));
        abbButton.click();

        // 2. Assert that "Target Account" field is visible
        WebElement targetAccount = driver().findElement(By.id(APP_PACK + ":id/accountSpinner"));
        assertTrue(targetAccount.isDisplayed());

        // 3. Assert that "Contact Name" field is visible
        WebElement name = driver().findElement(By.id(APP_PACK + ":id/contactNameEditText"));
        assertTrue(name.isDisplayed());

        // 4. Assert that "Contact Phone" field is visible
        WebElement phone = driver().findElement(By.id(APP_PACK + ":id/contactPhoneEditText"));
        assertTrue(phone.isDisplayed());

        // 5. Assert that "Contact E-mail" field is visible
        WebElement email = driver().findElement(By.id(APP_PACK + ":id/contactEmailEditText"));
        assertTrue(email.isDisplayed());

        //6. Assert that keyboard pops up
        assertNotNull(driver().getKeyboard());

        System.out.println("Native test complete");
    }
}
