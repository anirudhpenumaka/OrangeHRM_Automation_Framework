package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DashboardPage extends BasePage
{
	
	//constructor
	public DashboardPage(WebDriver driver) 	{super(driver);} 
	
	//page objects
	@FindBy(xpath="//div[@class='oxd-topbar-header-title']") WebElement dashboardHeader;
	@FindBy(xpath="//span[@class='oxd-userdropdown-tab']") WebElement userDropdown;
	@FindBy(xpath="//a[normalize-space()='Logout']") WebElement logoutbutton;
	
	
	//actions
	public boolean isDashboardDisplayed()
	{
		return dashboardHeader.isDisplayed();
	}
	
	public void logout()
	{
		userDropdown.click();
		logoutbutton.click();
	}
}
