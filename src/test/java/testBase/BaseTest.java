package testBase;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

public class BaseTest 
{
	protected WebDriver driver;
	protected Properties config;  //created a config. file and now we are uploading the data
	
	@BeforeMethod
	public void setup() throws IOException
	{
		driver=new ChromeDriver();
		config = new Properties();
		
		
		//C:\Java Workspace\Automation_Frameworks\OrangeHRM_Automation_Framework\src\test\resources\config.properties
		FileInputStream file=new FileInputStream(System.getProperty("user.dir")+"/src/test/resources/config.properties");
		config.load(file);
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		//Launching application
		driver.get(config.getProperty("appURL"));
		
	}
	
	@AfterMethod
	public void tearDown() throws InterruptedException {
	    System.out.println("Closing browser");
	    Thread.sleep(3000);
	    driver.quit();
	}
}
