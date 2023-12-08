package utilities;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TestCaseData {
	/*----------Test Data----------*/
	/*Enter the test data to be used here.
	 * All test data in the JSON file should be declared, regardless if going to be used or not
	 * Declaration here allows the data to be nullable, and/or the ability to not be placed in TCs that don't require them*/
	@JsonProperty("username")
	private String username;
	@JsonProperty("password")
	private String password;
	@JsonProperty("sex")
	private String sex;
	
	/*----------Functions for extracting Test Data----------*/
	/*Enter at getTestData functions here
	 * Note that we should only extract test data, there is no need to set new test data in the JSON file*/
	public String getUsername() {
		return username;
	}	
	public String getPassword() {
		return password;
	}	
	public String getSex() {
		return sex;
	}
}