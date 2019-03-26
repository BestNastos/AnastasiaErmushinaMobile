package contactManager;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

import static org.openqa.selenium.support.PageFactory.*;

public class MainPage {

    @AndroidFindBy(id = "addContactButton")
    private AndroidElement addContactButton;

    public MainPage(AppiumDriver driver) {
        initElements(new AppiumFieldDecorator(driver), this);
    }

    public void addContact(){
        addContactButton.click();
    }
}
