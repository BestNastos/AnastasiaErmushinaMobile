package setup;

/**
 * Provides String constants for driver preparation for web or native app test(s). Provides standard
 * property key words for retrieving values from property files. It also includes names of the most
 * popular browsers and mobile platforms for setting a browser in case of web app testing.
 **/

public class KeysAndOptions {
    // standard property key words:
    static final String AUT_KEY = "aut";
    static final String SUT_KEY = "sut";
    static final String DRIVER_KEY = "driver";
    static final String PLATFORM_KEY = "platform";
    static final String BROWSER_TITLE_KEY = "browserTitle";
    static final String APP_PACK_KEY = "appPackageInDevice";
    static final String UDID_KEY = "udid";
    static final String ACTIVITY_KEY = "activity";

    // the most popular browsers and mobile platforms:
    static final String ANDROID = "Android";
    static final String CHROME = "Chrome";
    static final String iOS = "iOS";
    static final String SAFARI = "Safari";
}
