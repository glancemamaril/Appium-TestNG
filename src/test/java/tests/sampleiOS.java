package tests;

import io.appium.java_client.ios.options.XCUITestOptions;

public class sampleiOS {
	public static void main(String args[]) {
		XCUITestOptions options = new XCUITestOptions();
		options.setDeviceName("iPhone 15 Pro Max");
		options.setPlatformVersion("17.0");
		//options.setBrowserName("Safari");
	}
}
