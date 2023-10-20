package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import Frameworkbase.BaseClass;
import io.appium.java_client.AppiumDriver;
import pages.Calculator;
import pages.GeneralStore.StartPage;

public class sampleTestGeneralStore extends BaseClass{
	@SuppressWarnings("rawtypes")
	StartPage startPage;
	AppiumDriver driver;
	
	private void initialize() {
		driver = getDriver();
		startPage = new StartPage();
	}
	
	@Test
	public void testOne() throws InterruptedException {
		initialize();
		System.out.println("Starting TestOne");
		startPage.enterGeneralStoreDetails("PW","Taiwan","female");
		
	}
}
