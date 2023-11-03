package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import Frameworkbase.BaseClass;
import io.appium.java_client.AppiumDriver;
import pages.DemoWebshop.LoginPage;
import pages.DemoWebshop.MainPage;
import utilities.JSONReader;
import utilities.TestCaseData;

public class sampleWeb extends BaseClass{
	@SuppressWarnings("rawtypes")
	MainPage demoWebshop;
	LoginPage loginPage;
	AppiumDriver driver;
	JSONReader jsonFile;
	TestCaseData tcData;
	
	private void initialize() {		
		driver = getDriver();
		demoWebshop = new MainPage();
		loginPage = new LoginPage();
		jsonFile = new JSONReader(testDataPath);
		tcData = jsonFile.getTestCaseData("TC004");
	}
	
	@Test
	public void testWeb() throws InterruptedException {
		initialize();
		String email = tcData.getUsername();
		String password = tcData.getPassword();
		System.out.println("Running WebTest");
		
		demoWebshop.clickOptionsFromTopMenu("Log in");
		
		loginPage.login(email, password);
		
		demoWebshop.verifySucessfulLogin(email);
		demoWebshop.clickOptionsFromTopMenu("Log out");
		demoWebshop.verifySucessfulLogout();
	}
}
