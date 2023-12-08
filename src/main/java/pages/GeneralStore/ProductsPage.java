package pages.GeneralStore;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import Frameworkbase.BaseClass;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import utilities.UserHelper;

public class ProductsPage extends UserHelper{
	//public AppiumDriver driver;
	
	/*----------Top Menu Toolbar----------*/
	@FindBy(id = "com.androidsample.generalstore:id/appbar_btn_back")
	private static WebElement btnBack;
	@FindBy(id = "com.androidsample.generalstore:id/appbar_btn_cart")
	private static WebElement btnCart;
	@FindBy(id = "com.androidsample.generalstore:id/rvProductList")
	private static WebElement rvProductList;
	@FindBy(id = "com.androidsample.generalstore:id/radioFemale")
	private static WebElement rdbtnFemale;
	@FindBy(id = "com.androidsample.generalstore:id/btnLetsShop")
	private static WebElement btnLetsShop;
	
	public ProductsPage() {
		PageFactory.initElements(driver, this);
	}
	
	public void addProductToCart(String productName) {
		String method = "add to cart";
		findAndScrollUntilVisible(rvProductList,productName);
		WebElement addToCart = driver.findElement(By.xpath("//android.widget.TextView[@text='"+productName+"']/following-sibling::*/android.widget.TextView[@text='ADD TO CART']"));
		addToCart.click();
		Wait(2);
		takeScreenshotAndLog(driver,method,"added product to cart");
	}
	
	public void verifyProductAddedToCart(String productName) {
		String method = "already added to cart";
		try {
			WebElement addToCart = driver.findElement(By.xpath("//android.widget.TextView[@text='"+productName+"']/following-sibling::*/android.widget.TextView[@text='ADDED TO CART']"));
		}catch(NoSuchElementException e) {
			throw new Error("Element not added to cart!");
		}
		takeScreenshotAndLog(driver,method,"verified successful add to cart");
	}
}
