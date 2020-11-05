package webPages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePageObjects {

	private static String url = "https://shoestuff.com";
	private By input_email = By.id("Email");
	private By button_signUp = By.id("subscribe");
	private By textbox_search = By.xpath("//input[@name='q'][1]");
	private By button_search = By.xpath("//span[@class='icon icon-search']");
	private By productAppleBrandLeatherCare = By.xpath("//a[@class='product-grid-item']//p[contains(text(), 'Apple Brand Leather Care')]//..//..//a");
	private By categoryAppleBrandLeatherCare = By.xpath("//a[@title='Browse our Apple Brand Leather Care collection']");
	private By extendMoreCatagories1 = By.xpath("//div[@id='shopify-section-collection-list']//div[@class='section-header--right']/a");
	private By extendMoreCatagories2 = By.xpath("//div[@id='shopify-section-1525463269891']//div[@class='section-header--right']/a");
	private By extendMoreAppleBrand = By.xpath("//div[@id='shopify-section-collection-row-1']//div[@class='section-header--right']/a");
	private By extendMoreHeelixFoot = By.xpath("//div[@id='shopify-section-collection-row-2']//div[@class='section-header--right']/a");
	private By extendMoreConformerOrthotics = By.xpath("//div[@id='shopify-section-collection-row-3']//div[@class='section-header--right']/a");

	private WebDriver driver;
	
	public HomePageObjects(WebDriver driver) {
		
		this.driver = driver;
	}
	
	public static String getURL() {
		
		return url;
	}
	
	public void setTextInInputEmail(String text) {
		
		driver.findElement(input_email).sendKeys(text);
	}
	
	public void clickSignUpButton() {
		driver.findElement(button_signUp).click();
	}
	
	public void setTextInSearch(String text) {
		
		driver.findElement(textbox_search).sendKeys(text);
	}
	
	public void clickSearch() {
		driver.findElement(button_search).click();
	}
	
	public void clickProduct() {
		driver.findElement(productAppleBrandLeatherCare).click();
	}
	
	public void clickCategory() {
		driver.findElement(categoryAppleBrandLeatherCare).click();
	}
	
	public void clickExtendMoreCatagories1() {
		driver.findElement(extendMoreCatagories1).click();
	}
	
	public void clickExtendMoreCatagories2() {
		driver.findElement(extendMoreCatagories2).click();
	}
	
	public void clickExtendMoreAppleBrand() {
		driver.findElement(extendMoreAppleBrand).click();
	}
	
	public void clickExtendMoreHeelixFoot() {
		driver.findElement(extendMoreHeelixFoot).click();
	}
	
	public void clickExtendMoreConformerOrthotics() {
		driver.findElement(extendMoreConformerOrthotics).click();
	}
	
}
