package webPages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import net.bytebuddy.asm.Advice.OffsetMapping.Target.ForField.ReadOnly;

public class SignInPageObjects {

	private static String url = "https://shoestuff.com/account/login";
	private By input_email = By.id("customer_email");
	private By input_password = By.id("customer_password");
	private By button_signIn = By.xpath("//input[@value='Sign In']");

	WebDriver driver;
	
	public SignInPageObjects(WebDriver driver) {
		
		this.driver = driver;
	}
	
	public static String getURL() {
		
		return url;
	}
	
	public void setTextInInputEmail(String text) {
		
		driver.findElement(input_email).sendKeys(text);
	}
	
	public void setTextInInputPassword(String text) {
		
		driver.findElement(input_password).sendKeys(text);
	}
	
	public void clickSignUp() {
		driver.findElement(button_signIn).click();
	}

}
