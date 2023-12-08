package pages.GeneralStore;

import org.openqa.selenium.By;
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

public class StartPage extends UserHelper{
	//public AppiumDriver driver;
	
	/*----------Top Menu Toolbar----------*/
	@FindBy(id = "com.androidsample.generalstore:id/spinnerCountry")
	private static WebElement ddCountry;
	@FindBy(id = "com.androidsample.generalstore:id/nameField")
	private static WebElement txtName;
	@FindBy(id = "com.androidsample.generalstore:id/radioMale")
	private static WebElement rdbtnMale;
	@FindBy(id = "com.androidsample.generalstore:id/radioFemale")
	private static WebElement rdbtnFemale;
	@FindBy(id = "com.androidsample.generalstore:id/btnLetsShop")
	private static WebElement btnLetsShop;
	
	public StartPage() {
		PageFactory.initElements(driver, this);
	}
	
	public void enterGeneralStoreDetails(String name, String country, String gender) {
		String method = "Entering details";
		enterName(name);
		chooseCountryFromDropdown(country);
		enterGender(gender);
		btnLetsShop.click();
		takeScreenshotAndLog(driver,method,"entered complete details");
	}
	
	private void enterGender(String option) {
		String method = "Click from top Menu";
		switch(option.toUpperCase()) {
			case "MALE":
				waitForElement(rdbtnMale);
				rdbtnMale.click();
				System.out.println("Clicked Male");
				break;
			case "FEMALE":
				waitForElement(rdbtnFemale);
				rdbtnFemale.click();
				System.out.println("Clicked Female");
				break;	
			default:
				throw new Error("Option not in Menu!");
		}
		takeScreenshotAndLog(driver,method,"clicked "+option);
	}
	
	private void chooseCountryFromDropdown(String countryName) {
		String method = "country dropdown";
		waitForElement(ddCountry);
		ddCountry.click();
		Wait(2);
		WebElement countryList = driver.findElement(By.className("android.widget.ListView"));
		WebElement country = findAndScrollUntilVisible(countryList,countryName);
		country.click();
		takeScreenshotAndLog(driver,method,"chose country");
	}
	
	private void enterName(String name) {
		String method = "enter name";
		waitForElement(txtName);
		txtName.clear();
		txtName.sendKeys(name);
		takeScreenshotAndLog(driver,method,"entered Name");
	}
}
