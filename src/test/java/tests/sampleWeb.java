package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import Frameworkbase.BaseClass;
import io.appium.java_client.AppiumDriver;
import pages.DemoWebshop.LoginPage;
import pages.DemoWebshop.MainPage;

public class sampleWeb extends BaseClass{
	@SuppressWarnings("rawtypes")
	MainPage demoWebshop;
	LoginPage loginPage;
	AppiumDriver driver;
	
	private void initialize() {		
		driver = getDriver();
		demoWebshop = new MainPage();
		loginPage = new LoginPage();
	}
	
	@Test
	public void testWeb() throws InterruptedException {
		initialize();
		System.out.println("Running WebTest");
		
		demoWebshop.clickOptionsFromTopMenu("Log in");
		
		loginPage.login("jdl1@test.com", "password1");
		
		demoWebshop.verifySucessfulLogin("jdl1@test.com");
		demoWebshop.clickOptionsFromTopMenu("Log out");
		demoWebshop.verifySucessfulLogout();
	}
}
