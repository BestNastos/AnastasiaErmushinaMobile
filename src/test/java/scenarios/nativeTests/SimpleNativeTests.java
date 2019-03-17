package scenarios.nativeTests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import scenarios.Hooks;
import setup.PropertyFile;

@Test(groups = "native")
public class SimpleNativeTests extends Hooks {

//    SimpleNativeTests(PropertyFile path){
//        super(path);
//    }

    @Test(description = "Just click on button 'Add contact'")
    public void nativeTest() { //throws Exception ?

        String app_package_name = "com.example.android.contactmanager:id/";
        By add_btn = By.id(app_package_name + "addContactButton");
        driver.findElement(add_btn).click();

        //TODO wait for fields appearance?
        //TODO Assertions needs to be verified (tested) in turn.
        //TODO “Mutation testing” (light version) can be used for this purpose.

        System.out.println("Simplest Appium test done");
    }
}
