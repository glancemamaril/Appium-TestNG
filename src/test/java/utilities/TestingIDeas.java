package utilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

public class TestingIDeas {
	public static void main(String[] args) {
		String fileSeparator = File.separator;
		//String fileSeparator = "\\";
		String scrFilePath = System.getProperty("user.dir")+fileSeparator+"extentReports"+fileSeparator+"screenshots"+fileSeparator;
		System.out.println(scrFilePath);
		
		String os = System.getProperty("os.name").toLowerCase();
		
		String appiumVersion = getAppiumVersion();
		System.out.println(appiumVersion);
        
	}

	private static String getAppiumVersion() {
		try {
			Process process = Runtime.getRuntime().exec("appium -v");
			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			Thread.sleep(5000);
			return reader.readLine();
		} catch(Exception e) {
			return null;
		}
		
	}
}
