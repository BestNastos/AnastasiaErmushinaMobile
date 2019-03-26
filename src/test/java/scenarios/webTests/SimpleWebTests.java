package scenarios.webTests;

import ianaWebsite.HomePage;
import org.testng.annotations.Test;
import setup.Driver;

import java.io.IOException;

import static setup.Driver.*;
import static setup.Driver.SUT;

/**
 * Web test(s). Test properties file path is passed to the test via XML configuration file.
 */
@Test(groups = "web")
public class SimpleWebTests {

    /**
     * Opens website and asserts that website is successfully opened.
     *
     * @throws IOException if an I/O exception occurs while establishing HttpURLConnection in
     *                     {@link ianaWebsite.HomePage#checkStatusCodeIsOk()} or if incorrect
     *                     URL is passed to driver constructor in {@link Driver#driver()} or
     *                     {@link Driver#driverWait()}.
     */
    @Test(description = "Open website and assert it is opened")
    public void webTest() throws IOException {

        // 1. Open site under testing.
        HomePage homePage = new HomePage(driver());
        homePage.openWebsite(SUT, driverWait());

        // 2. Assert browser title is correct.
        homePage.checkBrowserTitleIsCorrect(BROWSER_TITLE);

        // 3. Assert main content is present.
        homePage.checkMainContentIsPresent();

        // 4. Assert status code is OK.
        homePage.checkStatusCodeIsOk();

        System.out.println("Web test complete");
    }
}
