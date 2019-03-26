package ianaWebsite;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.*;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import setup.KeysAndOptions;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static java.net.HttpURLConnection.HTTP_OK;
import static org.openqa.selenium.support.PageFactory.initElements;
import static org.openqa.selenium.support.ui.ExpectedConditions.urlMatches;
import static org.testng.Assert.*;
import static setup.Driver.SUT;
import static setup.Driver.driver;

public class HomePage {

    private AppiumDriver driver;

    @FindBy(id = "main")
    private RemoteWebElement mainContent;

    public HomePage(AppiumDriver driver) {
        this.driver = driver;
        initElements(new AppiumFieldDecorator(driver), this);
    }

    public void openWebsite(String url, WebDriverWait wait) {
        driver.get(url);
        wait.until(urlMatches(SUT + "/"));
    }

    public void checkBrowserTitleIsCorrect(String title){
        assertEquals(driver.getTitle(), title);
    }

    public void checkMainContentIsPresent() {
        assertTrue(mainContent.isDisplayed());
    }

    public void checkStatusCodeIsOk() throws IOException {
        URL url = new URL(driver.getCurrentUrl());
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        int responseCode = connection.getResponseCode();
        connection.disconnect();
        assertEquals(responseCode, HTTP_OK);
    }
}
