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
import webPages.PaymentPageObjects;
import webPages.CheckOutPageObjects;

@Test (groups = {"Business", "Links"})
public class Test010 {

	private WebDriver driver;
	private WebElement operator;
	private WebElement validator;
	private DisscountProductPageObjects disscountProductPageObjects;
	private CartPageObjects cartPageObjects;
	private CheckOutPageObjects checkOutPageObjects;
	private PaymentPageObjects paymentPageObjects;
	private String productPageURL;
	private String cartPageURL;

	private String demoEmail;
	private String demoFirstName;
	private String demoLastName;
	private String demoAddress;
	private String demoCity;
	private String demoZIPCode;
	private String refundPolicy;
	private String shippingPolicy;
	private String privacyPolicy;
	private String termsOfService;

	
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
			refundPolicy = PropertiesFile.getProperty("refundPolicy");
			shippingPolicy = PropertiesFile.getProperty("shippingPolicy");
			privacyPolicy = PropertiesFile.getProperty("privacyPolicy");
			termsOfService = PropertiesFile.getProperty("termsOfService");

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
	public void closePopup() {
		
		driver.navigate().back();
		operator = new WebDriverWait(driver, 15).until(driver -> driver.findElement(By.id("continue_button")));
		operator.click();
		
	}
	
	@Test (priority = 1)
	public void testRefundPolicy() {

		try {
			//add a product to cart
			driver.get(productPageURL);
			disscountProductPageObjects.clickAddToCart();
			
			new WebDriverWait(driver, 10).until(driver -> driver.findElement(By.xpath("//div[@id='ajaxifyCart']/form/h1")));
					
			cartPageObjects = new CartPageObjects(driver);
			cartPageURL = cartPageObjects.getURL();
			//Checkout
			driver.get(cartPageURL);
			cartPageObjects.clickCheckout();
			
			new WebDriverWait(driver, 15).until(driver -> driver.findElement(By.xpath("//span[@class='btn__content']")));

			checkOutPageObjects = new CheckOutPageObjects(driver);
			//fill nessecary fields
			checkOutPageObjects.setTextInEmailOrMobile(demoEmail);
			checkOutPageObjects.setTextInFirstName(demoFirstName);
			checkOutPageObjects.setTextInLastName(demoLastName);
			checkOutPageObjects.setTextInAddress(demoAddress);
			checkOutPageObjects.setTextInCity(demoCity);
			checkOutPageObjects.setTextInZipCode(demoZIPCode);
			//Continue to Shipping
			checkOutPageObjects.clickContinueToShipping();
			//click Continue
			operator = new WebDriverWait(driver, 15).until(driver -> driver.findElement(By.id("continue_button")));

			operator.click();

			paymentPageObjects = new PaymentPageObjects(driver);
			//click "Refund Policy" link
			paymentPageObjects.clickRefundPolicy();
			//validate "Refund Policy" view
			validator = new WebDriverWait(driver, 10).until(driver -> driver.findElement(By.id("modal-title")));				
			assertNotNull(validator);
		}

		catch (Exception exp) {
			
			System.out.println("1: " + exp.getMessage());
		}
	}
	
	@Test (priority = 2)
	public void testShippingPolicy() {

		try {
			//click "Shipping Policy" link
			paymentPageObjects.clickShippingPolicy();
			//validate "Shipping Policy" view
			validator = new WebDriverWait(driver, 10).until(driver -> driver.findElement(By.id("modal-title")));				
			assertNotNull(validator);
		}

		catch (Exception exp) {
			
			System.out.println("2: " + exp.getMessage());
		}
	}
	
	@Test (priority = 3)
	public void testPrivacyPolicy() {

		try {
			//click "Privacy Policy" link
			paymentPageObjects.clickPrivacyPolicy();
			//validate "Privacy Policy" view
			validator = new WebDriverWait(driver, 10).until(driver -> driver.findElement(By.id("modal-title")));				
			assertNotNull(validator);
		}

		catch (Exception exp) {
			
			System.out.println("3: " + exp.getMessage());
		}
	}
	
	@Test (priority = 4)
	public void testTermsOfService() {

		try {
			//click "Terms Of Service" link
			paymentPageObjects.clickTermsOfService();
			//validate "Terms Of Service" view
			validator = new WebDriverWait(driver, 10).until(driver -> driver.findElement(By.id("modal-title")));				
			assertNotNull(validator);
		}

		catch (Exception exp) {
			
			System.out.println("4: " + exp.getMessage());
		}
	}
}