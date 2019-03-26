package appObjects;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

import static org.openqa.selenium.support.PageFactory.*;
import static org.testng.Assert.*;

public class NewContactPage {

    private AppiumDriver driver;

    @AndroidFindBy(id = "accountSpinner")
    private AndroidElement targetAccountField;

    @AndroidFindBy(id = "contactNameEditText")
    private AndroidElement nameField;

    @AndroidFindBy(id = "contactPhoneEditText")
    private AndroidElement phoneField;

    @AndroidFindBy(id = "contactEmailEditText")
    private AndroidElement emailField;

    public NewContactPage(AppiumDriver driver) {
        this.driver = driver;
        initElements(new AppiumFieldDecorator(driver), this);
    }

    public void checkAllFieldsAreDisplayed() {
        assertTrue(targetAccountField.isDisplayed());
        assertTrue(nameField.isDisplayed());
        assertTrue(phoneField.isDisplayed());
        assertTrue(emailField.isDisplayed());
    }

    public void checkVirtualKeyboardIsDisplayed() {
        assertNotNull(driver.getKeyboard());
    }
}
