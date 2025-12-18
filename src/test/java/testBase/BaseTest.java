package testBase;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;


public class BaseTest 
{
	
	public Logger logger; //log4j
	protected WebDriver driver;
	protected Properties config;  //created a config. file and now we are uploading the data
	
	@BeforeMethod
	@Parameters({"browser"})
	public void setup(String br) throws IOException
	{
		logger=LogManager.getLogger(this.getClass());
		
		switch(br.toLowerCase())
		{
			case "chrome" : driver=new ChromeDriver();break;
			case "edge" : driver=new EdgeDriver();break;
			default : System.out.println("Invalid browser");return;
		}
		
		config = new Properties();
		
		//C:\Java Workspace\Automation_Frameworks\OrangeHRM_Automation_Framework\src\test\resources\config.properties
		FileInputStream file=new FileInputStream(System.getProperty("user.dir")+"/src/test/resources/config.properties");
		config.load(file);
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		//Launching application
		logger.info("Launching browser and opening application");
		driver.get(config.getProperty("appURL"));
		
	}
	
	@AfterMethod
	public void tearDown() throws InterruptedException {
		logger.info("Closing browser");
	    Thread.sleep(3000);
	    driver.quit();
	}
}
