package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage
{
	//constructor
	public LoginPage(WebDriver driver) 	{super(driver);}  //This is to initiate driver
	
	//locator
	@FindBy(xpath="//input[@placeholder='Username']") WebElement usernameInput;
	@FindBy(xpath="//input[@placeholder='Password']") WebElement passwordInput;
	@FindBy(xpath="//button[normalize-space()='Login']") WebElement loginButton;
	
	@FindBy(xpath = "//p[contains(@class,'oxd-alert-content-text')]") WebElement errorMessage;
	
	
	//Actions
	public void enterUsername(String username)
	{
		usernameInput.sendKeys(username);
	}
	
	public void enterPassword(String password)
	{
		passwordInput.sendKeys(password);
	}
	
	public void clickLogin()
	{
		loginButton.click();
	}
	
	public void loginAs(String username, String password) 
	{
        enterUsername(username);
        enterPassword(password);
        clickLogin();
    }
	
	public boolean isErrorMessageDisplayed() {
	    return errorMessage.isDisplayed();
	}
	
	public boolean isPasswordMasked() {
	    return passwordInput.getAttribute("type").equals("password");
	}
	
}
