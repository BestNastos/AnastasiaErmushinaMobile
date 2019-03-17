package scenarios.nativeTests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import scenarios.Hooks;

import java.io.IOException;

import static setup.Driver.driverSingleton;
import static setup.PropertyFile.NATIVE;

@Test(groups = "native")
public class SimpleNativeTests extends Hooks {

    SimpleNativeTests(){
        super(NATIVE.toString());
    }

    @Test(description = "Just click on button 'Add contact'")
    public void nativeTest() throws IOException {

        String app_package_name = "com.example.android.contactmanager:id/";
        By add_btn = By.id(app_package_name + "addContactButton");
        driverSingleton().findElement(add_btn).click();

        //TODO wait for fields appearance?
        //TODO Assertions needs to be verified (tested) in turn.
        //TODO “Mutation testing” (light version) can be used for this purpose.

        //target account com.example.android.contactmanager:id/accountSpinner
        //contact name com.example.android.contactmanager:id/contactNameEditText
        //contact phone com.example.android.contactmanager:id/contactPhoneEditText
        //contact email com.example.android.contactmanager:id/contactEmailEditText
        // displayed
        // driver().getKeyboard();

        System.out.println("Simplest Appium test done");
    }
}
