package ChallengeTest.Unosquare;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class NewCustomer implements IActions {
	
	@FindBy(how = How.ID, using = "ap_customer_name")
	WebElement txt_name;
	
	@FindBy(how = How.ID, using = "ap_email")
	WebElement txt_email;
	
	@FindBy(how = How.XPATH, using = "//*[@id=\"legalTextRow\"]/a[1]")
	WebElement conditions;
	
	@FindBy(how = How.XPATH, using = "//*[@id=\"helpsearch\"]")
	WebElement searchBar;
	
	@FindBy(how = How.XPATH, using = "(//*[@class='a-link-normal'])[1]")
	WebElement echoSupport;
	
	@FindBy(how = How.XPATH, using = "//h4[contains(text(),'Getting Started')]")
	WebElement getting;
	
	@FindBy(how = How.XPATH, using = "//h4[contains(text(),'Wi-Fi and Bluetooth')]")
	WebElement wiFi;
	
	@FindBy(how = How.XPATH, using = "//h4[contains(text(),'Wi-Fi and Bluetooth')]")
	WebElement sofHard;
	
	@FindBy(how = How.XPATH, using = "//h4[contains(text(),'Troubleshooting')]")
	WebElement trouble;
	
	WebDriver driver;
	
	public NewCustomer(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	

	@Override
	public void clickOnElement(WebElement element) {
		element.click();
	}

	@Override
	public void fillForms(String name, String email, String item) {
		sendText(txt_name, name);
		sendText(txt_email, email);
		clickOnElement(conditions);
		sendText(searchBar, item);
		enterBtn(searchBar);
		clickOnElement(echoSupport);
		//Is Getting Started displayed
		Assert.assertTrue(isElementDisplayed(getting));
		//Is Wi-Fi and Bluetooth displayed
		Assert.assertTrue(isElementDisplayed(wiFi));
		//Is Device Software and Hardware displayed
		Assert.assertTrue(isElementDisplayed(sofHard));
		//Is TroubleShooting displayed
		Assert.assertTrue(isElementDisplayed(trouble));
	}

	@Override
	public boolean isElementDisplayed(WebElement element) {
		return element.isDisplayed();
	}

	@Override
	public void sendText(WebElement element, String text) {
		element.clear();
		element.sendKeys(text);
	}
	
	public void enterBtn(WebElement element) {
		element.sendKeys(Keys.ENTER);
	}

}
