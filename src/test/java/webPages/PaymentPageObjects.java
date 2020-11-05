package webPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class PaymentPageObjects {
	
	private By button_refund_policy = By.xpath("//a[@data-modal='policy-refund-policy']");
	private By button_shipping_policy = By.xpath("//a[@data-modal='policy-shipping-policy']");
	private By button_privacy_policy = By.xpath("//a[@data-modal='policy-privacy-policy']");
	private By button_terms_of_service = By.xpath("//a[@data-modal='policy-terms-of-service']");

	private WebDriver driver;

	
	
	public PaymentPageObjects(WebDriver driver) {
		
		this.driver = driver;
	}
	
	
	public void clickRefundPolicy() {
		
		driver.findElement(button_refund_policy).click();
	}
	
	public void clickShippingPolicy() {
		
		driver.findElement(button_shipping_policy).click();
	}
	
	public void clickPrivacyPolicy() {
		
		driver.findElement(button_privacy_policy).click();
	}
	
	public void clickTermsOfService() {
		
		driver.findElement(button_terms_of_service).click();
	}
	
}