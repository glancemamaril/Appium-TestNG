package utilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.FileReader;
import java.io.Reader;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import com.fasterxml.jackson.databind.ObjectMapper;

public class TestingIDeas {
	public static void main(String[] args) {
		String fileSeparator = File.separator;
		String scrFilePath = System.getProperty("user.dir")+fileSeparator+"extentReports"+fileSeparator+"screenshots"+fileSeparator;
		String tdFilePath = System.getProperty("user.dir")+fileSeparator+"testData"+fileSeparator;
		System.out.println(scrFilePath);
		
		String os = System.getProperty("os.name").toLowerCase();
		String excelTestDataLoc = fileSeparator+"testData"+fileSeparator+"timetracker_glenn_v2.xlsx";
		String jsonTestDataLoc = tdFilePath+"derulo.json";
		String csvTestDataLoc = tdFilePath+"SampleTestData.csv";
		
		String fileExtension = getFileExtension(new File(jsonTestDataLoc));
		System.out.println(fileExtension);
		fileExtension = getFileExtension(new File(csvTestDataLoc));
		System.out.println(fileExtension);
		fileExtension = getFileExtension(new File(System.getProperty("user.dir") + excelTestDataLoc));
		System.out.println(fileExtension);
		//String appiumVersion = getAppiumVersion();
		//System.out.println(appiumVersion);
		ExcelReader creds = new ExcelReader(System.getProperty("user.dir") + excelTestDataLoc, "Login");
		String id = "TC001_TimeTracker_Login_ValidCredentials";
		String username = creds.testData(id, "username");
    	//String password = creds.testData(id, "password");
    	//String reason = creds.testData(id, "remarks");
    	System.out.println(username);
    	
    	//JSONParser parser = new JSONParser();
    	ObjectMapper objectMapper = new ObjectMapper();
    	
    	try {
    		JSONObjectModel data = objectMapper.readValue(new File(jsonTestDataLoc), JSONObjectModel.class);
    		
    		for(TestCaseData testcase : data.getTestCaseData()) {
    			System.out.println("----------");
    			System.out.println(testcase.getUsername());
    			System.out.println(testcase.getPassword());
    		}
    		System.out.println("----------");
    	}catch(IOException e) {
    		e.printStackTrace();
    	}
    	
    	CSVReader csvCreds = new CSVReader(csvTestDataLoc);
    	System.out.println(csvCreds.getData("TC002","password"));
	}
	
	private static String getFileExtension(File file) {
        String fileName = file.getName();
        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex == -1) {
            return ""; // No file extension found
        }
        return fileName.substring(dotIndex + 1);
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
