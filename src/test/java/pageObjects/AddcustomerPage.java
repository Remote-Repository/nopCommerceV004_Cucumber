package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class AddcustomerPage {

public WebDriver ldriver;
	
	public AddcustomerPage(WebDriver rdriver)
	{
		ldriver = rdriver;
		PageFactory.initElements(ldriver, this);
	}
	
	By lnkCustomers_menu=By.xpath("(//i[@class='right fas fa-angle-left '])[4]");
	By lnkCustomers_menuitem=By.xpath("(//i[@class='nav-icon far fa-dot-circle'])[13]");

	By btnAddnew=By.xpath("//a[@class='btn btn-primary']"); //Add new
	
	By txtEmail=By.xpath("//input[@id='Email']");
	By txtPassword=By.xpath("//input[@id='Password']");
	
	By txtcustomerRoles = By.xpath("(//div[@class='k-multiselect-wrap k-floatwrap'])[2]");
	
	By lstitemAdministrators=By.xpath("//li[contains(text(),'Administrators')]");
	By lstitemRegistered=By.xpath("//li[contains(text(),'Registered')]");
	By lstitemGuests=By.xpath("//li[contains(text(),'Guest')]");
	By lstitemVendors=By.xpath("//li[contains(text(),'Vendors')]");
	
	By drpmgrOfVendor=By.xpath("//*[@id='VendorId']");
	By rdMaleGender=By.id("Gender_Male");
	By rdFeMaleGender=By.id("Gender_Female");
	
	By txtFirstName=By.xpath("//input[@id='FirstName']");
	By txtLastName=By.xpath("//input[@id='LastName']");
	
	By txtDob=By.xpath("//input[@id='DateOfBirth']");
	
	By txtCompanyName=By.xpath("//input[@id='Company']");
	
	By txtAdminContent=By.xpath("//textarea[@id='AdminComment']");
	
	By btnSave = By.xpath("//button[@name='save']");
	
//Action Methods
	
	public String getPageTitle() {
		return ldriver.getTitle();
	}
	
	public void clickOnCustomersMenu() {
		ldriver.findElement(lnkCustomers_menu).click();
	}
	
	public void clickOnCustomersMenuItem() {
		ldriver.findElement(lnkCustomers_menuitem).click();
	}
	
	public void clickOnAddNew() {
		ldriver.findElement(btnAddnew).click();
	}
	
	public void setEmail(String email) {
		ldriver.findElement(txtEmail).sendKeys(email);
	}
	
	public void setPassword(String password) {
		ldriver.findElement(txtPassword).sendKeys(password);
	}
	
	public void setCustomerRoles(String role) throws InterruptedException {
		if(!role.equals("vendors"))  //if role is vendors should not delete Register
		{
			ldriver.findElement(By.xpath("//ul[@id='SelectedCustomerRoleIds_taglist']/li/span[1]"));
		}
	
		ldriver.findElement(txtcustomerRoles).click();
		
		WebElement listitem;
		
		Thread.sleep(3000);
		
		if(role.equals("Administrators")) {
			listitem=ldriver.findElement(lstitemAdministrators);
		}
		else if(role.equals("Guests")) {
			listitem=ldriver.findElement(lstitemGuests);
		}
		else if(role.equals("Rgistered")) {
			listitem=ldriver.findElement(lstitemRegistered);
		}
		else if(role.equals("Vendors")) {
			listitem=ldriver.findElement(lstitemVendors);
		}
		else {
			listitem=ldriver.findElement(lstitemGuests);
		}
//		listitem.click();
//		Thread.sleep(3000);
		
		JavascriptExecutor js = (JavascriptExecutor)ldriver;
		js.executeScript("arguments[0].click();", listitem);
		
	}
	
	public void setManagerVendor(String value) {
		Select drp = new Select(ldriver.findElement(drpmgrOfVendor));
		drp.selectByVisibleText(value);
	}
	
	public void setGender(String gender) {
		if(gender.equals("Male")) {
			ldriver.findElement(rdMaleGender).click();
		}
		else if(gender.equals("Female")) {
			ldriver.findElement(rdFeMaleGender).click();
		}
		else {
			ldriver.findElement(rdMaleGender).click();  //Default
		}
	}
	
	public void setFirstName(String fname) {
		ldriver.findElement(txtFirstName).sendKeys(fname);
	}
	
	public void setLastName(String lname) {
		ldriver.findElement(txtLastName).sendKeys(lname);
	}
	
	public void setDob(String dob) {
		ldriver.findElement(txtDob).sendKeys(dob);
	}
	
	public void setCompanyName(String company) {
		ldriver.findElement(txtCompanyName).sendKeys(company);
	}
	
	public void setAdminContent(String information) {
		ldriver.findElement(txtAdminContent).sendKeys(information);
	}
	
	public void clickOnSave() {
		ldriver.findElement(btnSave).click();
	}



}
