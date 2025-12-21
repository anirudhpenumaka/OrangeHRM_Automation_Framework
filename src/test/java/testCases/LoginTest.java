package testCases;

import testBase.BaseTest;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.LoginPage;

public class LoginTest extends BaseTest
{
	LoginPage loginpage;
	
	
	@Test
	public void logintest() {

	    logger.info("===== START: Valid Login Test =====");

	    loginpage = new LoginPage(driver);
	    logger.info("Entering valid username and password");
	       
	    loginpage.loginAs("Admin", "admin123");

	    logger.info("Validating dashboard URL after login");
	    Assert.assertTrue(driver.getCurrentUrl().contains("dashboard"),
	            "Valid login failed: Dashboard not loaded");

	    logger.info("Valid login test PASSED");
	    logger.info("===== END: Valid Login Test =====");
	}
	
	@Test
	public void invalidLoginTest() {

	    logger.info("===== START: Invalid Login Test =====");

	    loginpage = new LoginPage(driver);
	    logger.info("Entering invalid password");

	    loginpage.loginAs("Admin", "wrongpassword");

	    logger.info("Validating error message for invalid credentials");
	    Assert.assertTrue(loginpage.isErrorMessageDisplayed(),
	            "Error message not displayed for invalid credentials");

	    logger.info("Invalid login test PASSED");
	    logger.info("===== END: Invalid Login Test =====");
	}
	 
	@Test
	public void blankCredentialsTest() {

	    logger.info("===== START: Blank Credentials Login Test =====");

	    loginpage = new LoginPage(driver);
	    logger.info("Attempting login with blank username and password");

	    loginpage.loginAs("", "");

	    logger.info("Validating error message for blank credentials");
	    Assert.assertTrue(loginpage.isErrorMessageDisplayed(),
	            "Error message not displayed for blank credentials");

	    logger.info("Blank credentials login test PASSED");
	    logger.info("===== END: Blank Credentials Login Test =====");
	}
	 
	@Test
	public void loginPageUIValidationTest() {

	    logger.info("===== START: Login Page UI Validation Test =====");

	    loginpage = new LoginPage(driver);

	    logger.info("Validating password field masking");
	    Assert.assertTrue(loginpage.isPasswordMasked(),
	            "Password field is not masked");

	    logger.info("Validating login page title");
	    Assert.assertTrue(driver.getTitle().contains("OrangeHRM"),
	            "Login page title mismatch");

	    logger.info("Validating login page URL");
	    Assert.assertTrue(driver.getCurrentUrl().contains("orangehrmlive"),
	            "Login page URL mismatch");

	    logger.info("Login page UI validation test PASSED");
	    logger.info("===== END: Login Page UI Validation Test =====");
	}
}
