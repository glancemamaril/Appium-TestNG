package Frameworkbase;

import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
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
	
	public AppiumDriver getDriver() {
		return mobileDrivers.get();
	}
	
	public void setDriver(AppiumDriver driver) {
		mobileDrivers.set(driver);
	}
	
	@Parameters({ "appPackage", "platformName" , "platformVersion", "deviceName", "browserName", "isWebTest","link"})
	@BeforeTest
	public void setup(String app,String platformName, String platformVersion, String deviceName, String browserName, String isWebTest, String link) {
		try {
			DesiredCapabilities caps = new DesiredCapabilities();
			//entering common capabilities
			caps.setCapability("platformName", platformName);
			caps.setCapability("platformVersion", platformVersion);
			caps.setCapability("deviceName", deviceName);		
			switch(platformName.toUpperCase()) {
				/* isWebTest determines if test is web mobile automation
				 * value will be "true" is the string obtained from .xml file is "true"
				 * value will be "false" otherwise*/
				case "ANDROID":
					caps.setCapability("automationName", "UIAutomator2");
					setAppCapabilitiesAndroid(caps, Boolean.parseBoolean(isWebTest), browserName);
					break;
				case "IOS":
					caps.setCapability("automationName", "XCUITest");
					setAppCapabilitiesIOS(caps, Boolean.parseBoolean(isWebTest), browserName);	
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
	
	@AfterTest
	public void teardown() {
		//mobileDriver.close();
		//mobileDriver.quit();
		getDriver().quit();
		removeDriver();
	}
	
	private static void removeDriver() {
		mobileDrivers.remove();
	}

	public DesiredCapabilities setAppCapabilitiesAndroid(DesiredCapabilities cap, boolean isWebTest, String browserName) {
		
		if(isWebTest) {
			cap.setCapability("browserName", browserName);
		}else {
			cap.setCapability("appPackage", "com.android.calculator2");
			cap.setCapability("appActivity", "com.android.calculator2.Calculator");
		}
		return cap;
	}
	
	public DesiredCapabilities setAppCapabilitiesIOS(DesiredCapabilities cap, boolean isWebTest, String browserName) {

		if(isWebTest) {
			cap.setCapability("browserName", browserName);
		}else {
			cap.setCapability("appPackage", "com.android.calculator2");
			cap.setCapability("appActivity", "com.android.calculator2.Calculator");
		}
		return cap;
	}
}
