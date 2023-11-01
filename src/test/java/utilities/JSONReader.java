package utilities;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONReader {

	private static ObjectMapper objectMapper;
	private static JSONObjectModel fullData;
	
	public JSONReader(String filePath) {
		getJSONMap(filePath);
	}

	private static void getJSONMap(String jsonFilePath) {
    	try {
    		objectMapper = new ObjectMapper();
    		fullData = objectMapper.readValue(new File(jsonFilePath), JSONObjectModel.class);
    	}catch(IOException e) {
    		e.printStackTrace();
    	}
	}
	
	public TestCaseData getTestCaseData(String testCaseName) {
		TestCaseData testcaseData = fullData.getTestCaseData().get(testCaseName);
		return testcaseData;
	}
}
