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
import webPages.SignInPageObjects;

@Test (groups = {"UserAuth"})
public class Test004 {

	private WebDriver driver;
	private WebElement validator;
	private SignInPageObjects signInPageObjects;
	private String demoEmail;
	private String demoPassword;
	private String url;


	@BeforeClass
	public void setUpTest() {
				
		try {

			System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver/chromedriver.exe");
			driver = new ChromeDriver(); 
			
			signInPageObjects = new SignInPageObjects(driver);
			url = signInPageObjects.getURL();
			demoEmail = PropertiesFile.getProperty("demoEmail");
			demoPassword = PropertiesFile.getProperty("demoPassword");
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
	public void testEmailOnly() {

		try {
			//insert email but no password to get error message
			driver.get(url);
			signInPageObjects.setTextInInputEmail(demoEmail);
			signInPageObjects.clickSignUp();
			validator = new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@class='disc']/li")));
			assertEquals(validator.getText().toLowerCase(), "Incorrect email or password.".toLowerCase());		
		}
		
		catch (Exception exp) {
			
			System.out.println("Exception: " + exp.getMessage());
		}
	}
	
	
	@Test (priority=2)
	public void testPasswordOnly() {

		try {
			//insert password but no email to get error message
			driver.get(url);
			signInPageObjects.setTextInInputPassword(demoPassword);
			signInPageObjects.clickSignUp();
			validator = new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@class='disc']/li")));
			assertEquals(validator.getText().toLowerCase(), "Incorrect email or password.".toLowerCase());				
		}
		
		catch (Exception exp) {
			
			System.out.println("Exception: " + exp.getMessage());
		}
	}

}
