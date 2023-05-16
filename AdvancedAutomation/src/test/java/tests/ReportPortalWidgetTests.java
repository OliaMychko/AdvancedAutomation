package tests;

import com.codeborne.selenide.SelenideElement;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.LoginPage;
import pages.SharedWidgetsPage;
import utilities.ConfigFileReader;
import utilities.TestDataProviders;

import static org.testng.Assert.*;

public class ReportPortalWidgetTests extends BaseTest {
    private LoginPage loginPage = new LoginPage();
    private DashboardPage dashboardPage = new DashboardPage();
    private final ConfigFileReader configFileReader = new ConfigFileReader();

    @Test(priority = 1)
    public void testReportPortalLogin() {
        // Retrieve username and password from config.properties
        String rpUsername = configFileReader.getProperty("reportportal.username");
        String rpPassword = configFileReader.getProperty("reportportal.password");

        loginPage.login(rpUsername, rpPassword);

        // Verify successful login
        dashboardPage.waitForDashboardTitle();
        String expectedTab = "ALL DASHBOARDS";
        String actualTab = dashboardPage.getDashboardTitle();
        assertEquals(actualTab, expectedTab, "Verify that All Dashboards tab is opened after log in");
    }

    @Test(priority = 2)
    public void testWidgetsPageIsOpened() {
        dashboardPage.selectTestAccount();
        dashboardPage.clickDashboardTab();

        // Verify that right Dashboard tab is opened
        String expectedTitle = "DEMO DASHBOARD";
        String actualTitle = dashboardPage.getDashboardTitle();
        assertEquals(expectedTitle, actualTitle, "Verify that DEMO DASHBOARD page is opened");
    }
    @Test(priority = 3, dataProvider = "buttons", dataProviderClass = TestDataProviders.class)
    public void testButtonsArePresent(String buttonName) {
        SelenideElement button = dashboardPage.getButton(buttonName);

        assertEquals(button.text(), buttonName, "Button '" + buttonName + "' has incorrect text.");
    }

    @Test(priority = 4, dataProvider = "titles", dataProviderClass = TestDataProviders.class)
    public void testWidgetTitleExists(String titleName) {
        SelenideElement title = dashboardPage.getTitle(titleName);

        assertTrue(title.exists(), "Expected title '" + titleName + "' not found on page");
    }

    @Test(priority = 5, dataProvider = "sharedWidgets", dataProviderClass = TestDataProviders.class)
    public void testAddingSharedWidgetsToDashboard(String widgetName) {
        SharedWidgetsPage sharedWidgetsPage = new SharedWidgetsPage();

        // Tap on Add shared widget button
        sharedWidgetsPage.clickAddSharedWidgetButton();

        // Search available widgets
        sharedWidgetsPage.searchWidget("Demo");

        // Find and select the shared widget
        sharedWidgetsPage.selectSharedWidget(widgetName);

        // Scroll to "Add" button
        sharedWidgetsPage.scrollToAddButton();

        // Tap on "Add" button to add new widgets
        sharedWidgetsPage.clickAddButton();

        // Check that new widgets are added and displayed
        SelenideElement newWidget = sharedWidgetsPage.getNewWidget(widgetName);
        assertTrue(newWidget.isDisplayed());
    }
}
