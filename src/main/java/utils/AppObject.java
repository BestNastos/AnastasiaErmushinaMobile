package utils;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AppObject {
//    String packageName = "com.example.android.contactmanager:id/";

    @FindBy(id = "addContactButton")
    public WebElement addContactButton;

    @FindBy(id = "accountSpinner")
    public WebElement targetAccount;

    @FindBy(id = "contactNameEditText")
    WebElement name;

    @FindBy(id = "contactPhoneEditText")
    WebElement phone;

    @FindBy(id = "contactEmailEditText")
    WebElement email;



}
