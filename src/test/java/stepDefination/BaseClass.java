package stepDefination;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;


import pageObjects.AddcustomerPage;
import pageObjects.LoginPage;
import pageObjects.SearchCustomerPage;

public class BaseClass {

	public WebDriver driver;
	public LoginPage lp;
	public AddcustomerPage addCust;
	public SearchCustomerPage searchCust;
	public static Logger logger;
	
	//This additional method Created for generating random string Unique email id
	public static String randomestring() {
		String  generatedString1 = RandomStringUtils.randomAlphabetic(5);
		return (generatedString1);
	}
}
