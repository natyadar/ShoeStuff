package tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertTrue;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
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
import webPages.HomePageObjects;

@Test (groups = {"MainPage", "Links"})
public class Test003 {

	private WebDriver driver;
	private WebElement validator;
	private HomePageObjects homePageObjects;
	private String url;

	
	@BeforeClass
	public void setUpTest() {
				
		try {

			System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver/chromedriver.exe");
			driver = new ChromeDriver(); 
			
			homePageObjects = new HomePageObjects(driver);
			url = homePageObjects.getURL();
		}
		
		catch (Exception exp) {
			
			System.out.println(exp.getMessage());
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
	
	@Test (priority=1)
	public void testExtendMoreCatagories1() {

		try {
			//browse to page and click link
			driver.get(url);
			homePageObjects.clickExtendMoreCatagories1();
			validator = new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//nav[@class='breadcrumb']/span[2]")));
			assertEquals(validator.getText().toLowerCase(), "Collections".toLowerCase());	
		}
		
		catch (Exception exp) {
			
			System.out.println("Exception: " + exp.getMessage());
		}
	}
	
	@Test (priority=2)
	public void testExtendMoreCatagories2() {

		try {
			//browse to page and click link
			driver.get(url);
			homePageObjects.clickExtendMoreCatagories2();
			validator = new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//nav[@class='breadcrumb']/span[2]")));
			assertEquals(validator.getText().toLowerCase(), "Collections".toLowerCase());	
		}
		
		catch (Exception exp) {
			
			System.out.println("Exception: " + exp.getMessage());
		}
	}
	
	@Test (priority=3)
	public void testExtendMoreAppleBrand() {

		try {
			//browse to page and click link
			driver.get(url);
			homePageObjects.clickExtendMoreAppleBrand();
			validator = new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//nav[@class='breadcrumb']/span[2]")));
			assertEquals(validator.getText().toLowerCase(), "Apple Brand Leather Care".toLowerCase());	
		}
		
		catch (Exception exp) {
			
			System.out.println("Exception: " + exp.getMessage());
		}
	}
	
	@Test (priority=4)
	public void testExtendMoreHeelixFoot() {

		try {
			//browse to page and click link
			driver.get(url);
			homePageObjects.clickExtendMoreHeelixFoot();
			validator = new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//nav[@class='breadcrumb']/span[2]")));
			assertEquals(validator.getText().toLowerCase(), "Heelix Foot and Shoe Care".toLowerCase());	
		}
		
		catch (Exception exp) {
			
			System.out.println("Exception: " + exp.getMessage());
		}
	}
	
	@Test (priority=5)
	public void testExtendMoreConformerOrthotics() {

		try {
			//browse to page and click link
			driver.get(url);
			homePageObjects.clickExtendMoreConformerOrthotics();
			validator = new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//nav[@class='breadcrumb']/span[2]")));
			assertEquals(validator.getText().toLowerCase(), "Conformer Orthotics".toLowerCase());	
		}
		
		catch (Exception exp) {
			
			System.out.println("Exception: " + exp.getMessage());
		}
	}
	
}
