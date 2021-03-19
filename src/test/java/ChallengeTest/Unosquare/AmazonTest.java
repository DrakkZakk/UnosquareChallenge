package ChallengeTest.Unosquare;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.google.gson.JsonParser;
//import com.google.gson.stream.JsonReader;

import ChallengeTest.Unosquare.HomePage;
import ChallengeTest.Unosquare.SearchDevice;
import io.github.bonigarcia.wdm.WebDriverManager;
/*import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import static io.restassured.RestAssured.given;*/

public class AmazonTest {
	
	WebDriver driver;
	
	@BeforeTest
	public void setUp(){
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("start-maximized");
        options.addArguments("--incognito");
		driver = new ChromeDriver(options);
	}
	
	@Test(priority = 1, dataProvider = "dp")
	public void firstTestCase(String data) throws InterruptedException {
		String elements[] = data.split(",");
		String url = elements[0];
		String device = elements[1];
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		HomePage home = new HomePage(driver);
		SearchDevice search = new SearchDevice(driver);
		home.searchPhone(device);
		search.selectItem();
		search.addToCart();
	}
	
	@Test(priority = 2, dataProvider = "dp")
	public void secondTestCase(String data) {
		String elements[] = data.split(",");
		String url = elements[0];
		String item = elements[2];
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		HomePage homeTC2 = new HomePage(driver);
		NewCustomer nc = new NewCustomer(driver);
		homeTC2.clickStart();
		nc.fillForms("Juan", "juanito@mail.com", item);
	}
	
	@DataProvider(name = "dp")
	public String[] readJson() throws IOException, ParseException{
		JSONParser jsonParser = new JSONParser();
		FileReader reader = new FileReader(".\\jsonfiles\\data.json");
		Object obj = jsonParser.parse(reader);
		JSONObject obje = (JSONObject) obj;
		JSONArray array = (JSONArray) obje.get("data");
		String arr[] = new String [array.size()];
		for(int i = 0; i < array.size(); i++) {
			JSONObject data = (JSONObject) array.get(i);
			String url = (String)data.get("url");
			String device = (String)data.get("device");
			String item = (String)data.get("item");
			arr[i] = url + "," + device + "," + item;
		}
		return arr;
	}
	
	/*
	public void getAPIdata(){
		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1/employee";
		RestAssured.basePath = "/employee";
		Response response = given().contentType(ContentType.JSON).log().all().get("/1");
		
		JsonPath json = response.jsonPath();
		String name = json.get("employee_name");
	}*/
	
	@AfterTest
	public void closeBrowser() {
		driver.close();
	}
}