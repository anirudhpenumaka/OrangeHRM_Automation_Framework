package testCases;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.AdminPage;
import pageObjects.LoginPage;
import testBase.BaseTest;

public class AdminTest extends BaseTest {

    LoginPage loginPage;
    AdminPage adminPage;

    @Test(dataProvider = "userData")
    public void addUserTest(String role,
                            String employeeName,
                            String username,
                            String status,
                            String password) {

        logger.info("===== START: Add User Test =====");

        // Login
        loginPage = new LoginPage(driver);
        logger.info("Logging in with Admin credentials");
        loginPage.loginAs(
                config.getProperty("username"),
                config.getProperty("password")
        );

        // Navigate to Admin
        adminPage = new AdminPage(driver);
        logger.info("Navigating to Admin module");
        adminPage.clickAdminMenu();

        // Add User
        logger.info("Clicking Add User");
        adminPage.clickAddUser();

        logger.info("Selecting User Role: " + role);
        adminPage.selectUserRole(role);

        logger.info("Entering Employee Name: " + employeeName);
        adminPage.enterEmployeeName(employeeName);

        logger.info("Entering Username: " + username);
        adminPage.enterUsername(username);

        logger.info("Selecting Status: " + status);
        adminPage.selectStatus(status);

        logger.info("Entering Password");
        adminPage.enterPassword(password);
        adminPage.enterConfirmPassword(password);

        logger.info("Saving user");
        adminPage.clickSave();

        // Verification
        logger.info("Searching for created user");
        adminPage.searchUser(username);

        Assert.assertTrue(
                adminPage.isUserPresent(username),
                "User creation failed for username: " + username
        );

        logger.info("User created successfully: " + username);
        logger.info("===== END: Add User Test =====");
    }

    // ================== DATA PROVIDER ==================

    @DataProvider(name = "userData")
    public Object[][] getUserData() {

        return new Object[][] {
                { "ESS", "Paul Collings", "testuser01", "Enabled", "Test@123" },
                { "Admin", "Paul Collings", "testuser02", "Enabled", "Test@123" }
        };
    }
}
