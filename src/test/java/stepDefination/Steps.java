package stepDefination;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import cucumber.api.java.en.*;
import pageObjects.AddcustomerPage;
import pageObjects.LoginPage;
import pageObjects.SearchCustomerPage;

public class Steps extends BaseClass {

//	public WebDriver driver;
//	public LoginPage lp;

	@Given("User Launch Chrome browser")
	public void user_Launch_Chrome_browser() {

		logger = Logger.getLogger("nopCommerce"); // Added logger
		PropertyConfigurator.configure("log4j.properies"); // Added logger

		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "//Drivers/chromedriver.exe");
		driver = new ChromeDriver();
		logger.info("************** Launching Browser ***************");
		lp = new LoginPage(driver);

	}

	@When("User opens URL {string}")
	public void user_opens_URL(String url) throws InterruptedException {
		logger.info("************* Opening URL ***************");
		driver.get(url);
		driver.manage().window().maximize();
		Thread.sleep(2000);
	}

	@When("User enters Email as {string} and Password as {string}")
	public void user_enters_Email_as_and_Password_as(String email, String password) throws InterruptedException {
		logger.info("************** Providing Login details ***************");
		lp.setUserName(email);
		lp.setPassword(password);
	}

	@When("Click on Login")
	public void click_on_Login() throws InterruptedException {
		logger.info("************** Started login ***************");
		lp.clickLogin();
		Thread.sleep(2000);
	}

	@Then("Page Title should be {string}")
	public void page_Title_should_be(String title) throws InterruptedException {
		if (driver.getPageSource().contains("Login was unsuccessful.")) {
			driver.close();
			logger.info("************* Login Pass ***************");
			Assert.assertTrue(false);
		} else {
			logger.info("************** Login Fail ***************");
			Assert.assertEquals(title, driver.getTitle());
		}
		Thread.sleep(3000);
	}

	@When("User click on Log out link")
	public void user_click_on_Log_out_link() throws InterruptedException {
		logger.info("************** Click on logout link ***************");
		lp.clickLogout();
		Thread.sleep(3000);
	}

	@Then("close browser")
	public void close_browser() {
		logger.info("************** Closing browser ***************");
		driver.quit();
	}

//	Customer feature step definition

	@Then("User can view Dashboard")
	public void user_can_view_Dashboard() {

		addCust = new AddcustomerPage(driver);

		Assert.assertEquals("Dashboard / nopCommerce administration", addCust.getPageTitle());
	}

	@When("User Click on customers Menu")
	public void user_Click_on_customers_Menu() throws InterruptedException {
		Thread.sleep(3000);
		addCust.clickOnCustomersMenu();
	}

	@When("click on customers Menu Item")
	public void click_on_customers_Menu_Item() throws InterruptedException {
		Thread.sleep(2000);
		addCust.clickOnCustomersMenuItem();
	}

	@When("click on add new button")
	public void click_on_add_new_button() throws InterruptedException {
		addCust.clickOnAddNew();
		Thread.sleep(2000);
	}

	@Then("User can view Add new customer page")
	public void user_can_view_Add_new_customer_page() {
		Assert.assertEquals("Add a new customer / nopCommerce administration", addCust.getPageTitle());
	}

	@When("User enter customer info")
	public void user_enter_customer_info() throws InterruptedException {
		logger.info("************** Adding new Customer ***************");
		logger.info("************** Providing Customer Details ***************");

		String email = randomestring() + "@gmail.com";
		addCust.setEmail(email);
		addCust.setPassword("test123");
//	  Registered  - default
//	  The customer cannot be in both 'Guests' or 'Registered' customer role
//	  Add the customer to 'Guests' or 'Registered' customer role
		addCust.setCustomerRoles("Administrators");
		Thread.sleep(3000);
		addCust.setManagerVendor("Vendor 2");
		addCust.setGender("Male");
		addCust.setFirstName("Aryan");
		addCust.setLastName("Chavan");
		addCust.setDob("7/05/1988");
		addCust.setCompanyName("busyQA");
		addCust.setAdminContent("This is for testing...............");
	}

	@When("click on Save button")
	public void click_on_Save_button() throws InterruptedException {
		logger.info("************** Saving customer data ***************");
		addCust.clickOnSave();
		Thread.sleep(2000);
	}

	@Then("User can view confirmation message {string}")
	public void user_can_view_confirmation_message(String msg) {
		Assert.assertTrue(driver.findElement(By.tagName("body")).getText()
				.contains("The new customer has been added successfully."));
	}

//	Steps for searching a customer using Email ID.

	@When("Enter customer Email")
	public void enter_customer_Email() {
		logger.info("************** Searching a customer by using email Id ***************");

		searchCust = new SearchCustomerPage(driver);
		searchCust.setEmail("victoria_victoria@nopCommerce.com");
	}

	@When("Click on search button")
	public void click_on_search_button() throws InterruptedException {
		searchCust.clickSearch();
		Thread.sleep(3000);
	}

	@Then("User should found Email in the search table")
	public void user_should_found_Email_in_the_search_table() {
		boolean status = searchCust.searchCustomerByEmail("victoria_victoria@nopCommerce.com");
		Assert.assertEquals(true, status);
	}

//	Steps for searching a customer using First Name and LastName

	@When("Enter customer FirstName")
	public void enter_customer_FirstName() {
		logger.info("************** Searching a customer by Name ***************");
		searchCust = new SearchCustomerPage(driver);
		searchCust.setFirstName("Victoria");
	}

	@When("Enter customer LastName")
	public void enter_customer_LastName() {
		searchCust.setLastName("Terces");
	}

	@Then("User should found Name in the search table")
	public void user_should_found_Name_in_the_search_table() {
		boolean status = searchCust.searchCustomerByName("Victoria Terces");
		Assert.assertEquals(true, status);

	}
}
