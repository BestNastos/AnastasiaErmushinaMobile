# AnastasiaErmushinaMobile

Tasks:
1. Change settings to run web test on a certain iOS device in mobile cloud. Run test with your changes. Did test pass? 
Yes.

2. Change settings to run native test on a certain/random Android device in mobile cloud. Add support of appPackage and appActivity parameters for Android devices (reading from a .properties file and then setting in the DesiredCapabilities). Locally installed Appium DT has no need in these parameters, but for Appium server of EPAM mobile cloud it’s mandatory.

3. Run test with your changes. Did test pass? 
Yes.

4. Try to use autoLaunch capability with app as before instead of appPackage and appActivity. Does this approach work?
No, test falls with exception:
"org.openqa.selenium.SessionNotCreatedException: Unable to create a new remote session. Please check the server log for more details. Original error: {"status":13,"value":{"message":"An unknown server-side error occurred while processing the command. Original error: The desired capabilities must include either an app, appPackage or browserName"},"sessionId":null} (WARNING: The server did not provide any stacktrace information)"
