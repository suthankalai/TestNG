package saytha;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Electronics {
	public WebDriver driver;
	
	@BeforeTest
	public void KA() {
	System.setProperty("webdriver.chrome.driver","\\C:\\Users\\Kalai\\Desktop\\Selenium\\Driver\\chromedriver.exe\\");
	driver= new ChromeDriver();
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS );
	driver.get("https://www.sathya.in/");
	driver.manage().timeouts().pageLoadTimeout(30,TimeUnit.SECONDS);

}
	@Test(priority=1)
	public void Ele() throws InterruptedException, IOException
	{
		
		driver.findElement(By.linkText("Electronics")).click();
		driver.findElement(By.linkText("Laptop")).click();
		driver.findElement(By.linkText("Lenovo")).click();
		
		
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

			
		}
		

		Actions builder = new Actions(driver);
		Workbook work = new XSSFWorkbook();
		Sheet sht = work.createSheet("Laptop");
		String pageXpath[]= {"pd-name pd-name-sm","pd-price"};
		
		Row newRow = sht.createRow(0);
		newRow.createCell(0).setCellValue("Product Name");
		newRow.createCell(1).setCellValue("Product Price");
		
		for(int i=0; i<ccount;i++)
		{
			try {
				String read = "//a[@class='art-picture img-center-container'][@title='"; 
				String mk = read +Count.get(i).getAttribute("title")+ "']";
				System.out.println(i +": "+mk);
				builder.moveToElement(driver.findElement(By.xpath(mk))).click().build().perform();
				Row newRow1 = sht.createRow(i+1);
				for(int j = 0;j<pageXpath.length;j++) 
				 {
					Cell cell = newRow1.createCell(j);
					String pass = "//*[@class = '"+pageXpath[j]+"']"; 
					cell.setCellValue(driver.findElement(By.xpath(pass)).getText().toString());}
				 driver.navigate().back();
				Thread.sleep(2000);
		}
			catch(StaleElementReferenceException  e) {
				i--;
				Count= driver.findElements(By.xpath("//a[@class='art-picture img-center-container']"));
			}
			}
		
		FileOutputStream fos = new FileOutputStream(new File("E:\\sathya.xlsx"));
		work.write(fos);
		fos.close();
		work.close();
	}
			 
	}
