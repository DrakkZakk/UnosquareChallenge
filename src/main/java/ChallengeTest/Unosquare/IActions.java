package ChallengeTest.Unosquare;

import org.openqa.selenium.WebElement;

public interface IActions {
	
	public void clickOnElement(WebElement element);
	
	public void fillForms(String name, String email, String item);
	
	public boolean isElementDisplayed(WebElement element);
	
	public void sendText(WebElement element, String text);
	
}
