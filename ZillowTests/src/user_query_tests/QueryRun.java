package user_query_tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class QueryRun {
	public static void main(String[] args) {
		System.setProperty("webdriver.gecko.driver", "/Users/niunani/Selenium/geckodriver");
		WebDriver driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

		String baseUrl = "https://www.zillow.com/san-diego-ca-92109/";
		driver.get(baseUrl);
		
		PriceQuery priceQuery = new PriceQuery(driver);
		TypeQuery typeQuery = new TypeQuery(driver);
		BedroomQuery bedroomQuery = new BedroomQuery(driver);
		
		driver.close();
		System.exit(0);
	}
	
	
}
