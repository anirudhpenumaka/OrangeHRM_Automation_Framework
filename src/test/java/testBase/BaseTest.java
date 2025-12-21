package testBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.net.URL;

public class BaseTest {

    public Logger logger;
    protected static WebDriver driver;
    protected Properties config;

    @BeforeMethod
    @Parameters({"os", "browser"})
    public void setup(@Optional("windows") String os,
                      @Optional("chrome") String browser) throws IOException {

        logger = LogManager.getLogger(this.getClass());

        // ✅ STEP 1: LOAD CONFIG FIRST (VERY IMPORTANT)
        config = new Properties();
        FileInputStream file = new FileInputStream(
                System.getProperty("user.dir") + "/src/test/resources/config.properties");
        config.load(file);

        String executionEnv = config.getProperty("execution_env").trim();

        
        if (executionEnv.equalsIgnoreCase("remote")) {

            DesiredCapabilities capabilities = new DesiredCapabilities();

            if (os.equalsIgnoreCase("windows")) {
                capabilities.setPlatform(Platform.WIN11);
            } else if (os.equalsIgnoreCase("mac")) {
                capabilities.setPlatform(Platform.MAC);
            }

            capabilities.setBrowserName(browser.toLowerCase());

            driver = new RemoteWebDriver(
                    new URL("http://localhost:4444/wd/hub"),
                    capabilities
            );
        }

      
        else {
            switch (browser.toLowerCase()) {
                case "edge":
                    driver = new EdgeDriver();
                    break;
                case "firefox":
                    driver = new FirefoxDriver();
                    break;
                default:
                    driver = new ChromeDriver();
                    break;
            }
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        logger.info("Launching application");
        driver.get(config.getProperty("appURL"));
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    
    public static String captureScreen(String testName) throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        TakesScreenshot ts = (TakesScreenshot) driver;
        File src = ts.getScreenshotAs(OutputType.FILE);

        String targetPath = System.getProperty("user.dir")
                + "/screenshots/" + testName + "_" + timeStamp + ".png";

        File target = new File(targetPath);
        src.renameTo(target);

        return targetPath;
    }
}
