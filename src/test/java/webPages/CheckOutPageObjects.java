package webPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;


public class CheckOutPageObjects {
	
	private By button_continue_to_shipping = By.id("continue_button");
	private By textbox_email_or_mobile = By.id("checkout_email_or_phone");
	private By textbox_first_name = By.id("checkout_shipping_address_first_name");
	private By textbox_last_name = By.id("checkout_shipping_address_last_name");
	private By textbox_address = By.id("checkout_shipping_address_address1");
	private By textbox_city = By.id("checkout_shipping_address_city");
	private By textbox_state = By.id("checkout_shipping_address_province");
	private By textbox_zip_code = By.id("checkout_shipping_address_zip");
	
	private WebDriver driver;

	
	
	public CheckOutPageObjects(WebDriver driver) {
		
		this.driver = driver;
	}
	
	

	public void clickContinueToShipping() {
		
		driver.findElement(button_continue_to_shipping).click();
	}
	
	public void setTextInEmailOrMobile(String text) {
		
		driver.findElement(textbox_email_or_mobile).sendKeys(text);
	}
	
	public void setTextInFirstName(String text) {
		
		driver.findElement(textbox_first_name).sendKeys(text);
	}
	
	public void setTextInLastName(String text) {
		
		driver.findElement(textbox_last_name).sendKeys(text);
	}
	
	public void setTextInAddress(String text) {
		
		driver.findElement(textbox_address).sendKeys(text);
	}
	
	public void setTextInCity(String text) {
		
		driver.findElement(textbox_city).sendKeys(text);
	}
	
	public void setState() {
		
		Select drpState = new Select(driver.findElement(textbox_state));
		drpState.selectByIndex(3);
	}
	
	public void setState(int index) {
		
		Select drpState = new Select(driver.findElement(textbox_state));
		drpState.selectByIndex(index);
	}
	
	public void setTextInZipCode(String text) {
		
		driver.findElement(textbox_zip_code).sendKeys(text);
	}
	
	public void clearTextInEmailOrMobile() {
		
		driver.findElement(textbox_email_or_mobile).clear();
	}
	
	public void clearTextInFirstName() {
		
		driver.findElement(textbox_first_name).clear();
	}
	
	public void clearTextInLastName() {
		
		driver.findElement(textbox_last_name).clear();
	}
	
	public void clearTextInAddress() {
		
		driver.findElement(textbox_address).clear();
	}
	
	public void clearTextInCity() {
		
		driver.findElement(textbox_city).clear();
	}
	
	public void clearTextInZipCode() {
		
		driver.findElement(textbox_zip_code).clear();
	}
}