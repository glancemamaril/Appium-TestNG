package utilities;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import com.aventstack.extentreports.MediaEntityBuilder;

import Frameworkbase.BaseClass;
import io.appium.java_client.AppiumDriver;

public class UserHelper {
	private final Duration TIMEOUT = Duration.ofSeconds(10);
	
	protected BaseClass bcObj = new BaseClass();
	protected AppiumDriver driver = bcObj.getDriver();
	private WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
	private JavascriptExecutor jse = (JavascriptExecutor)driver;
	
	
	public static LocalDateTime now = LocalDateTime.now();
	public static String dt = now.toString().replace(":",".");
	public static String scrFilePath = System.getProperty("user.dir")+"\\extentReports\\screenshots\\";
	public static String scrFileWithPath;
	
	public void takeScreenshotAndLog(AppiumDriver driver, String methodName, String description) {
		String SrcBase64 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
		LocalDateTime now = LocalDateTime.now();
		String dt = now.toString().replace(":",".");
		String fileSeparator = File.separator; //Windows uses backslash "\", macOS/Linux use regular slash "/"
		
		//copy screenshot to destination
		String scrFilePath = System.getProperty("user.dir")+fileSeparator+"extentReports"+fileSeparator+"screenshots"+fileSeparator;
		String scrFileWithPath = scrFilePath + methodName + "_" + dt + ".png";
		
		File ScrFile = OutputType.FILE.convertFromBase64Png(SrcBase64);
		File DestFile = new File(scrFileWithPath);
		try {
			FileUtils.copyFile(ScrFile, DestFile);
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		Listener.extentTest.get().pass(description, MediaEntityBuilder.createScreenCaptureFromBase64String(SrcBase64).build());
		Reporter.log(methodName+": "+description);
	}
	
	// Move and Highlight---------------------------------------------------
		/**
	     * Highlights the target element in the page.
	     *
	     * @param e as WebElement. Locator of the element
	     * @return void
	     */
		public void moveAndHighlightElement(WebElement e) {
			Actions actions = new Actions(driver);
			actions.moveToElement(e).build().perform();
			jse.executeScript("arguments[0].scrollIntoView(true);", e);
			jse.executeScript("arguments[0].style.border='2px solid blue'", e);
		}
	
	// Wait-----------------------------------------------------------------
		/**
	     * Wait for the element to load.
	     *
	     * @param e as WebElement. Locator of the element.
	     * @return void
	     */
		public void waitForElement(WebElement e) {
			wait.until(ExpectedConditions.visibilityOf(e));
		}
		/**
	     * Wait for the element to be clickable.
	     *
	     * @param e as WebElement. Locator of the element.
	     * @return void
	     */
		public void waitForClickable(WebElement e) {
			wait.until(ExpectedConditions.elementToBeClickable(e));
		}
		/**
	     * Wait/Pause command
	     *
	     * @param ms as integer. Pause time is in milliseconds.
	     * @return void
	     */
		public void Wait(int ms) {
			try {
				Thread.sleep(ms);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
}
