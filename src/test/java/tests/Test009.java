package tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.MediaEntityModelProvider;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import config.PropertiesFile;
import webPages.CartPageObjects;
import webPages.DisscountProductPageObjects;
import webPages.CheckOutPageObjects;

@Test (groups = {"Cart", "Business"})
public class Test009 {

	private WebDriver driver;
	private WebElement validator;
	private DisscountProductPageObjects disscountProductPageObjects;
	private CartPageObjects cartPageObjects;
	private CheckOutPageObjects checkOutPageObjects;
	private String productPageURL;
	private String cartPageURL;

	private String demoEmail;
	private String demoFirstName;
	private String demoLastName;
	private String demoAddress;
	private String demoCity;
	private String demoZIPCode;


	@BeforeClass
	public void setUpTest() {
				
		try {

			System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver/chromedriver.exe");
			driver = new ChromeDriver(); 
			
			disscountProductPageObjects = new DisscountProductPageObjects(driver);
			productPageURL = disscountProductPageObjects.getURL();
			
			demoEmail = PropertiesFile.getProperty("demoEmail");
			demoFirstName = PropertiesFile.getProperty("demoFirstName");
			demoLastName = PropertiesFile.getProperty("demoLastName");
			demoAddress = PropertiesFile.getProperty("demoAddress");
			demoCity = PropertiesFile.getProperty("demoCity");
			demoZIPCode = PropertiesFile.getProperty("demoZIPCode");
		}
		
		catch (Exception exp) {
			
			System.out.println("Exception: " + exp.getMessage());
		}
	}
	
	@AfterClass
	public void tearDownTest() {
		
		try {
			driver.close();
			driver.quit();
		}
		
		catch (Exception exp) {
			
			System.out.println(exp.getMessage());
		}
	}
	
	@AfterMethod
	public void clearAll() {
		
		checkOutPageObjects.clearTextInZipCode();
		checkOutPageObjects.setState(0);
		checkOutPageObjects.clearTextInEmailOrMobile();
		checkOutPageObjects.clearTextInFirstName();
		checkOutPageObjects.clearTextInLastName();
		checkOutPageObjects.clearTextInAddress();
		checkOutPageObjects.clearTextInCity();
	}
	
	@Test (priority = 1)
	public void testEmailOrMobile() {

		try {
			//add a product to cart
			driver.get(productPageURL);
			disscountProductPageObjects.clickAddToCart();
			
			new WebDriverWait(driver, 10).until(driver -> driver.findElement(By.xpath("//div[@id='ajaxifyCart']/form/h1")));
					
			cartPageObjects = new CartPageObjects(driver);
			cartPageURL = cartPageObjects.getURL();
			//click Checkout
			driver.get(cartPageURL);
			cartPageObjects.clickCheckout();
			
			new WebDriverWait(driver, 15).until(driver -> driver.findElement(By.xpath("//span[@class='btn__content']")));

			checkOutPageObjects = new CheckOutPageObjects(driver);
			//fill all fields but "Email/Mobile"
			checkOutPageObjects.setTextInFirstName(demoFirstName);
			checkOutPageObjects.setTextInLastName(demoLastName);
			checkOutPageObjects.setTextInAddress(demoAddress);
			checkOutPageObjects.setTextInCity(demoCity);
			checkOutPageObjects.setState(0);

			checkOutPageObjects.clearTextInEmailOrMobile();
			checkOutPageObjects.clickContinueToShipping();
			checkOutPageObjects.clearTextInEmailOrMobile();
			//validate empty field message
			validator = new WebDriverWait(driver, 15).until(driver -> driver.findElement(By.id("error-for-email_or_phone")));			
			assertEquals(validator.getText().toLowerCase(), "Enter an email or mobile phone number".toLowerCase());
		}

		catch (Exception exp) {
			
			System.out.println("1: " + exp.getMessage());
		}
	}
	
	@Test (priority = 2)
	public void testState() {

		try {
			//fill all fields but "State"
			checkOutPageObjects.setState(0);
			checkOutPageObjects.setTextInEmailOrMobile(demoEmail);
			checkOutPageObjects.setTextInFirstName(demoFirstName);
			checkOutPageObjects.setTextInLastName(demoLastName);
			checkOutPageObjects.setTextInAddress(demoAddress);
			checkOutPageObjects.setTextInCity(demoCity);
			
			
			new WebDriverWait(driver, 3);
			checkOutPageObjects.clickContinueToShipping();
			//validate empty field message
			validator = new WebDriverWait(driver, 15).until(driver -> driver.findElement(By.id("error-for-province")));			
			assertEquals(validator.getText().toLowerCase(), "Select a state / province".toLowerCase());
		}

		catch (Exception exp) {
			
			System.out.println("2: " + exp.getMessage());
		}
	}
	
	@Test (priority = 3)
	public void testFirstName() {

		try {
			//fill all fields but "First Name"
			checkOutPageObjects.setTextInEmailOrMobile(demoEmail);
			checkOutPageObjects.setTextInLastName(demoLastName);
			checkOutPageObjects.setTextInAddress(demoAddress);
			checkOutPageObjects.setTextInCity(demoCity);
			checkOutPageObjects.setState(0);
			checkOutPageObjects.setTextInZipCode(demoZIPCode);
			
			checkOutPageObjects.clickContinueToShipping();
			//validate empty field message
			validator = new WebDriverWait(driver, 15).until(driver -> driver.findElement(By.id("error-for-first_name")));			
			assertEquals(validator.getText().toLowerCase(), "Enter a first name".toLowerCase());
		}

		catch (Exception exp) {
			
			System.out.println("3: " + exp.getMessage());
		}
	}
	
	@Test (priority = 4)
	public void testLastName() {

		try {
			//fill all fields but "Last Name"
			checkOutPageObjects.setTextInEmailOrMobile(demoEmail);
			checkOutPageObjects.setTextInFirstName(demoFirstName);
			checkOutPageObjects.setTextInAddress(demoAddress);
			checkOutPageObjects.setTextInCity(demoCity);
			checkOutPageObjects.setState();
			checkOutPageObjects.setTextInZipCode(demoZIPCode);
			
			checkOutPageObjects.clickContinueToShipping();
			//validate empty field message
			validator = new WebDriverWait(driver, 15).until(driver -> driver.findElement(By.id("error-for-last_name")));			
			assertEquals(validator.getText().toLowerCase(), "Enter a last name".toLowerCase());
		}

		catch (Exception exp) {
			
			System.out.println("4: " + exp.getMessage());
		}
	}
	
	@Test (priority = 5)
	public void testAddress() {

		try {
			//fill all fields but "Address"
			checkOutPageObjects.setTextInEmailOrMobile(demoEmail);
			checkOutPageObjects.setTextInFirstName(demoFirstName);
			checkOutPageObjects.setTextInLastName(demoLastName);
			checkOutPageObjects.setTextInCity(demoCity);
			checkOutPageObjects.setState();
			checkOutPageObjects.setTextInZipCode(demoZIPCode);
			
			checkOutPageObjects.clickContinueToShipping();
			//validate empty field message
			validator = new WebDriverWait(driver, 15).until(driver -> driver.findElement(By.id("error-for-address1")));			
			assertEquals(validator.getText().toLowerCase(), "Enter an address".toLowerCase());
		}

		catch (Exception exp) {
			
			System.out.println("5: " + exp.getMessage());
		}
	}
	
	@Test (priority = 6)
	public void testCity() {

		try {
			//fill all fields but "City"
			checkOutPageObjects.setTextInEmailOrMobile(demoEmail);
			checkOutPageObjects.setTextInFirstName(demoFirstName);
			checkOutPageObjects.setTextInLastName(demoLastName);
			checkOutPageObjects.setTextInAddress(demoAddress);
			checkOutPageObjects.setState();
			checkOutPageObjects.setTextInZipCode(demoZIPCode);
			
			checkOutPageObjects.clickContinueToShipping();
			//validate empty field message
			validator = new WebDriverWait(driver, 15).until(driver -> driver.findElement(By.id("error-for-city")));			
			assertEquals(validator.getText().toLowerCase(), "Enter a city".toLowerCase());
		}

		catch (Exception exp) {
			
			System.out.println("6: " + exp.getMessage());
		}
	}
	

	@Test (priority = 7)
	public void testZIPCode() {

		try {
			//fill all fields but "Zip Code"
			checkOutPageObjects.setTextInEmailOrMobile(demoEmail);
			checkOutPageObjects.setTextInFirstName(demoFirstName);
			checkOutPageObjects.setTextInLastName(demoLastName);
			checkOutPageObjects.setTextInAddress(demoAddress);
			checkOutPageObjects.setTextInCity(demoCity);
			checkOutPageObjects.setState();
			
			checkOutPageObjects.clickContinueToShipping();
			//validate empty field message
			validator = new WebDriverWait(driver, 15).until(driver -> driver.findElement(By.id("error-for-zip")));			
			assertEquals(validator.getText().toLowerCase(), "Enter a ZIP / postal code".toLowerCase());
		}

		catch (Exception exp) {
			
			System.out.println("7: " + exp.getMessage());
		}
	}
	
}
