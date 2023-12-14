package samplemavenSeleniumTest.amezonApp;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AmezonAppTest {
 
                       static WebDriver driver=null;


public static void main(String[] args) {
	

	String path = "drivers/windows/chromedriver.exe";
	System.setProperty("webdriver.chrome.driver", path);
	//opening the browser
	driver = new ChromeDriver();
	//Searchin the url
	driver.get("https://www.amazon.in/");
	
	screenshot("homepage.png");

	iphoneSearch();
		
	}
   public static void iphoneSearch() {
//		Click on ‘Mobiles’ in the navigation bar
		driver.findElement(By.linkText("Mobiles")).click();
		
		
		WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(30));
    //point the pointer over ‘Mobiles & Accessories’
		WebElement mobiles = driver.findElement(By.xpath("(//span[contains(@class,'nav-a-content')])[2]"));
		Actions actions = new Actions(driver);
		actions.moveToElement(mobiles).build().perform();
		
		screenshot("mobiles.png");
		
		//explicit wait for some wait until the condition true
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[contains(@class,'nav-a-content')])[2]")));
//		Click on ‘Apple’ in the sub-menu
		driver.findElement(By.linkText("Apple")).click();
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys("iphone13");
		driver.findElement(By.xpath("//*[@id=\"nav-search-submit-button\"]")).submit();
		screenshot("search.png");
		
		driver.findElement(By.xpath("(//span[contains(@class,'a-size-medium a-color-base a-text-normal')])[1]")).click();
		
		screenshot("i13page.png");
//		Switch focus on new tab
		ArrayList<String> newtab = new ArrayList<>(driver.getWindowHandles());
		driver.switchTo().window(newtab.get(1));
		
		WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(60));
		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id("buy-now-button")));
		
		
		screenshot("buyoption.png");
		//	Click on ‘Buy Now’ button
		driver.findElement(By.id("buy-now-button")).click();
		
		
		//	Verify user sees the text ‘Sign in’ on the last page.
		String expectedText = "Sign in";
		
		WebDriverWait wait3 = new WebDriverWait(driver, Duration.ofSeconds(60));
		wait2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[contains(@class,'a-spacing-small')]")));
		
		screenshot("output.png");

		String actualText = driver.findElement(By.xpath("//h1[contains(@class,'a-spacing-small')]")).getText();
		if(actualText.equals(expectedText)) {
			System.out.println("Test case passed");
		}
		else {
			System.out.println("Test case failed");
		}
		
   }
   private static void screenshot(String scname) {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File f = ts.getScreenshotAs(OutputType.FILE);
		try {
			FileHandler.copy(f, new File("output\\"+scname));
		}catch(IOException e) {
			e.printStackTrace();
		}
   
}
}
	







