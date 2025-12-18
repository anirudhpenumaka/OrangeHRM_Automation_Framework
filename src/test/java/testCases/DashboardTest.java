package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.DashboardPage;
import pageObjects.LoginPage;
import testBase.BaseTest;

public class DashboardTest extends BaseTest
{
	
	
	
	@Test
	public void dashboardAndLogoutTest()
	{
		logger.info("===== START: Dashboard and Logout Test =====");
		
		//First we have to login
		LoginPage loginPage = new LoginPage(driver);
		 logger.info("Logging in with valid credentials");
		 loginPage.loginAs("Admin", "admin123");
		 
		 //now checking the dashboard
		 DashboardPage dashboardPage=new DashboardPage(driver);
		 logger.info("Validating dashboard visibility");
	        Assert.assertTrue(dashboardPage.isDashboardDisplayed(),
	                "Dashboard is not displayed after login");
	        
	        // Logout
	        logger.info("Performing logout");
	        dashboardPage.logout();
	        
	     // Session validation
	        logger.info("Validating logout redirection");
	        Assert.assertTrue(driver.getCurrentUrl().contains("auth/login"),
	                "Logout failed or user not redirected to login page");
	        
	     // Back button check
	        driver.navigate().back();
	        Assert.assertTrue(driver.getCurrentUrl().contains("auth/login"),
	                "Session not terminated properly after logout");
	        
	        logger.info("Dashboard and Logout Test PASSED");
	        logger.info("===== END: Dashboard and Logout Test =====");
		 
	}
}
