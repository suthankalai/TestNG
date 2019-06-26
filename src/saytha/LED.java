package saytha;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class LED {
	public WebDriver driver;
	
	@BeforeMethod
	public void AV() {
	System.setProperty("webdriver.chrome.driver","\\C:\\Users\\Kalai\\Desktop\\Selenium\\Driver\\chromedriver.exe\\");
	driver= new ChromeDriver();
	driver.manage().window().maximize();
	driver.manage().timeouts().pageLoadTimeout(30,TimeUnit.SECONDS);
	driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS );
	driver.get("https://www.sathya.in/");
	

	}
	@Test

public void Led() 
{
	
	driver.findElement(By.linkText("Audio & Video")).click();
	driver.findElement(By.linkText("LED Television")).click();
	
	
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	Select DropDown = new Select(driver.findElement(By.xpath("//select[@id=\"artlist-action-pagesize\"]")));
	DropDown.selectByVisibleText("120");
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	List<WebElement> Count = driver.findElements(By.xpath("//a[@class=\"art-picture img-center-container\"]"));
	int ccount = Count.size();
	System.out.println(ccount);
	
	for (int i = 0; i<ccount;i++)
	{
		List<WebElement> name = driver.findElements(By.xpath("//a[@class=\"art-picture img-center-container\"]"));
		String namee = name.get(i).getAttribute("title");
		System.out.println(namee);
	}}
		@AfterMethod
		public void Exit() 
		{
			//driver.close();
			System.out.println("After Test");
		}
		
		
}
