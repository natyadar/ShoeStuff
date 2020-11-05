package webPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DisscountProductPageObjects {

	private static String url = "https://shoestuff.com/products/tingley-moccasin-dress-rubber-overshoe?_pos=1&_sid=f0ba45cd3&_ss=r&variant=12591000617054";
	private By button_add_quantity = By.xpath("//span[@class='js--qty-adjuster js--add']");
	private By button_add_to_cart = By.id("addToCart-product-template");
	
	private WebDriver driver;

	
	public DisscountProductPageObjects(WebDriver driver) {
		
		this.driver = driver;
	}
	
	public static String getURL() {

		return url;
	}
	
	public void clickAddQuantity() {
		
		driver.findElement(button_add_quantity).click();
	}

	public void clickAddToCart() {
		
		driver.findElement(button_add_to_cart).click();
	}

}
