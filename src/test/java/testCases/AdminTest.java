package testCases;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.AdminPage;
import pageObjects.LoginPage;
import testBase.BaseTest;

public class AdminTest extends BaseTest {

    @Test(dataProvider = "adminData", groups = {"admin"})
    public void addAdminUserTest(String employeeName,
                                 String username,
                                 String password) {

        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginAs(
                config.getProperty("username"),
                config.getProperty("password")
        );

        AdminPage adminPage = new AdminPage(driver);

        adminPage.openAdminPage();
        adminPage.clickAddUser();

        adminPage.selectAdminRole();
        adminPage.selectEnabledStatus();

        adminPage.enterEmployeeName(employeeName);
        adminPage.enterUsername(username);
        adminPage.enterPassword(password);

        adminPage.clickSave();
    }

    @DataProvider(name = "adminData")
    public Object[][] getAdminData() {
        return new Object[][]{
                {"Paul Collings", "admin_user_01", "Test@123"}
        };
    }
}