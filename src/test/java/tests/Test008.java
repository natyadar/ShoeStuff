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

@Test (groups = {"Cart", "Business"})
public class Test008 {

	private WebDriver driver;
	private WebElement validator;
	private DisscountProductPageObjects disscountProductPageObjects;
	private CartPageObjects cartPageObjects;
	private String productPageURL;
	private String cartPageURL;

	
	@BeforeClass
	public void setUpTest() {
				
		try {

			System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver/chromedriver.exe");
			driver = new ChromeDriver(); 
			
			disscountProductPageObjects = new DisscountProductPageObjects(driver);
			productPageURL = disscountProductPageObjects.getURL();
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
	
	@Test (priority = 1)
	public void testCartForQuantity() {

		try {
			//add specific product to cart
			driver.get(productPageURL);
			disscountProductPageObjects.clickAddToCart();
			
			new WebDriverWait(driver, 10).until(driver -> driver.findElement(By.xpath("//div[@id='ajaxifyCart']/form/h1")));
					
			cartPageObjects = new CartPageObjects(driver);
			cartPageURL = cartPageObjects.getURL();
			//add to quantity
			driver.get(cartPageURL);
			cartPageObjects.clickAddQuantity();
			//validate added quantity in the view
			validator = new WebDriverWait(driver, 10).until(driver -> driver.findElement(By.xpath("//div[@class='cart-row' and @data-line='1']//input[@class='js--num']")));			
			assertEquals(validator.getAttribute("value"), "2");
		}

		catch (Exception exp) {
			
			System.out.println("Exception: " + exp.getMessage());
		}
	}
	
	@Test (priority = 2)
	public void testCartUpdate() {

		try {
			//update cart
			cartPageObjects.clickUpdateCart();
			driver.get(cartPageURL);
			//validate updated quantity
			validator = new WebDriverWait(driver, 10).until(driver -> driver.findElement(By.xpath("//div[@class='cart-row' and @data-line='1']//input[@class='js--num']")));			
			assertEquals(validator.getAttribute("value"), "2");
		}

		catch (Exception exp) {
			
			System.out.println("Exception: " + exp.getMessage());
		}
	}
	
	@Test (priority = 3)
	public void testCheckOut() {

		try {
			//click Checkout
			cartPageObjects.clickCheckout();
			//validate landing on Checkout page
			validator = new WebDriverWait(driver, 15).until(driver -> driver.findElement(By.xpath("//span[@class='btn__content']")));			
			assertEquals(validator.getText().toLowerCase(), "Continue to shipping".toLowerCase());
		}

		catch (Exception exp) {
			
			System.out.println("Exception: " + exp.getMessage());
		}
	}
	
	@Test (priority = 3)
	public void testRemoveProduct() {

		try {
			//remove product from cart view
			driver.get(cartPageURL);
			cartPageObjects.clickRemoveProduct();
			//validate empty cart
			validator = new WebDriverWait(driver, 10).until(driver -> driver.findElement(By.xpath("//p[@class='cart--empty-message']")));			
			assertEquals(validator.getText().toLowerCase(), "Your cart is currently empty.".toLowerCase());
		}

		catch (Exception exp) {
			
			System.out.println("Exception: " + exp.getMessage());
		}
	}
	
}
