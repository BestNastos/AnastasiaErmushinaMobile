package contactManager;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;

import static org.openqa.selenium.support.PageFactory.*;
import static org.testng.Assert.*;

/**
 * "Save Contact" page of the Contact Manager app. The page is developed
 * for Android test(s) since there is no Contact Manager version for iOS.
 */
public class SaveContactPage {

    private AppiumDriver driver;

    @AndroidFindBy(id = "accountSpinner")
    private WebElement targetAccountField;

    @AndroidFindBy(id = "contactNameEditText")
    private WebElement nameField;

    @AndroidFindBy(id = "contactPhoneEditText")
    private WebElement phoneField;

    @AndroidFindBy(id = "contactEmailEditText")
    private WebElement emailField;

    public SaveContactPage(AppiumDriver driver) {
        this.driver = driver;
        initElements(new AppiumFieldDecorator(driver), this);
    }

    /**
     * Asserts that all the necessary fields are present on the page.
     */
    public void checkAllFieldsAreDisplayed() {
        assertTrue(targetAccountField.isDisplayed());
        assertTrue(nameField.isDisplayed());
        assertTrue(phoneField.isDisplayed());
        assertTrue(emailField.isDisplayed());
    }

    /**
     * Asserts that virtual keyboard pops up.
     */
    public void checkVirtualKeyboardIsDisplayed() {
        assertNotNull(driver.getKeyboard());
    }
}
