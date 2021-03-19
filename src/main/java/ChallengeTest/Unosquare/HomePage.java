package ChallengeTest.Unosquare;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage implements IActions {
	
	private WebDriver driver;
	
	@FindBy(how = How.ID, using = "twotabsearchtextbox")
	WebElement searchBar;
	
	@FindBy(how = How.XPATH, using = "//input[@id='nav-search-submit-button']")
	WebElement submit;
	
	@FindBy(how = How.XPATH, using = "//*[@id='nav-link-accountList']")
	WebElement hello;
	
	WebDriverWait wait;
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void searchPhone(String deviceName) {
		sendText(searchBar, deviceName);
		clickOnElement(submit);
	}
	
	public void clickStart() {
		Actions action = new Actions(driver);
		action.moveToElement(hello).build().perform();
		wait = new WebDriverWait(driver, 10);
		WebElement startHere = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"nav-flyout-ya-newCust\"]/a")));
		clickOnElement(startHere);
	}
	
	@Override
	public void sendText(WebElement element, String text) {
		element.clear();
		element.sendKeys(text);
	}
	
	@Override
	public void clickOnElement(WebElement element) {
		element.click();
	}

	@Override
	public void fillForms(String name, String email, String item) {
	}

	@Override
	public boolean isElementDisplayed(WebElement element) {
		return false;
	}
	
}
