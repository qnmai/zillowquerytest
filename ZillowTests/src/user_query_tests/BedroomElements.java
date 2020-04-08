package user_query_tests;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BedroomElements extends Elements {
	WebElement bedroomButton = null;
	WebElement oneBedOption = null;
	WebElement twoBedOption = null;
	WebElement threeBedOption = null;
	WebElement fourBedOption = null;
	WebElement fiveBedOption = null;
	
	public BedroomElements(WebDriver driver) {
		bedroomButton = getIdentifier("beds", "id",driver);
		oneBedOption = getIdentifier("beds-options-1", "id", driver);
		twoBedOption = getIdentifier("beds-options-2", "id", driver);
		threeBedOption = getIdentifier("beds-options-3", "id", driver);
		fourBedOption = getIdentifier("beds-options-4", "id", driver);
		fiveBedOption = getIdentifier("beds-options-5", "id", driver);

	}
}
