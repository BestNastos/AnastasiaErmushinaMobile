package ianaWebsite;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.*;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import static java.net.HttpURLConnection.HTTP_OK;
import static org.openqa.selenium.support.PageFactory.initElements;
import static org.openqa.selenium.support.ui.ExpectedConditions.urlMatches;
import static org.testng.Assert.*;
import static setup.Driver.SUT;

/**
 * Home Page of iana.org website. Generic @FindBy annotation is used because
 * this class is meant for web application testing and not native app testing.
 */
public class HomePage {

    private AppiumDriver driver;

    @FindBy(id = "main")
    private RemoteWebElement mainContent;

    public HomePage(AppiumDriver driver) {
        this.driver = driver;
        initElements(new AppiumFieldDecorator(driver), this);
    }

    /**
     * Opens website and waits until it's loaded.
     *
     * @param url  target website url.
     * @param wait WebDriverWait object that provides timeout.
     */
    public void openWebsite(String url, WebDriverWait wait) {
        driver.get(url);
        wait.until(urlMatches(SUT + "/"));
    }

    /**
     * Asserts that browser title is correct.
     *
     * @param title expected browser title.
     */
    public void checkBrowserTitleIsCorrect(String title) {
        assertEquals(driver.getTitle(), title);
    }

    /**
     * Asserts that main web page content is displayed.
     */
    public void checkMainContentIsPresent() {
        assertTrue(mainContent.isDisplayed());
    }

    /**
     * Asserts that web page response code is OK (200).
     *
     * @throws IOException if an I/O exception occurs while establishing HttpURLConnection or
     *                     if incorrect URL is passed to URL constructor.
     */
    public void checkStatusCodeIsOk() throws IOException {
        URL url = new URL(driver.getCurrentUrl());
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        int responseCode = connection.getResponseCode();
        connection.disconnect();
        assertEquals(responseCode, HTTP_OK);
    }
}
