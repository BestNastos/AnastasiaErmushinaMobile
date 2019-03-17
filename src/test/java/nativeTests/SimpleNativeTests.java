package nativeTests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import setup.Driver;

import java.io.IOException;

@Test(groups = "native")
public class SimpleNativeTests extends Driver {

    protected SimpleNativeTests() throws IOException {//TODO fetch property file
    }

    @Test(description = "Just click on button 'Add contact'")
    public void simplestTest() throws Exception {

        String app_package_name = "com.example.android.contactmanager:id/";
        By add_btn = By.id(app_package_name + "addContactButton");
        driver().findElement(add_btn).click();

        //TODO wait for fields appearance?
        //TODO Assertions needs to be verified (tested) in turn.
        //TODO “Mutation testing” (light version) can be used for this purpose.

        System.out.println("Simplest Appium test done");
    }
}
