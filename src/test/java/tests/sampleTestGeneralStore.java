package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import Frameworkbase.BaseClass;
import io.appium.java_client.AppiumDriver;
import pages.Calculator;
import pages.GeneralStore.ProductsPage;
import pages.GeneralStore.StartPage;

public class sampleTestGeneralStore extends BaseClass{
	@SuppressWarnings("rawtypes")
	StartPage startPage;
	ProductsPage productsPage;
	AppiumDriver driver;
	
	private void initialize() {
		driver = getDriver();
		startPage = new StartPage();
		productsPage = new ProductsPage();
	}
	
	@Test
	public void testOne() throws InterruptedException {
		initialize();
		System.out.println("Starting TestOne");
		startPage.enterGeneralStoreDetails("PW","Bahrain","female");
		
		Thread.sleep(5000);
		productsPage.addProductToCart("Air Jordan 9 Retro");
		productsPage.verifyProductAddedToCart("Air Jordan 9 Retro");
	}
}
