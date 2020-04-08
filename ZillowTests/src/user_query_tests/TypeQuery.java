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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TypeQuery {
	WebElement typeButton = null;
	WebElement forSaleCheckBox = null;
	WebElement forRentCheckBox = null;
	WebElement soldCheckBox = null;
	WebElement typeDoneButton = null;
	List<List<WebElement>> typeTestCases = null;
	Map<String, Integer> incorrectTypeCases = null;
	static TypeElements typeElements = null;
	
	public TypeQuery(WebDriver driver) {
		typeElements = new TypeElements(driver);
		typeButton = typeElements.typeButton;
		forSaleCheckBox = typeElements.forSaleCheckBox;
		forRentCheckBox = typeElements.forRentCheckBox;
		soldCheckBox = typeElements.soldCheckBox;
		//typeDoneButton = typeElements.typeDoneButton;
		Map<String, WebElement> elementMap = typeElements.elementMap;
		System.out.println(elementMap);
		
		//List<String> typeList = Arrays.asList("For sale", "For rent", "Sold");
		List<WebElement> typeList = Arrays.asList(forSaleCheckBox, forRentCheckBox, soldCheckBox);
		
		

		typeTestCases = new TestCases().getTable(typeList);
		System.out.println("Type test cases " + typeTestCases);
		
		incorrectTypeCases = check(typeTestCases, driver, typeButton, forSaleCheckBox, forRentCheckBox, soldCheckBox, typeDoneButton);
		System.out.println(incorrectTypeCases);
	}
	
	public static Map<String, Integer> check(List<List<WebElement>> typeTestCases, WebDriver driver, WebElement typeButton, WebElement forSaleCheckBox, WebElement forRentCheckBox, WebElement soldCheckBox, WebElement typeDoneButton) {
		String typeSelectionKey = "";
		Map<String, Integer> incorrectCasesTable = new HashMap<String, Integer>();
		int timeToWait = 15; //second
		typeButton.click();
		
		for (List<WebElement> checkedTypeSet : typeTestCases) {
			List<String> currentElements = new ArrayList<String>();
			String currentElementsKey = "";
			for (WebElement element : checkedTypeSet) {
				if (element.toString().contains("ForSale")) {
					if (!currentElements.contains("ForSale")) {
						currentElements.add("sale");
						currentElementsKey = currentElementsKey + " - sale - ";
					}
				}
				if (element.toString().contains("ForRent")) {
					if (!currentElements.contains("rent")) {
						currentElements.add("rent");
						currentElementsKey = currentElementsKey + " - rent - ";
					}
				}
				if (element.toString().contains("isRecentlySold")) {
					if (!currentElements.contains("Sold")) {
						currentElements.add("sold");
						currentElementsKey = currentElementsKey + " - sold - ";
					}
				}
				if(!element.isSelected()) {
					((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
				}			
			}
			try {
				for (int i=0; i<timeToWait ; i++) {
					Thread.sleep(350);
				}
			} catch (InterruptedException ie)
			{
				Thread.currentThread().interrupt();
			}
			
			List<WebElement> returnTypes = null;
			List<String> returnItems = new ArrayList<String>();
			int incorrectCount = 0;			
			int containsFlag = 0;
			
			returnTypes = typeElements.retryFindElementByIdentifier(returnTypes, By.className("list-card-type"), driver);
			Map<String, WebElement> typeElementsMap = typeElements.elementMap;
			
			//System.out.println("Returned query " + returnTypes);
			
			for (WebElement returned : returnTypes) {
				returnItems.add(returned.getText().toLowerCase());
				//System.out.println("TEST " + currentElements.parallelStream().anyMatch(returned.getText()::contains));
				if (!currentElements.parallelStream().anyMatch(returned.getText().toLowerCase()::contains)) {
					incorrectCount++;
					System.out.println(returned.getText());
				}
			}
			
			//System.out.println("Return items " + returnItems);
			System.out.println("Current elements " + currentElements);
			//System.out.println(typeElementsMap);
			System.out.println("Current elements key " + currentElementsKey);
			System.out.println("Incorrect : " + incorrectCount);
			System.out.println("--------------");
			incorrectCasesTable.put(currentElementsKey, incorrectCount);
			
			if(forSaleCheckBox.isSelected()) {
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", forSaleCheckBox);
			}
			if(forRentCheckBox.isSelected()) {
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", forRentCheckBox);
			}
			if(soldCheckBox.isSelected()) {
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", soldCheckBox);
			}
		}
		//System.out.println(incorrectCasesTable);
		return incorrectCasesTable;
	}

//	public static void main(String[] args) {
//		System.setProperty("webdriver.gecko.driver", "/Users/niunani/Selenium/geckodriver");
//		WebDriver driver = new FirefoxDriver();
//		new TypeQuery(driver);
//		
//		
//	}
}
