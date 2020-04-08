 package user_query_tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PriceElements extends Elements{
	WebElement priceButton = null;
	WebElement priceExposedMin = null;
	WebElement priceExposedMax = null;
	WebElement priceDoneButton = null;
	
	public PriceElements(WebDriver driver) {
		priceButton = getIdentifier("price", "id", driver);
		priceExposedMin = getIdentifier("price-exposed-min", "id", driver);
		priceExposedMax = getIdentifier("price-exposed-max", "id", driver);
		priceDoneButton = getIdentifier("/html/body/div[1]/div[6]/div/div[1]/div[1]/div[2]/div[2]/div/div/div/button", "xpath", driver);
	}
	

}
