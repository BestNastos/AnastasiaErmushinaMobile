package contactManager;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;

import static org.openqa.selenium.support.PageFactory.*;

/**
 * Main Page of the Contact Manager app. The page is developed for
 * Android test(s) since there is no Contact Manager version for iOS.
 */
public class MainPage {

    @AndroidFindBy(id = "addContactButton")
    private WebElement add;

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
