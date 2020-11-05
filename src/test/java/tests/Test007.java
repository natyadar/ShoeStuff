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
public class Test007 {

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
	public void testCartForProduct() {

		try {
			//go to product page with specific size
			driver.get(productPageURL);
			//add quantity
			disscountProductPageObjects.clickAddQuantity();
			disscountProductPageObjects.clickAddToCart();
			
			new WebDriverWait(driver, 10).until(driver -> driver.findElement(By.xpath("//div[@id='ajaxifyCart']/form/h1")));
					
			cartPageObjects = new CartPageObjects(driver);
			cartPageURL = cartPageObjects.getURL();
			
			//validate correct product title in cart view
			driver.get(cartPageURL);
			validator = new WebDriverWait(driver, 10).until(driver -> driver.findElement(By.xpath("//div[@data-line='1']//div[@class='grid-item two-thirds large--three-quarters']/a")));			
			assertEquals(validator.getText().toLowerCase(), "Tingley Moccasin Dress Rubber Overshoe".toLowerCase());
		}

		catch (Exception exp) {
			
			System.out.println("Exception: " + exp.getMessage());
		}
	}
	
	@Test (priority = 2)
	public void testCartForSize() {

		try {
			//validate right size in cart view 
			validator = new WebDriverWait(driver, 10).until(driver -> driver.findElement(By.xpath("//div[@data-line='1']//div[@class='grid-item two-thirds large--three-quarters']/small")));			
			assertEquals(validator.getText().toLowerCase(), "X-Large: Men's 11.5 - 14".toLowerCase());
		}

		catch (Exception exp) {
			
			System.out.println("Exception: " + exp.getMessage());
		}
	}
	
	@Test (priority = 3)
	public void testCartForQuantity() {

		try {
			//validate right quantity in cart view
			validator = new WebDriverWait(driver, 10).until(driver -> driver.findElement(By.xpath("//input[@name='updates[]']")));
			assertEquals(validator.getAttribute("value"), "2");
		}

		catch (Exception exp) {
			
			System.out.println("Exception: " + exp.getMessage());
		}
	}
	
}
