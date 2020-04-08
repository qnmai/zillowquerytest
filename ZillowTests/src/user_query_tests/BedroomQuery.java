package user_query_tests;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.CharMatcher;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BedroomQuery {
	WebElement bedroomButton = null;
	WebElement oneBedOption = null;
	WebElement twoBedOption = null;
	WebElement threeBedOption = null;
	WebElement fourBedOption = null;
	WebElement fiveBedOption = null;
	List<List<WebElement>> bedTestCases = null;
	Map<String, Integer> incorrectTypeCases = null;
	static BedroomElements bedroomElements = null;
	static Map<String, WebElement> elementMap;
	
	public BedroomQuery(WebDriver driver) {
		bedroomElements = new BedroomElements(driver);
		bedroomButton = bedroomElements.bedroomButton;
		oneBedOption = bedroomElements.oneBedOption;
		twoBedOption = bedroomElements.twoBedOption;
		threeBedOption = bedroomElements.threeBedOption;
		fourBedOption = bedroomElements.fourBedOption;
		fiveBedOption = bedroomElements.fiveBedOption;
		//typeDoneButton = typeElements.typeDoneButton;
		elementMap = bedroomElements.elementMap;
		System.out.println(elementMap);
		
		//List<String> typeList = Arrays.asList("For sale", "For rent", "Sold");
		//List<WebElement> typeList = Arrays.asList(forSaleCheckBox, forRentCheckBox, soldCheckBox);
		
		

		//typeTestCases = new TestCases().getTable(typeList);
		//System.out.println("Type test cases " + typeTestCases);
		
		check(driver, bedroomButton);
		//System.out.println(incorrectTypeCases);
	}
	
	public static Map<String, Integer> check(WebDriver driver, WebElement bedroomButton) {
		String typeSelectionKey = "";
		Map<String, Integer> incorrectCasesTable = new HashMap<String, Integer>();
		int timeToWait = 15; //second
		bedroomButton.click();
		
		for (Map.Entry<String, WebElement> entry: elementMap.entrySet()) {
			//entry.getValue().click();
			int currentQueriedBeds = 0;
			try {
				currentQueriedBeds = Integer.parseInt(CharMatcher.inRange('0', '9').retainFrom(entry.getKey()));
				System.out.println("Current ID: " + currentQueriedBeds);
			} catch(NumberFormatException ex) {
				System.out.println("No number");
			}
			
			
			
			int incorrectCount = 0;
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", entry.getValue());
			try {
				for (int i=0; i<timeToWait ; i++) {
					Thread.sleep(350);
				}
			} catch (InterruptedException ie)
			{
				Thread.currentThread().interrupt();
			}
			
			List<WebElement> listDetails = driver.findElements(By.className("list-card-details"));
			int currentNumBeds = 0;
			for (WebElement ulDetail: listDetails) {
				 List<WebElement> liList = ulDetail.findElements(By.tagName("li"));
				 for (WebElement liDetail: liList) {
					 if (liDetail.getText().contains("bd")) {
						 try {
							 currentNumBeds = Integer.parseInt(CharMatcher.inRange('0', '9').retainFrom(liDetail.getText()));
						 } catch(NumberFormatException ex) {
							 System.out.println("No number");
						 }
						 
						 System.out.println(currentNumBeds);
						 if (currentNumBeds < currentQueriedBeds) {
							 incorrectCount++;
						 }
					 }
					 
				 }
				 System.out.println("Incorrect count " + incorrectCount);
				 incorrectCasesTable.put(String.valueOf(currentQueriedBeds), incorrectCount);
			}
			
		}

		System.out.println(incorrectCasesTable);
		return incorrectCasesTable;
	}

}
