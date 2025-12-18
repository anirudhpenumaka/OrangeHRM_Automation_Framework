package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AdminPage extends BasePage {

    public AdminPage(WebDriver driver) {
        super(driver);
    }

    // ================== PAGE LOCATORS ==================

    @FindBy(xpath = "//span[normalize-space()='Admin']")
    WebElement adminMenu;

    @FindBy(xpath = "//button[normalize-space()='Add']")
    WebElement addUserButton;

    /*@FindBy(xpath = "//div[@class='oxd-select-text oxd-select-text--focus']//div[@class='oxd-select-text-input'][normalize-space()='-- Select --']")
    WebElement userRoleDropdown;*/

    @FindBy(xpath = "//input[@placeholder='Type for hints...']")
    WebElement employeeNameInput;

    @FindBy(xpath = "//div[@class='oxd-form-row']//div[@class='oxd-grid-2 orangehrm-full-width-grid']//div[@class='oxd-grid-item oxd-grid-item--gutters']//div[@class='oxd-input-group oxd-input-field-bottom-space']//div//input[@class='oxd-input oxd-input--active']")
    WebElement usernameInput;

    @FindBy(xpath = "//div[@class='oxd-grid-item oxd-grid-item--gutters user-password-cell']//div[@class='oxd-input-group oxd-input-field-bottom-space']//div//input[@type='password']")
    WebElement passwordInput;

    @FindBy(xpath = "//div[@class='oxd-grid-item oxd-grid-item--gutters']//div[@class='oxd-input-group oxd-input-field-bottom-space']//div//input[@type='password']")
    WebElement confirmPasswordInput;

    @FindBy(xpath = "//button[normalize-space()='Save']")
    WebElement saveButton;

    // ================== ACTION METHODS ==================

    public void clickAdminMenu() {
        wait.until(ExpectedConditions.elementToBeClickable(adminMenu)).click();
    }

    public void clickAddUser() {
        wait.until(ExpectedConditions.elementToBeClickable(addUserButton)).click();
    }

    public void selectUserRole(String role) {
        WebElement userRoleDropdown = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//div[text()='-- Select --']")));
        userRoleDropdown.click();

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//span[text()='" + role + "']")
                )).click();
    }

    public void enterEmployeeName(String employeeName) {
        wait.until(ExpectedConditions.visibilityOf(employeeNameInput))
                .sendKeys(employeeName);

        wait.until(ExpectedConditions.elementToBeClickable(
                driver.findElement(By.xpath("//span[text()='" + employeeName + "']"))
        )).click();
    }

    public void enterUsername(String username) {
        wait.until(ExpectedConditions.visibilityOf(usernameInput)).clear();
        usernameInput.sendKeys(username);
    }

    public void selectStatus(String status) {

        // Click Status dropdown
        WebElement statusDropdown = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//label[text()='Status']/../following-sibling::div")
                )
        );
        statusDropdown.click();

        // Select Enabled / Disabled
        wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//div[text()='" + status + "']")
                )
        ).click();
    }

    public void enterPassword(String password) {
        wait.until(ExpectedConditions.visibilityOf(passwordInput))
                .sendKeys(password);
    }

    public void enterConfirmPassword(String password) {
        wait.until(ExpectedConditions.visibilityOf(confirmPasswordInput))
                .sendKeys(password);
    }

    public void clickSave() {
        wait.until(ExpectedConditions.elementToBeClickable(saveButton)).click();
    }

    public void searchUser(String username) {
        WebElement searchInput =
                driver.findElement(By.xpath("//label[text()='Username']/../following-sibling::div/input"));

        wait.until(ExpectedConditions.visibilityOf(searchInput)).sendKeys(username);

        driver.findElement(By.xpath("//button[@type='submit']")).click();
    }

    public boolean isUserPresent(String username) {
        return wait.until(ExpectedConditions.visibilityOf(
                driver.findElement(
                        By.xpath("//div[@class='oxd-table-cell']//span[text()='" + username + "']")
                )
        )).isDisplayed();
    }
}
