package kalai;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class browser {
	 static WebDriver driver;
		
		
		public static WebDriver browser(String Browser) {
			/*if(Browser.equalsIgnoreCase("firefox")) {
				System.setProperty("webdriver.gecko.driver", "C:\\New_folder\\geckodriver\\geckodriver.exe");
				driver= new FirefoxDriver();
				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				System.out.println("Firefox opened successfully");
			}*/
			 if (Browser.equalsIgnoreCase("chrome")) {
				System.setProperty("webdriver.chrome.driver", "\\C:\\Users\\Kalai\\Desktop\\Selenium\\Driver\\chromedriver.exe\\");
				driver=new ChromeDriver();
				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				System.out.println("Chrome opened successfully");
			}
			
			else if(Browser.equalsIgnoreCase("Edge")) {
				System.setProperty("webdriver.edge.driver", "C:\\New_folder\\MicrosoftWebDriver.exe");
				driver=new EdgeDriver();
				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				System.out.println("Edge opened successfully");
				}
			

			return driver;
			

		}
		}



