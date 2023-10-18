package tests;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.options.XCUITestOptions;

public class TestingIdeas {
	public static void main(String args[]) throws MalformedURLException {
		String appiumServer = "127.0.0.1";
		int appiumPort = 4723;
		URL appiumURL = null;
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("platformName", "Android");
		caps.setCapability("platformVersion", "12.0");
		caps.setCapability("deviceName", "Samsung A11");
		caps.setCapability("udid", "R9RR7014CKH");
		caps.setCapability("browserName", "Chrome");
		//caps.setCapability("appPackage","com.linguist");
		//caps.setCapability("appActivity","com.lingq.ui.MainActivity");
		caps.setCapability("automationName", "UIAutomator2");
		appiumURL = new URL("http://"+appiumServer+":"+appiumPort);
		AppiumDriver driver = new AppiumDriver(appiumURL,caps);
		driver.get("https://demowebshop.tricentis.com/");
		
		driver.quit();
	}
}
