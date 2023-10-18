package pages.lingq;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.UserHelper;

public class StartPage extends UserHelper{
	
	@FindBy(id="com.linguist:id/btnSignup")
	private static WebElement btnGetStarted;
	@FindBy(id="com.linguist:id/btnLogin")
	private static WebElement btnLogin;
	
	public StartPage() {
		PageFactory.initElements(driver, this);
	}
	
	public void clickSignup() {
		String method = "click signup";
		waitForElement(btnGetStarted);
		moveAndHighlightElement(btnGetStarted);
		btnGetStarted.click();
		takeScreenshotAndLog(driver,method,"Clicked Signup");
	}
	
	public void clickLogin() {
		String method = "click Login";
		btnLogin.click();
		takeScreenshotAndLog(driver,method,"Clicked Login");
	}
}
