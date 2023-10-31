package Frameworkbase;

import java.io.File;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class BaseClass {

	//@SuppressWarnings("rawtypes")
	public static ThreadLocal<AppiumDriver> mobileDrivers = new ThreadLocal<AppiumDriver>();
	public String appiumServer = "127.0.0.1";
	public int appiumPort = 4723;
	URL appiumURL = null;
	public static String fileSeparator = File.separator;
	public String testDataPath;
	public String apkFilePath;
	
	public AppiumDriver getDriver() {
		return mobileDrivers.get();
	}
	
	public void setDriver(AppiumDriver driver) {
		mobileDrivers.set(driver);
	}
	
	@Parameters({ "appPath", "platformName" , "deviceName", "browserName", "isWebTest","link","appPackage","appActivity","testData"})
	@BeforeClass
	public void setup(String app,String platformName, String udid, String browserName, String isWebTest, String link, 
			String appPackageBundleId, String appActivity, String testData) {
		try {
			DesiredCapabilities caps = new DesiredCapabilities();
			testDataPath = System.getProperty("user.dir")+fileSeparator+"TestData"+fileSeparator+testData;
			apkFilePath = System.getProperty("user.dir")+fileSeparator+"src"+fileSeparator+"test"
								+fileSeparator+"resources"+fileSeparator+"apps"+fileSeparator+app;
			setAppCapabilitiesCommon(caps,platformName,udid);
			switch(platformName.toUpperCase()) {
				/* isWebTest determines if test is web mobile automation
				 * value will be "true" is the string obtained from .xml file is "true"
				 * value will be "false" otherwise*/
				case "ANDROID":
					caps.setCapability("automationName", "UIAutomator2");
					setAppCapabilitiesAndroid(caps, Boolean.parseBoolean(isWebTest), browserName, appPackageBundleId, appActivity, apkFilePath);
					break;
				case "IOS":
					caps.setCapability("automationName", "XCUITest");
					setAppCapabilitiesIOS(caps, Boolean.parseBoolean(isWebTest), browserName, appPackageBundleId, apkFilePath);	
					break;
			}
			/*Appium URL setup is dependent on the current Appium version
			 * for versions 1.2 and below, such as Appium Desktop, "/wd/hub" should be added at the end
			 * for versions 2.0 onwards, that string is no longer necessary
			 * Note that Appium can still automate mobile apps regardless of Appium version
			 * currently still placed here, in case user is currently using an older version of Appium*/
			//appiumURL = new URL("http://"+appiumServer+":"+appiumPort+"/wd/hub"); //for Appium 1.2 and lower
			appiumURL = new URL("http://"+appiumServer+":"+appiumPort);
			AppiumDriver driver = new AppiumDriver(appiumURL,caps);
			setDriver(driver);
			if(Boolean.parseBoolean(isWebTest)) {
				driver.get(link);
			}
		}catch(Exception exp){
			System.out.println("Cause is: "+exp.getCause());
			System.out.println("Message is: "+exp.getMessage());
			exp.printStackTrace();
		}
	}
	
	@AfterClass
	public void teardown() {
		getDriver().close();
		getDriver().quit();
		removeDriver();
	}
	
	private static void removeDriver() {
		mobileDrivers.remove();
	}

	private DesiredCapabilities setAppCapabilitiesAndroid(DesiredCapabilities cap, boolean isWebTest, String browserName,
			String appPackage, String appActivity, String appFilePath) {
		if(isWebTest) {
			cap.setCapability("browserName", browserName);
		}else if(doesAppFileExist(appFilePath)){	
			cap.setCapability("app", appFilePath);
		}else {
			cap.setCapability("appPackage", appPackage);
			cap.setCapability("appActivity", appActivity);			
		}
		return cap;
	}
	
	private DesiredCapabilities setAppCapabilitiesIOS(DesiredCapabilities cap, boolean isWebTest, String browserName,
			String bundleId, String appFilePath) {
		if(isWebTest) {
			cap.setCapability("browserName", browserName);
		}else if(doesAppFileExist(appFilePath)){
			cap.setCapability("app", appFilePath);
		}else {
			cap.setCapability("bundleId", bundleId);
		}
		return cap;
	}
	
	private DesiredCapabilities setAppCapabilitiesCommon(DesiredCapabilities cap, String platform, String udid) {
		cap.setCapability("platformName", platform);
		cap.setCapability("udid", udid);
		cap.setCapability("noReset", "true");
		cap.setCapability("unicodeKeyboard", true);
		cap.setCapability("resetKeyboard", true);
		return cap;
	}
	
	private boolean doesAppFileExist(String appFileName) {
		File file = new File(appFileName);
		return file.exists();
	}
}
