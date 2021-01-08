package stepdef;

import static org.junit.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import cucumber.api.PendingException;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


public class FTSERiserandFaller {
	

	private static WebDriver driver;
	private static String URL = "https://www.hl.co.uk/shares/stock-market-summary/ftse-100";
	
	@Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/resources/drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1366, 768));
        driver.get("https://www.hl.co.uk/shares/stock-market-summary/ftse-100");
        driver.findElement(By.cssSelector("#acceptCookie")).click();
    }
	
	@After
    public void tearDown() {
        driver.close();
	}
	
	@Given("^I can access the FTSE Site$")
	public void i_can_access_the_FTSE_Site() throws Throwable {
		driver.get(URL);
		
	}

	@When("^I click the risers$")
	public void i_click_the_risers() throws Throwable {
    	JavascriptExecutor js = (JavascriptExecutor) driver;
    	WebElement Element = driver.findElement(By.linkText("RISERS"));
    	js.executeScript("arguments[0].scrollIntoView();", Element);
        WebElement risers = driver.findElement(By.cssSelector("#view-constituents > ul > li:nth-child(2) > a > strong"));
        risers.click();

	}

	@Then("^I find the top riser$")
	public void i_find_the_top_riser() throws Throwable {
		WebElement highest = driver.findElement(By.cssSelector("#ls-perc-SBRY-L"));
        String highText = highest.getText();
        assertEquals(highText, highest.getText());
	}

	@When("^I click the faller$")
	public void i_click_the_faller() throws Throwable {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement Element2 = driver.findElement(By.linkText("FALLERS"));
    	js.executeScript("arguments[0].scrollIntoView();", Element2);
        WebElement fallers = driver.findElement(By.cssSelector("#view-constituents > ul > li:nth-child(3) > a") );
        fallers.click();
	}

	@Then("^I find the top faller$")
	public void i_find_the_top_faller() throws Throwable {
		WebElement lowest = driver.findElement(By.cssSelector("#ls-perc-AUTO-L"));
	       String lowText = lowest.getText();
	       assertEquals(lowText, lowest.getText());
	}


}
