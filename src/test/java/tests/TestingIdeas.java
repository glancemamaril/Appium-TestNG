package tests;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteExecuteMethod;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.android.nativekey.KeyEventFlag;
import io.appium.java_client.ios.options.XCUITestOptions;

public class TestingIdeas {
	public static void main(String args[]) throws MalformedURLException, InterruptedException {
		String appiumServer = "127.0.0.1";
		int appiumPort = 4723;
		URL appiumURL = null;
		DesiredCapabilities caps = new DesiredCapabilities();
		
//		caps.setCapability("platformName", "iOS");
		caps.setCapability("platformName", "Android");
		
//		caps.setCapability("platformVersion", "14.0");
//		caps.setCapability("deviceName", "Samsung A11");
		
//		caps.setCapability("udid", "R9RR7014CKH");
		caps.setCapability("udid", "emulator-5554");
//		caps.setCapability("udid", "C1730E8C-D3A9-45FF-8B33-6BAA24211740");
		
//		caps.setCapability("browserName", "Chrome");
//		caps.setCapability("browserName", "Safari");
		
		caps.setCapability("appPackage","com.androidsample.generalstore");
		caps.setCapability("appActivity","com.androidsample.generalstore.SplashActivity");
		
		caps.setCapability("automationName", "UIAutomator2");
//		caps.setCapability("automationName", "XCUITest");
		
		appiumURL = new URL("http://"+appiumServer+":"+appiumPort);
		AppiumDriver driver = new AppiumDriver(appiumURL,caps);
//		driver.get("https://demowebshop.tricentis.com/");
		Thread.sleep(3000);
		driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("PW");
		driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
		
		Thread.sleep(5000);
		
//		RemoteExecuteMethod executeMethod = new RemoteExecuteMethod((RemoteWebDriver)driver);
		((JavascriptExecutor)driver).executeScript("mobile: swipeGesture", ImmutableMap.of(
			    "left", 100, "top", 100, "width", 200, "height", 200,
			    "direction", "up",
			    "percent", 0.75
			));
		//TouchAction touchAction = TouchAction(driver); 
		
		Thread.sleep(5000);
		driver.quit();
		System.out.println("Run finished!");
//		String fileSeparator = File.separator;
//		String filePath = System.getProperty("user.dir")+fileSeparator+"src"+fileSeparator+"test"+fileSeparator+"resources"+fileSeparator+"apps"+fileSeparator+"chrome.apk";
//		System.out.println(filePath);
//        // Create a File object with the specified file path
//        File file = new File(filePath);
//
//        // Check if the file exists
//        if (file.exists()) {
//            System.out.println("The file exists.");
//        } else {
//            System.out.println("The file does not exist.");
//        }
        
        
	}

	private static Object getSwipeGestureParameters() {
        // Define your swipe gesture parameters as a Map
        // For example, specifying the starting and ending coordinates
        Map<String, Object> swipeParams = new HashMap<>();
        swipeParams.put("startX", 500);
        swipeParams.put("startY", 1500);
        swipeParams.put("endX", 500);
        swipeParams.put("endY", 500);
        swipeParams.put("duration", 0.8); // Duration of the swipe in seconds

        return swipeParams;
    }
}
