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
import webPages.HomePageObjects;

@Test (groups = {"MainPage"})
public class Test005 {

	private WebDriver driver;
	private WebElement validator;
	private HomePageObjects homePageObjects;
	private String searchValue;
	private String url;


	@BeforeClass
	public void setUpTest() {
				
		try {

			System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver/chromedriver.exe");
			driver = new ChromeDriver(); 
			
			homePageObjects = new HomePageObjects(driver);
			url = homePageObjects.getURL();
			searchValue = PropertiesFile.getProperty("searchValue");
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
	
	@Test
	public void testSearchBar() {

		try {
			//search a product and land on the product's page
			driver.get(url);
			homePageObjects.setTextInSearch(searchValue);
			homePageObjects.clickSearch();
			validator = new WebDriverWait(driver, 10).until(driver -> driver.findElement(By.xpath("//h1[@class='h2 text-center']")));
			assertEquals(validator.getText().toLowerCase(), "Your search for \"TINGLEY MOCCASIN DRESS RUBBER OVERSHOE\" revealed the following:".toLowerCase());
		}

		catch (Exception exp) {
			
			System.out.println("Exception: " + exp.getMessage());
		}
	}
	
}
