package ChallengeTest.Unosquare;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class SearchDevice implements IActions {
	
	WebDriver driver;
	
	@FindBy(how = How.XPATH, using = "(//*[@class='a-link-normal a-text-normal'])[1]") //a[contains(.,'Galaxy Note 20')]
	WebElement item;
	
	@FindBy(how = How.XPATH, using = "(//*[@class='a-price-whole'])[1]")
	WebElement price;
	
	@FindBy(how = How.XPATH, using = "(//*[@class='a-price-fraction'])[1]")
	WebElement priceFraction;
	
	@FindBy(how = How.XPATH, using = "//span[@id='price_inside_buybox']")
	WebElement secondPrice;
	
	@FindBy(how = How.XPATH, using = "//input[@id='add-to-cart-button']")
	WebElement addCart;
	
	@FindBy(how = How.XPATH, using = "(//*[@class='a-color-price hlb-price a-inline-block a-text-bold'])[1]")
	WebElement thirdPrice;
	
	@FindBy(how = How.XPATH, using ="//a[@id='hlb-ptc-btn-native']")
	WebElement proceedCheckout;
	
	@FindBy(how = How.XPATH, using = "//a[@id='hlb-view-cart-announce']")
	WebElement cart;
	
	@FindBy(how = How.XPATH, using = "//*[@value='Delete']")
	WebElement deleteItem;
	
	WebDriverWait wait;

	public SearchDevice(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void selectItem() {
		storePrice();
		Assert.assertTrue(isElementDisplayed(item));
		clickOnElement(item);
		Assert.assertTrue(comparePrices());
	}
	
	public void addToCart() throws InterruptedException {
		clickOnElement(addCart);
		clickOnElement(proceedCheckout);
		returnBack();
		clickOnElement(cart);
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(deleteItem));
		clickOnElement(deleteItem);
	}
	
	@Override
	public void clickOnElement(WebElement element) {
		element.click();
	}

	@Override
	public void fillForms(String name, String email, String item) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isElementDisplayed(WebElement element) {
		return element.isDisplayed();
	}
	
	public String storePrice() {
		String price = this.price.getText();
		String fraction = this.priceFraction.getText();
		String completePrice = price +"."+ fraction;
		return completePrice;
	}
	
	public boolean comparePrices() {
		String price = this.price.getText();
		String fraction = this.priceFraction.getText();
		String complete = price +"."+ fraction;
		if(complete.equalsIgnoreCase(storePrice())) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public void returnBack() {
		driver.navigate().back();
	}

	@Override
	public void sendText(WebElement element, String text) {
		// TODO Auto-generated method stub
		
	}

}