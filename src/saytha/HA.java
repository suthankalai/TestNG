package saytha;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.String;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;


public class HA {
	public WebDriver driver;
	
@BeforeTest
public void KA() {
System.setProperty("webdriver.chrome.driver","\\C:\\Users\\Kalai\\Desktop\\Selenium\\Driver\\chromedriver.exe\\");
driver= new ChromeDriver();
driver.manage().window().maximize();
driver.manage().timeouts().pageLoadTimeout(30,TimeUnit.SECONDS);
driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS );
driver.get("https://www.sathya.in/");
//driver.findElement(By.linkText("Home Appliances")).click();

}

@Test(priority=2)
public void GasStove() 
{
	driver.findElement(By.linkText("Gas Stove")).click();
	
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	Select DropDown = new Select(driver.findElement(By.xpath("//select[@id=\"artlist-action-pagesize\"]")));
	DropDown.selectByVisibleText("36");
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	

	driver.findElement(By.xpath("//*[@title=\"Butterfly Stove 2B Rhino\"]")).click();
	driver.findElement(By.xpath("//*[@class=\"btn btn-primary btn-lg btn-block btn-add-to-cart ajax-cart-link\"]")).click();
	driver.findElement(By.xpath("//*[@class=\"btn btn-clear btn-block btn-action\"]")).click();
	driver.findElement(By.xpath("//*[@class=\"btn btn-secondary btn-lg btn-block checkout-as-guest-button\"]")).click();
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	
	List<WebElement> Count = driver.findElements(By.xpath("//a[@class=\"art-picture img-center-container\"]"));
	int ccount = Count.size();
	System.out.println(ccount);
	
	for (int i = 0; i<ccount;i++)
	{
		List<WebElement> name = driver.findElements(By.xpath("//a[@class=\"art-picture img-center-container\"]"));
		String namee = name.get(i).getAttribute("title");
		System.out.println(namee);

		
	}
	
}


@Test(priority=1)
public void Ha() 

	
	
{
	
	driver.findElement(By.linkText("Home Appliances")).click();
	driver.findElement(By.linkText("Kitchen Appliances")).click();

	
}

@AfterTest
public void Exit() 
{
	//driver.close();
	System.out.println("After Test");
}



}
