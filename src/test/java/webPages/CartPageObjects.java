package webPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class CartPageObjects {
	
	private static String url = "https://shoestuff.com/cart";
	private By button_add_quantity = By.xpath("//div[@class='cart-row' and @data-line='1']//span[@class='js--qty-adjuster js--add']");
	private By button_update_cart = By.xpath("//input[@name='update']");
	private By button_checkOut = By.xpath("//button[@name='checkout']");
	private By button_remove_product = By.xpath("//div[@class='cart-row' and @data-line='1']//a[@class='icon-fallback-text btn-secondary remove']");

	private WebDriver driver;

	
	
	public CartPageObjects(WebDriver driver) {
		
		this.driver = driver;
	}
	
	public static String getURL() {
		
		return url;
	}

	public void clickAddQuantity() {
		
		driver.findElement(button_add_quantity).click();
	}
	
	public void clickUpdateCart() {
		
		driver.findElement(button_update_cart).click();
	}
	
	public void clickCheckout() {
		
		driver.findElement(button_checkOut).click();
	}
	
	public void clickRemoveProduct() {
		
		driver.findElement(button_remove_product).click();
	}
	
}