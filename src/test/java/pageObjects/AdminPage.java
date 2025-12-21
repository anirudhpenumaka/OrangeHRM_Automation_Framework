package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AdminPage extends BasePage {

    public AdminPage(WebDriver driver) {
        super(driver);
    }

    // ================= LOCATORS =================

    @FindBy(xpath = "//span[normalize-space()='Admin']")
    WebElement adminMenu;

    @FindBy(xpath = "//button[normalize-space()='Add']")
    WebElement addUserButton;

    // User Role
    @FindBy(xpath = "//label[text()='User Role']/../following-sibling::div//i")
    WebElement userRoleDropdown;

    @FindBy(xpath = "//div[@role='option']//span[text()='Admin']")
    WebElement adminRoleOption;

    // Status
    @FindBy(xpath = "//label[text()='Status']/../following-sibling::div//i")
    WebElement statusDropdown;

    @FindBy(xpath = "//div[@role='option']//span[text()='Enabled']")
    WebElement enabledStatusOption;

    // Employee Name
    @FindBy(xpath = "//input[@placeholder='Type for hints...']")
    WebElement employeeNameInput;

    // Username
    @FindBy(xpath = "//label[text()='Username']/../following-sibling::div/input")
    WebElement usernameInput;

    // Passwords
    @FindBy(xpath = "//label[text()='Password']/../following-sibling::div/input")
    WebElement passwordInput;

    @FindBy(xpath = "//label[text()='Confirm Password']/../following-sibling::div/input")
    WebElement confirmPasswordInput;

    @FindBy(xpath = "//button[normalize-space()='Save']")
    WebElement saveButton;

    // ================= ACTIONS =================

    public void openAdminPage() {
        wait.until(ExpectedConditions.elementToBeClickable(adminMenu)).click();
    }

    public void clickAddUser() {
        wait.until(ExpectedConditions.elementToBeClickable(addUserButton)).click();
    }

    public void selectAdminRole() {
        userRoleDropdown.click();
        wait.until(ExpectedConditions.visibilityOf(adminRoleOption)).click();
    }

    public void selectEnabledStatus() {
        statusDropdown.click();
        wait.until(ExpectedConditions.visibilityOf(enabledStatusOption)).click();
    }

    public void enterEmployeeName(String empName) {

        wait.until(ExpectedConditions.visibilityOf(employeeNameInput))
            .sendKeys(empName);

        // 🔑 Angular autocomplete → keyboard selection
        wait.until(driver -> employeeNameInput.getAttribute("value").length() > 2);

        employeeNameInput.sendKeys(Keys.ARROW_DOWN);
        employeeNameInput.sendKeys(Keys.ENTER);
    }

    public void enterUsername(String username) {
        usernameInput.sendKeys(username);
    }

    public void enterPassword(String password) {
        passwordInput.sendKeys(password);
        confirmPasswordInput.sendKeys(password);
    }

    public void clickSave() {
        saveButton.click();
    }
}
