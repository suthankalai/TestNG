package saytha;



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
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;


import org.openqa.selenium.chrome.ChromeDriver;

public class purchase {
	
	WebDriver driver;
	
	@BeforeTest()
	public void browser() 
	{
		System.setProperty("webdriver.chrome.driver","\\C:\\Users\\Kalai\\Desktop\\Selenium\\Driver\\chromedriver.exe\\");
		driver= new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(30,TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS );
		driver.get("https://www.sathya.in/");
		
	}
		
	@Test(priority=1)
	public void testcase1() throws InterruptedException
{
		
		//Click Home Appliance DropDown button
	      WebElement menu=driver.findElement(By.xpath("//a[@class='nav-link dropdown-toggle']"));
	      Actions actions = new Actions (driver);
	      actions.moveToElement(menu).perform();
	      
	      //Click Kitchen Appliances 
	      driver.findElement(By.linkText("Kitchen Appliances")).click();
	      System.out.println("Kitchen Appliances Page Opened Successfully");
	      
	  	

	  	driver.findElement(By.xpath("//*[@title=\"Butterfly Stove 2B Rhino\"]")).click();
	  	driver.findElement(By.xpath("//*[@class=\"btn btn-primary btn-lg btn-block btn-add-to-cart ajax-cart-link\"]")).click();
	  	driver.findElement(By.xpath("//*[@class=\"btn btn-clear btn-block btn-action\"]")).click();
	  	driver.findElement(By.xpath("//*[@class=\"btn btn-secondary btn-lg btn-block checkout-as-guest-button\"]")).click();
	  	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  	
}
	  
	@Test(priority=2)
	public void Excel() throws IOException, InterruptedException
	{
		InputStream ExcelFileToRead = new FileInputStream("E:\\GasStove_Purchase.xlsx");
	    XSSFWorkbook  wb = new XSSFWorkbook(ExcelFileToRead);
	    XSSFSheet sheet = wb.getSheetAt(0);
	    //XSSFRow row; 
	    //XSSFCell cell;
		 
	    
	    XSSFCell Company = wb.getSheetAt(0).getRow(1).getCell(0);
		String CompanyName= Company.toString();
	    driver.findElement(By.xpath("//input[@id=\"NewAddress_Company\"]")).sendKeys(CompanyName);
		
	    XSSFCell Fname = wb.getSheetAt(0).getRow(1).getCell(1);
		String FirstName= Fname.toString();
		driver.findElement(By.xpath("//input[@id=\"NewAddress_FirstName\"]")).sendKeys(FirstName);
		
		XSSFCell Lname = wb.getSheetAt(0).getRow(1).getCell(2);
		String LastName= Lname.toString();
		driver.findElement(By.xpath("//input[@id=\"NewAddress_LastName\"]")).sendKeys(LastName);
		
		XSSFCell Address1 = wb.getSheetAt(0).getRow(1).getCell(3);
		String Adrs1= Address1.toString();
	    driver.findElement(By.xpath("//input[@id=\"NewAddress_Address1\"]")).sendKeys(Adrs1);
	    
	    XSSFCell Address2 = wb.getSheetAt(0).getRow(1).getCell(4);
		String Adrs2= Address2.toString();
	    driver.findElement(By.xpath("//input[@id=\"NewAddress_Address2\"]")).sendKeys(Adrs2);
	    
	    XSSFCell City = wb.getSheetAt(0).getRow(1).getCell(5);
		String Cityy= City.toString();
	    driver.findElement(By.xpath("//input[@id=\"NewAddress_City\"]")).sendKeys(Cityy);
	    
	    XSSFCell Zip = wb.getSheetAt(0).getRow(1).getCell(6);
		String Zipp= Zip.getRawValue();
	    driver.findElement(By.xpath("//input[@id=\"NewAddress_ZipPostalCode\"]")).sendKeys(Zipp);
	    
	    driver.findElement(By.xpath("//span[@title=\"Select country\"]")).click();
	    
	    driver.findElement(By.xpath("//li[@role=\"treeitem\"][2]")).click();
	   
	    XSSFCell Email = wb.getSheetAt(0).getRow(1).getCell(9);
		String email= Email.toString();
	    driver.findElement(By.xpath("//input[@id=\"NewAddress_Email\"]")).sendKeys(email);
	    
	    XSSFCell PhoneNumber = wb.getSheetAt(0).getRow(1).getCell(10);
		String Phone= PhoneNumber.getRawValue();
	    driver.findElement(By.xpath("//input[@id=\"NewAddress_PhoneNumber\"]")).sendKeys(Phone);
	    
	    XSSFCell Fax = wb.getSheetAt(0).getRow(1).getCell(11);
		String fax= Fax.getRawValue();
	    driver.findElement(By.xpath("//input[@id=\"NewAddress_FaxNumber\"]")).sendKeys(fax);
	    
	    
	    
	   
	    
//	    driver.findElement(By.xpath("//span[text()='Next']")).click();
//	    
//	   
//	    driver.findElement(By.xpath("//span[text()='Ship to this address']")).click();
//	    
//	    driver.findElement(By.xpath("//span[text()='Next']")).click(); 
//	    
//	    driver.findElement(By.xpath("//span[text()='Next']")).click(); 
//	    
//	    
//	    
//	    Assert.assertEquals(driver.getTitle(), "Checkout", "Title is not matched");
	    
	}
	
	@Test(priority=3)
	public void Payment()
	{
	driver.findElement(By.xpath("//button[@class='btn btn-warning btn-lg new-address-next-step-button']")).click();
	driver.findElement(By.xpath("//button[@class='btn btn-warning btn-block select-shipping-address-button']")).click();
	driver.findElement(By.xpath("//button[@class='btn btn-warning btn-lg shipping-method-next-step-button']")).click();
	driver.findElement(By.xpath("//button[@class='btn btn-warning btn-lg payment-method-next-step-button']")).click();
	}
	
	@Test(priority=4)
	public void Assert()
	{
	      Assert.assertEquals("Checkout",driver.getTitle());
	      System.out.println("Title is Same");
	 }
	
	@AfterTest
	public void teardown() {
		driver.close();
	}
}



