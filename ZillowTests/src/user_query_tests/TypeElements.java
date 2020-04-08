package user_query_tests;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TypeElements extends Elements {	
	WebElement typeButton = null;
	WebElement forSaleCheckBox = null;
	WebElement forRentCheckBox = null;
	WebElement soldCheckBox = null;
	
	public TypeElements(WebDriver driver) {
		typeButton = getIdentifier("listing-type", "id", driver);
		
		forSaleCheckBox = getIdentifier("//*[@id=\"isForSaleByAgent_isForSaleByOwner_isNewConstruction_isComingSoon_isAuction_isForSaleForeclosure_isPreMarketForeclosure_isPreMarketPreForeclosure_isMakeMeMove\"]", "xpath", driver);
		forRentCheckBox = getIdentifier("//*[@id=\"isForRent\"]", "xpath", driver);
		soldCheckBox = getIdentifier("//*[@id=\"isRecentlySold\"]", "xpath", driver);
		
	}
}
