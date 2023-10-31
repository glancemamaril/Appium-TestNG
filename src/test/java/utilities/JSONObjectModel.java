package utilities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

public class JSONObjectModel {
	@JsonProperty("users")
	private TestCaseData[] users;
	
	public TestCaseData[] getTestCaseData() {
		return users;
	}
}