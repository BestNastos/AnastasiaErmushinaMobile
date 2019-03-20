# AnastasiaErmushinaMobile

5. Modify existing tests to run on a real device. What should be changed?

DeviceName in code needs to be changed:
capabilities.setCapability("deviceName", "5200b9bffeb8453b");

6. Connect a real device to Appium (describe required actions) and run tests. Are there any difference with run on emulator?

- You need to enable developer mode on physical device, enable USB debugging, set USB configuration to MTP.
- After you connect actual device via USB you need to allow this computer to work with the device (click OK in pop up).
- Run Appium, add new set of desired capabilities for the divice if you want to work with Inspector.
- Unlike AVD, physical devide doesn’t slow down the PC which makes work much more comfortable. Appium Inspector doesn’t lag.
