package saytha;

import java.util.List;import java.io.File;
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
import org.openqa.selenium.NoSuchContextException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;





@Test
public class CCTV {
public WebDriver driver;
	
	@BeforeTest
	public void Camera() {
	System.setProperty("webdriver.chrome.driver","\\C:\\Users\\Kalai\\Desktop\\Selenium\\Driver\\chromedriver.exe\\");
	driver= new ChromeDriver();
	driver.manage().window().maximize();
	driver.manage().timeouts().pageLoadTimeout(30,TimeUnit.SECONDS);
	driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS );
	driver.get("https://www.sathya.in/");
	

	}
	public void Cam() 
{
	
	driver.findElement(By.linkText("Security Solutions")).click();
	driver.findElement(By.linkText("CCTV camera (42)")).click();
	
	
	
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	Select DropDown = new Select(driver.findElement(By.xpath("//select[@id=\"artlist-action-pagesize\"]")));
	DropDown.selectByVisibleText("120");
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	List<WebElement> Count = driver.findElements(By.xpath("//a[@class=\"art-picture img-center-container\"]"));
	int ccount = Count.size();
	System.out.println(ccount);
	
	for (int i = 0; i<ccount;i++)
	{
		List<WebElement> name = driver.findElements(By.xpath("//a[@class=\\\"art-picture img-center-container\\\"]"));
		String namee = name.get(i).getAttribute("title");
		System.out.println(namee);
	}}
	public void ExcelWrite() throws IOException, InterruptedException
	{
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Select Count_Dropdown = new Select(driver.findElement(By.xpath("//select[@id=\"artlist-action-pagesize\"]")));
		Count_Dropdown.selectByVisibleText("120");
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		
		
		Workbook work = new XSSFWorkbook();
		Sheet sheet = work.createSheet("CCTV Camera");
		
		String pageXpath[]= {"pd-name pd-name-sm", "pd-price pd-price--offer", "pd-saving-percent"};
		List<WebElement> CCTV_Count= driver.findElements(By.xpath("//a[@class='art-picture img-center-container']"));
		//String basWin= driver.getWindowHandle();
		Actions builder = new Actions(driver);
		
		int Total_CCTV=CCTV_Count.size();
		System.out.println("total:"+Total_CCTV);
		System.out.println(driver.getTitle());
		
		Row newRow = sheet.createRow(0);
		newRow.createCell(0).setCellValue("Product Name");
		newRow.createCell(1).setCellValue("Product Price");
		newRow.createCell(2).setCellValue("Offer details ");
		
		
		for(int i=0; i<Total_CCTV;i++)
		{
			try {
				String read = "//a[@class='art-picture img-center-container'][@title='"; 
				String mk = read +CCTV_Count.get(i).getAttribute("title")+ "']";
				System.out.println(i +": "+mk);
				builder.moveToElement(driver.findElement(By.xpath(mk))).click().build().perform();
				Row newRow1 = sheet.createRow(i+1);
				for(int j = 0;j<pageXpath.length;j++) 
				 {
					Cell cell = newRow1.createCell(j);
					String pass = "//*[@class = '"+pageXpath[j]+"']"; 
					try {
						System.out.println(driver.findElement(By.xpath(pass)).getText().toString());
						cell.setCellValue(driver.findElement(By.xpath(pass)).getText().toString());
					}
					
					catch(NoSuchElementException y)
					{
						cell.setCellValue("Nill");
						//cell.setCellValue(driver.findElement(By.xpath("//*[@class=\"pd-pricepd-price\"]")).getText().toString());

					}
					catch(NoSuchContextException e) { 
						System.out.println("Exception");
						cell.setCellValue("Nil");
					}
				}
				driver.navigate().back();
				Thread.sleep(2000);
		}
			catch(StaleElementReferenceException e) {
				i--;
				CCTV_Count= driver.findElements(By.xpath("//a[@class='art-picture img-center-container']"));
			}
			
//			
		}
		FileOutputStream fos = new FileOutputStream(new File("D:\\Sathya_CCTV_PS.xlsx"));
		work.write(fos);
		fos.close();
	}	

	
	@AfterTest
		public void Exit() 
		{
			driver.close();
			System.out.println("After Test");
		}
		
		

}
