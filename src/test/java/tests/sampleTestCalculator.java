package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import Frameworkbase.BaseClass;
import io.appium.java_client.AppiumDriver;
import pages.Calculator;

public class sampleTestCalculator extends BaseClass{
	@SuppressWarnings("rawtypes")
	Calculator calculator;
	AppiumDriver driver;
	
	private void initialize() {
		driver = getDriver();
		calculator = new Calculator(driver);
	}
	
	@Test
	public void testOne() throws InterruptedException {
		initialize();
		System.out.println("Completed TestOne");
		String expression = "1+2=";
		calculator.parseExpression(expression);
		Assert.assertTrue(calculator.verifyResult(), "Result not the same!");
	}
}
