package utilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

public class TestingIDeas {
	public static void main(String[] args) {
		String fileSeparator = File.separator;
		String scrFilePath = System.getProperty("user.dir")+fileSeparator+"extentReports"+fileSeparator+"screenshots"+fileSeparator;
		System.out.println(scrFilePath);
		
		String os = System.getProperty("os.name").toLowerCase();
		String testDataLoc = fileSeparator+"testData"+fileSeparator+"timetracker_glenn_v2.xlsx";
		
		//String appiumVersion = getAppiumVersion();
		//System.out.println(appiumVersion);
		ExcelReader creds = new ExcelReader(System.getProperty("user.dir") + testDataLoc, "Login");
		String id = "TC001_TimeTracker_Login_ValidCredentials";
		String username = creds.testData(id, "username");
    	//String password = creds.testData(id, "password");
    	//String reason = creds.testData(id, "remarks");
    	System.out.println(username);
	}

	private static String getAppiumVersion() {
		try {
			Process process = Runtime.getRuntime().exec("appium -v");
			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			StringBuilder builder = new StringBuilder();
	        String line;
	        while ((line = reader.readLine()) != null) {
	            builder.append(line);
	        }

	        try {
	            process.waitFor();
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }

	        return builder.toString().trim();
			//return reader.readLine();
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
}
