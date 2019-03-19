package scenarios.nativeTests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import scenarios.Hooks;

import java.io.IOException;

import static org.testng.Assert.*;
import static setup.Driver.driver;
import static setup.PropertyFile.NATIVE;

//@Test(groups = "native")
public class SimpleNativeTests extends Hooks {

    SimpleNativeTests() {
        super(NATIVE.toString());
    }

    @Test(description = "Click 'Add Contact' button", groups = "native")
    public void nativeTest() throws IOException {
        String packName = "com.example.android.contactmanager:id/";

        // 1. Click "Add Contact"
        WebElement abbButton = driver().findElement(By.id(packName + "addContactButton"));
        abbButton.click();

        // 2. Assert that "Target Account" field is visible
        WebElement targetAccount = driver().findElement(By.id(packName + "accountSpinner"));
        assertTrue(targetAccount.isDisplayed());

        // 3. Assert that "Contact Name" field is visible
        WebElement name = driver().findElement(By.id(packName + "contactNameEditText"));
        assertTrue(name.isDisplayed());

        // 4. Assert that "Contact Phone" field is visible
        WebElement phone = driver().findElement(By.id(packName + "contactPhoneEditText"));
        assertTrue(phone.isDisplayed());

        // 5. Assert that "Contact E-mail" field is visible
        WebElement email = driver().findElement(By.id(packName + "contactEmailEditText"));
        assertTrue(email.isDisplayed());

        //6. Assert that keyboard pops up
        assertNotNull(driver().getKeyboard());

        System.out.println("Native test complete");

        //TODO Assertions needs to be verified (tested) in turn.
        //TODO “Mutation testing” (light version) can be used for this purpose.
    }
}
