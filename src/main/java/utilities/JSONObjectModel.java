package utilities;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JSONObjectModel {
	@JsonProperty("Testcases")
	private Map<String, TestCaseData> testcases;
	
	public Map<String, TestCaseData> getTestCaseData() {
		return testcases;
	}
}