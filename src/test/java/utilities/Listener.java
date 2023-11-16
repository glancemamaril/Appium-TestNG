package utilities;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.core.tools.Generate.CustomLogger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import Frameworkbase.BaseClass;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

public class Listener extends BaseClass implements ITestListener {
	ExtentReporterManager ermObj = new ExtentReporterManager();
	ExtentTest parentTest;
	ExtentTest childTest;
	ExtentReports extent = ermObj.createInstanceExtentReports();
	public static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();
	public static String fileSeparator = File.separator; //Windows uses backslash "\", macOS/Linux use regular slash "/"
	public String fileWithPath;
	public String filePath = System.getProperty("user.dir")+fileSeparator+"extentReports"+fileSeparator+"screenshots"+fileSeparator;
	public static String methodName;
	public static String className;
	public static String SrcBase64String;
	public static LocalDateTime now = LocalDateTime.now();
	public static String dt = now.toString().replace(":",".");
	private static final Logger LOGGER = Logger.getLogger(CustomLogger.class.getName());
	private static FileHandler fileHandler;
	
	
	@Override
	public void onTestStart(ITestResult result) {
		methodName = "";
		methodName = result.getMethod().getMethodName();
		System.out.println("TEST START: "+result.getName());
		String className = result.getTestClass().getRealClass().getSimpleName();
		parentTest = extent.createTest(className);
		parentTest.assignCategory("Smoke");
		childTest = parentTest.createNode(methodName);
		extentTest.set(childTest);
		System.out.println(className);
		configureLogger(className);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		String testMethodName = result.getMethod().getMethodName();
		System.out.println("Testcase Passed :"+result.getName());
		extentTest.get().pass(MarkupHelper.createLabel("PASSED: "+testMethodName, ExtentColor.GREEN));
		LOGGER.info("Test passed: " + result.getName());
	}

	@SuppressWarnings({ "rawtypes", "unused" })
	@Override
	public void onTestFailure(ITestResult result) {
		
		String testMethodName = result.getMethod().getMethodName();
		SrcBase64String = "";
		String SrcBase64 = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BASE64);
		SrcBase64String = SrcBase64;

		// copies screenshot to destLoc
		fileWithPath = filePath + testMethodName + "_" + dt + ".png";
		File SrcFile = OutputType.FILE.convertFromBase64Png(SrcBase64);
		File DestFile = new File(fileWithPath);
		try {
			FileUtils.copyFile(SrcFile, DestFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		extentTest.get().fail(result.getThrowable(),
				MediaEntityBuilder.createScreenCaptureFromBase64String(SrcBase64String).build());
		extentTest.get().fail(MarkupHelper.createLabel("FAILED: " + testMethodName, ExtentColor.RED));
		LOGGER.severe("Test failed: " + result.getName());
		LOGGER.severe("Exception: " + result.getThrowable());
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		System.out.println("SKIPPED: "+result.getName());
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) {
		System.out.println("ENGINE START: "+context.getName());
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		System.out.println("ENGINE FINISH");
		extent.flush();
		if(fileHandler!=null) {
			fileHandler.close();
		}
	}

	private static void configureLogger(String className) {
		try {
			fileHandler = new FileHandler(System.getProperty("user.dir")+fileSeparator+"testLogs"+fileSeparator+className+".log", true);
			
			SimpleFormatter formatter = new SimpleFormatter();
			fileHandler.setFormatter(formatter);
			
			LOGGER.setLevel(Level.ALL);
			LOGGER.addHandler(fileHandler);
			
		}catch(Exception e) {
			LOGGER.log(Level.SEVERE, "Error configuring logger", e);
		}
	}
}
