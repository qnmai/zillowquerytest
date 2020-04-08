package user_query_tests;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.ArrayList;

import org.openqa.selenium.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
//import org.openqa.selenium.chrome.ChromeDriver;

public class PriceQuery {
	WebElement priceButton = null;
	WebElement priceExposedMin = null;
	WebElement priceExposedMax = null;
	WebElement priceDoneButton = null;
	Map<Integer, List<Integer>> priceTestCases = null;
	Map<String, Integer> incorrectPriceCases = null;
	

	public PriceQuery(WebDriver driver) {
		PriceElements priceElements = new PriceElements(driver);
		priceButton = priceElements.priceButton;
		priceExposedMin = priceElements.priceExposedMin;
		priceExposedMax = priceElements.priceExposedMax;
		priceDoneButton = priceElements.priceDoneButton;
		
		priceTestCases = new TestCases().getTable(300000, 500000, 1000000, 100000);
		System.out.println("THESE test cases " + priceTestCases);
		
		incorrectPriceCases = check(priceTestCases, driver, priceButton, priceDoneButton, priceExposedMin, priceExposedMax);
		System.out.println(incorrectPriceCases);

	}

	public static WebElement getWebElement(By by, WebDriver driver) {
		WebElement element = driver.findElement(by);
		return element;

	}


	public static Map<String, Integer> check(Map<Integer, List<Integer>> priceTestCases, WebDriver driver, WebElement priceButton, WebElement priceDoneButton, WebElement priceExposedMin, WebElement priceExposedMax) {
		String priceRangeKey = "";
		Map<String, Integer> incorrectCasesTable = new HashMap<String, Integer>();
		//Map<Integer, List<Integer>> myPriceTestCases = priceTestCases;
		System.out.println(priceTestCases);

		priceButton.click();
		for (Map.Entry<Integer, List<Integer>> minPrice : priceTestCases.entrySet()) {
			Integer minPriceKey = minPrice.getKey();
			List<Integer> values = minPrice.getValue();
			System.out.println("Key = " + minPriceKey);
			System.out.println("Values = " + values + "n");
			//priceExposedMin.clear();
			for (int i=0;i<20;i++) {
				priceExposedMin.sendKeys(Keys.BACK_SPACE);
			}
			priceExposedMin.sendKeys(String.valueOf(minPriceKey));
			for (Integer maxPrice : values) {
				int countWrong = 0;
				System.out.println(maxPrice);
				priceRangeKey = minPriceKey + " - " + maxPrice;
				//priceExposedMax.clear();
				//wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"price-exposed-max\"]"))).clear();
				for (int i=0;i<20;i++) {
					priceExposedMax.sendKeys(Keys.BACK_SPACE);
				}
				priceExposedMax.sendKeys(String.valueOf(maxPrice));
				priceDoneButton.click();

				//TODO: get these explicit waits to work instead of current Thread.sleep workaround
				//wait for document.readyState
				//new WebDriverWait(driver, 30).until(
				//      webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));

				//Wait for 30 seconds until AJAX search load the content
				//new WebDriverWait(driver,30).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("list-card-price")));
				//new WebDriverWait(driver,30).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("ul > li.closed")));

				int timeToWait = 10; //second
				try {
					for (int i=0; i<timeToWait ; i++) {
						Thread.sleep(350);
					}
				} catch (InterruptedException ie)
				{
					Thread.currentThread().interrupt();
				}	          

				List<WebElement> returnPrices = null;
				returnPrices = new Elements().retryFindElementByIdentifier(returnPrices, By.className("list-card-price"), driver);
				System.out.println(returnPrices);
				for (WebElement priceElement : returnPrices) {
					String priceString = priceElement.getText();
					priceString = priceString.replace("Est. $", "");
					priceString = priceString.replace("$", "");
					priceString = priceString.replace(",", "");
					Integer price = Integer.parseInt(priceString);
					if (price > maxPrice) {
						countWrong++;
						System.out.println("Over price : " + Integer.toString(price));
					}
					System.out.println("This price : " + Integer.toString(price));

				}
				System.out.println("Incorrect query result: " + Integer.toString(countWrong)) ;
				incorrectCasesTable.put(priceRangeKey, countWrong);
				priceButton.click();

			}
		}
		return incorrectCasesTable;
	}
}



