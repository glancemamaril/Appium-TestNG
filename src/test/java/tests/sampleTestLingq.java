package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import Frameworkbase.BaseClass;
import io.appium.java_client.AppiumDriver;
import pages.Calculator;
import pages.lingq.LoginPage;
import pages.lingq.StartPage;

public class sampleTestLingq extends BaseClass{
	@SuppressWarnings("rawtypes")
	StartPage startPage;
	LoginPage loginPage;
	AppiumDriver driver;
	
	private void initialize() {
		driver = getDriver();
		startPage = new StartPage();
		loginPage = new LoginPage();
	}
	
	@Test
	public void testOne() throws InterruptedException {
		initialize();
		System.out.println("Completed TestOne");
		startPage.clickLogin();
		
		loginPage.loginToAccount("glacem28@gmail.com","");
	}
}
