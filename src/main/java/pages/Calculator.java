package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Frameworkbase.BaseClass;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import utilities.UserHelper;

public class Calculator extends UserHelper{
	public AppiumDriver driver;
	/*----------Toolbar----------*/
//	@AndroidFindBy(xpath="//android.widget.ImageButton[@content-desc=\"More options\"]")
//	private static WebElement options;
//	
//	/*----------More Options----------*/
//	@AndroidFindBy(xpath="/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.LinearLayout[1]/android.widget.RelativeLayout/android.widget.TextView")
//	private static WebElement history;
//	
//	@AndroidFindBy(xpath="/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.LinearLayout[2]/android.widget.RelativeLayout/android.widget.TextView")
//	private static WebElement openSourceLicenses;
	
	/*----------Calculator Functions----------*/
	@FindBy(id="com.android.calculator2:id/op_add")
	private static WebElement add;
	
	@FindBy(id="com.android.calculator2:id/op_sub")
	private static WebElement subtract;
	
	@FindBy(id="com.android.calculator2:id/op_mul")
	private static WebElement multiply;
	
	@FindBy(id="com.android.calculator2:id/op_div")
	private static WebElement divide;
	
	@FindBy(id="com.android.calculator2:id/clr")
	private static WebElement clear;
	
	@FindBy(id="com.android.calculator2:id/eq")
	private static WebElement equal;
	
	@FindBy(id="com.android.calculator2:id/dec_point")
	private static WebElement decimal;
	
	@FindBy(id="com.android.calculator2:id/del")
	private static WebElement delete;
	
	@FindBy(id="com.android.calculator2:id/formula")
	private static WebElement formula;
	
	@FindBy(id="com.android.calculator2:id/result")
	private static WebElement result;
	
	@FindBy(id="com.android.calculator2:id/pad_advanced")
	private static WebElement advancedOperations;
	
	/*----------Advanced Functions----------*/
	@FindBy(id="com.android.calculator2:id/toggle_inv")
	private static WebElement inv;
	
	@FindBy(id="com.android.calculator2:id/toggle_mode")
	private static WebElement angleMode;
	
	@FindBy(id="com.android.calculator2:id/op_pct")
	private static WebElement percent;
	
	@FindBy(id="com.android.calculator2:id/fun_sin")
	private static WebElement trigSine;
	
	@FindBy(id="com.android.calculator2:id/fun_cos")
	private static WebElement trigCosine;
	
	@FindBy(id="com.android.calculator2:id/fun_tan")
	private static WebElement tigTangent;
	
	@FindBy(id="com.android.calculator2:id/fun_ln")
	private static WebElement naturalLogarithm;
	
	@FindBy(id="com.android.calculator2:id/fun_log")
	private static WebElement logarithm;
	
	@FindBy(id="com.android.calculator2:id/op_fact")
	private static WebElement factorial;
	
	@FindBy(id="com.android.calculator2:id/const_pi")
	private static WebElement pi;
	
	@FindBy(id="com.android.calculator2:id/const_e")
	private static WebElement eulerNumber;
	
	@FindBy(id="com.android.calculator2:id/op_pow")
	private static WebElement power;
	
	@FindBy(id="com.android.calculator2:id/lparen")
	private static WebElement leftParenthesis;
	
	@FindBy(id="com.android.calculator2:id/rparen")
	private static WebElement rightParethesis;
	
	@FindBy(id="com.android.calculator2:id/op_sqrt")
	private static WebElement squareRoot;
	
	public Calculator(AppiumDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		System.out.println("Scan complete");
	}
	
	
	public void parseExpression(String expression) {
		String method = "parsingStatement";
		int index=0;
		char character;
		do {
			character = expression.charAt(index);
			System.out.println(character);
			if(Character.isDigit(character)) {
				clickNumber(character);
			}else {
				operation(character);
			}
			index++;
		}while(index!=expression.length());
		takeScreenshotAndLog(driver,method,"finished parsing statement");
	}
	
	public void clickNumber(char number) {
		String method = "number";
		WebElement numberElement = driver.findElement(By.id("com.android.calculator2:id/digit_"+number));
		numberElement.click();
		takeScreenshotAndLog(driver,method,"clicked "+number);
	}
	
	public void operation(char expression) {
		String method = "operation";
		switch(expression) {
		case '+':
			add.click();
			break;
		case '-':
			subtract.click();
			break;
		case '*':
			multiply.click();
			break;
		case '/':
			divide.click();
			break;
		case '=':
			equal.click();
			break;
		default:
			throw new Error("Character not valid!");	
		}
		takeScreenshotAndLog(driver,method,"clicked "+expression);
	}
	
	public boolean verifyResult() {
		String method = "verify_result";
		boolean isResultShown = !(result.getText()).isEmpty();
		if(isResultShown) {
			System.out.println(result.getText());
		}else {
			throw new Error("No data!");
		}
		takeScreenshotAndLog(driver,method,"verified result");
		return isResultShown;
	}
}
