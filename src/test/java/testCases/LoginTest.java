package testCases;

import testBase.BaseTest;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.LoginPage;

public class LoginTest extends BaseTest
{
	LoginPage loginpage;
	
	@Test(priority=1)
	public void logintest()
	{
		loginpage =new LoginPage(driver);
		loginpage.loginAs("Admin","admin123");
		
		//validation
		Assert.assertTrue(driver.getCurrentUrl().contains("dashboard"),"Valid login failed: Dashboard not loaded");
	}
	
	 @Test(priority = 2)
	    public void invalidLoginTest() {

		 loginpage = new LoginPage(driver);
		 loginpage.loginAs("Admin","wrongpassword");

		 Assert.assertTrue(loginpage.isErrorMessageDisplayed());
	    }
	 
	 @Test(priority = 3)
	    public void blankCredentialsTest() {

		 loginpage = new LoginPage(driver);
		 loginpage.loginAs("","");

		 Assert.assertTrue(driver.getCurrentUrl().contains("auth/login"),"User navigated away from login page with blank credentials");
	    }
	 
	 @Test(priority = 4)
	    public void loginPageUIValidationTest() {

		 loginpage = new LoginPage(driver);

	        Assert.assertTrue(loginpage.isPasswordMasked(), "Password field is not masked");

	        Assert.assertTrue(driver.getTitle().contains("OrangeHRM"),
	                "Login page title mismatch");

	        Assert.assertTrue(driver.getCurrentUrl().contains("orangehrmlive"),
	                "Login page URL mismatch");
	    }
}
