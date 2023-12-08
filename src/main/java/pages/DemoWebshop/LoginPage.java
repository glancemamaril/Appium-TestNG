package pages.DemoWebshop;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import utilities.UserHelper;

public class LoginPage extends UserHelper{
	//public AppiumDriver driver;
	
	/*-----Sign in Page-----*/
	@FindBy(className = "email")
	private static WebElement txtEmail;
	@FindBy(className = "password")
	private static WebElement txtPassword;
	@FindBy(id = "RememberMe")
	private static WebElement chkRememberMe;
//	@FindBy(linkText="Forgot password?")
//	private static WebElement lnkForgotPassword;
	@FindBy(xpath = "//input[@value='Log in']")
	private static WebElement btnLogin;
	
	
	public LoginPage() {
		PageFactory.initElements(driver, this);
	}
	
	public void login(String email, String password) {
		String method = "Log in";
		waitForElement(txtEmail);
		txtEmail.clear();
		txtEmail.sendKeys(email);
		txtPassword.clear();;
		txtPassword.sendKeys(password);
		//driver.sendKeyEvent(AndroidKey.BACK);
		btnLogin.click();
		takeScreenshotAndLog(driver,method,"entered login credentials");
		//Wait(5);
	}
}
