package user_query_tests;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Elements {
	WebElement element;
	static Map<String, WebElement> elementMap = new HashMap<String, WebElement>();
	
	public static WebElement getIdentifier(String identifier, String byType, WebDriver driver) {
		WebElement elementIdent = null;
		if (byType == "id") {
			elementIdent = driver.findElement(By.id(identifier));
		}
		else if (byType == "xpath") {
			elementIdent = driver.findElement(By.xpath(identifier));
		}
		elementMap.put(identifier, elementIdent);	
		
		return elementIdent;
		 
	}
	
	
	public static <T> T retryFindElementByIdentifier(T element, By by, WebDriver driver) {
		int attempts = 0;
		while(attempts < 2) {
			try {
				element = (T) driver.findElements(by);
				//result = true;
				break;
			} catch(StaleElementReferenceException e) {
			}
			attempts++;
		}
		return element;
	}
	
	public String getElementXPath(WebDriver driver, WebElement element) {
	    return (String)((JavascriptExecutor)driver).executeScript(
	    		"gPt=function(c){if(c.id!=='')"
	    		+ "{return'id(\"'+c.id+'\")'}if(c===document.body)"
	    		+ "{return c.tagName}var a=0;var e=c.parentNode.childNodes;for(var b=0;b<e.length;b++){var d=e[b];if(d===c)"
	    		+ "{return gPt(c.parentNode)+'/'+c.tagName+'['+(a+1)+']'}if(d.nodeType===1&&d.tagName===c.tagName){a++}}};"
	    		+ "return gPt(arguments[0]).toLowerCase();", element);
	}
}
