package scenarios.webTests;

import org.testng.annotations.Test;
import scenarios.Hooks;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import static java.net.HttpURLConnection.*;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;
import static org.testng.Assert.*;
import static setup.Driver.*;
import static setup.Driver.SUT;

/**
 * Web test(s). Test properties file path is passed to the test via XML configuration file.
 */
@Test(groups = "web")
public class SimpleWebTests extends Hooks { //TODO extends?

    /**
     * Opens website and asserts that website is successfully opened.
     *
     * @throws IOException If an I/O exception occurs while establishing HttpURLConnection or
     *                     if incorrect URL is passed to driver constructor.
     * @see setup.Driver#driver() method.
     */
    @Test(description = "Open website and assert it is opened")
    public void webTest() throws IOException {

        // 1. Open Website.
        driver().get(SUT);

        // 2. Assert the page is loaded.
        driverWait().until(urlMatches(SUT + "/"));

        // 3. Assert the browser title is correct.
        assertEquals(driver().getTitle(), BROWSER_TITLE);

        // 4. Assert that Status Code is OK (200).
        URL sut = new URL(driver().getCurrentUrl());
        HttpURLConnection connection = (HttpURLConnection) sut.openConnection();
        assertEquals(connection.getResponseCode(), HTTP_OK);
        connection.disconnect();

        System.out.println("Web test complete");
    }
}
