package pages.lingq;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.UserHelper;

public class LoginPage extends UserHelper{
	
	@FindBy(id="com.linguist:id/tvUsername")
	private static WebElement txtUserNameEmail;
	@FindBy(id="com.linguist:id/tvPassword")
	private static WebElement txtPassword;
	@FindBy(id="com.linguist:id/email_sign_in_button")
	private static WebElement btnLogin;
	@FindBy(id="com.linguist:id/view_forgot_password")
	private static WebElement lnkForgotPassword;
	
	public LoginPage() {
		PageFactory.initElements(driver, this);
	}
	
	public void loginToAccount(String usernameEmail, String password) {
		enterUsernameOrEmail(usernameEmail);
		enterPassword(password);
		btnLogin.click();
	}
	
	public void enterUsernameOrEmail(String userNameOrEmail) {
		String method = "enter username or email";
		txtUserNameEmail.sendKeys(userNameOrEmail);
		takeScreenshotAndLog(driver,method,"entered username or email");
	}
	
	public void enterPassword(String password) {
		String method = "enter password";
		txtPassword.sendKeys(password);
		takeScreenshotAndLog(driver,method,"entered password");
	}
}
