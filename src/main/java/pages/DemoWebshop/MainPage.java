package pages.DemoWebshop;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import Frameworkbase.BaseClass;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import utilities.UserHelper;

public class MainPage extends UserHelper{
	//public AppiumDriver driver;
	
	/*----------Top Menu Toolbar----------*/
	@FindBy(className = "ico-register")
	private static WebElement lnkRegister;
	@FindBy(className = "ico-login")
	private static WebElement lnkLogin;
	@FindBy(className = "ico-cart")
	private static WebElement lnkShoppingCart;
	@FindBy(className = "ico-wishlist")
	private static WebElement lnkWishlist;
	@FindBy(className = "ico-logout")
	private static WebElement lnkLogout;
	@FindBy(xpath = "//a[contains(text(), '@')]")
	private static WebElement lnkAccount;
	
	public MainPage() {
		PageFactory.initElements(driver, this);
	}
	
	public void clickOptionsFromTopMenu(String option) {
		String method = "Click from top Menu";
		switch(option.toUpperCase()) {
			case "REGISTER":
				waitForElement(lnkRegister);
				lnkRegister.click();
				System.out.println("Clicked Register");
				break;
			case "LOG IN":
				waitForElement(lnkLogin);
				lnkLogin.click();
				System.out.println("Clicked Log in");
				break;
			case "SHOPPING CART":
				waitForElement(lnkShoppingCart);
				lnkShoppingCart.click();
				System.out.println("Clicked Shopping Cart");
				break;
			case "WISHLIST":
				waitForElement(lnkWishlist);
				lnkWishlist.click();
				System.out.println("Clicked Wishlist");
				break;
			case "LOG OUT":
				waitForElement(lnkLogout);
				lnkLogout.click();
				System.out.println("Clicked Log out");
				break;
			case "Account":
				waitForElement(lnkAccount);
				lnkAccount.click();
				System.out.println("Clicked Log out");
				break;	
			default:
				throw new Error("Option not in Menu!");
		}
		takeScreenshotAndLog(driver,method,"clicked "+option);
	}
	
	public void verifySucessfulLogin(String accountName) {
		String method = "Verify successful login";
		String obtainedAccount;
		//Wait(2000);
		//waitForElement(lnkAccount);
		moveAndHighlightElement(lnkAccount);
		obtainedAccount = lnkAccount.getText();
		Assert.assertEquals(obtainedAccount, accountName, "Account not the same!");
		takeScreenshotAndLog(driver,method,"Login successful!");
	}
	
	public void verifySucessfulLogout() {
		String method = "Verify successful log out";
		//Wait(2000);
		//waitForElement(lnkRegister);
		//moveAndHighlightElement(lnkRegister);
		takeScreenshotAndLog(driver,method,"Logout successful!");
	}
}
