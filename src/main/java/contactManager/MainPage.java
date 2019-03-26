package contactManager;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

import static org.openqa.selenium.support.PageFactory.*;

/**
 * Main Page of the Contact Manager app. The page is developed for
 * Android test(s) as there is no Contact Manager version for iOS.
 */
public class MainPage {

    @AndroidFindBy(id = "addContactButton")
    private AndroidElement add;

    public MainPage(AppiumDriver driver) {
        initElements(new AppiumFieldDecorator(driver), this);
    }

    /**
     * Clicks "Add Contact" button to open "Save Contact" app page.
     */
    public void addContact() {
        add.click();
    }
}
