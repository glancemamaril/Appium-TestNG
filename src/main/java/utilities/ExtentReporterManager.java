package utilities;

import java.io.File;
import java.time.LocalDateTime;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReporterManager {
	static ExtentReports extent;
	public static String fileSeparator = File.separator;	//Windows uses backslash "\", macOS/Linux use regular slash "/"
	public static Properties prop;
	public static String filePath = fileSeparator+"config.properties";
	ReadConfig readConfigObj = new ReadConfig();
	public String reportName = readConfigObj.getReportName();
	public String documentTitle = readConfigObj.getDocumentTitle();
	public String tester = readConfigObj.getTester();
	public String environment = readConfigObj.getEnvironment();
	public String testType = readConfigObj.getTestType();
//	public String reportName = "HTML Report";
//	public String documentTitle = "TestNG Report";
//	public String tester = "Glenn";
//	public String environment = "QA";
//	public String testType = "Regression";
	
	public ExtentReports createInstanceExtentReports() {
		
		LocalDateTime now = LocalDateTime.now();
		String date = now.toString().replace(":",".");
		String fileName  = "Automation Report_" + date + ".html";
		String path = System.getProperty("user.dir")+fileSeparator+"extentReports"+fileSeparator+fileName;
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		
		reporter.config().setReportName(reportName);
		reporter.config().setDocumentTitle(documentTitle);
		reporter.config().setTheme(Theme.DARK);
		reporter.config().setEncoding("utf-8");
		reporter.config().setTimeStampFormat("MMM dd, yyyy HH:mm:ss");
		
		extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester",tester);
		extent.setSystemInfo("Environment Tested", environment);
		extent.setSystemInfo("Test Type", testType);
		return extent;
	}
}
