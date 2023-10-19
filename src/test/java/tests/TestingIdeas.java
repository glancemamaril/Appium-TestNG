package tests;

import java.io.File;
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
		
		//caps.setCapability("platformName", "iOS");
		caps.setCapability("platformName", "Android");
		
		//caps.setCapability("platformVersion", "14.0");
		//caps.setCapability("deviceName", "Samsung A11");
		
		//caps.setCapability("udid", "R9RR7014CKH");
		caps.setCapability("udid", "emulator-5554");
		//caps.setCapability("udid", "C1730E8C-D3A9-45FF-8B33-6BAA24211740");
		
		caps.setCapability("browserName", "Chrome");
		//caps.setCapability("browserName", "Safari");
		
		//caps.setCapability("appPackage","com.linguist");
		//caps.setCapability("appActivity","com.lingq.ui.MainActivity");
		
		caps.setCapability("automationName", "UIAutomator2");
		//caps.setCapability("automationName", "XCUITest");
		
//		appiumURL = new URL("http://"+appiumServer+":"+appiumPort);
//		AppiumDriver driver = new AppiumDriver(appiumURL,caps);
//		driver.get("https://demowebshop.tricentis.com/");
//		
//		driver.quit();
		String fileSeparator = File.separator;
		String filePath = System.getProperty("user.dir")+fileSeparator+"src"+fileSeparator+"test"+fileSeparator+"resources"+fileSeparator+"apps"+fileSeparator+"chrome.apk";
		System.out.println(filePath);
        // Create a File object with the specified file path
        File file = new File(filePath);

        // Check if the file exists
        if (file.exists()) {
            System.out.println("The file exists.");
        } else {
            System.out.println("The file does not exist.");
        }
	}
}
