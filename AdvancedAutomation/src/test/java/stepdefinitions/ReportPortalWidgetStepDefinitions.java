package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import com.codeborne.selenide.SelenideElement;
import pages.DashboardPage;
import pages.LoginPage;
import pages.SharedWidgetsPage;
import tests.BaseTest;
import utilities.ConfigFileReader;

import static com.codeborne.selenide.Selenide.open;
import static org.testng.Assert.*;

public class ReportPortalWidgetStepDefinitions extends BaseTest {
    private LoginPage loginPage = new LoginPage();
    private DashboardPage dashboardPage = new DashboardPage();
    private final ConfigFileReader configFileReader = new ConfigFileReader();
    private SharedWidgetsPage sharedWidgetsPage = new SharedWidgetsPage();
    private String expectedTitle;
    private String actualTitle;
    private SelenideElement button;
    private SelenideElement title;
    private String widgetName;

    @Given("I am on the Report Portal login page")
    public void givenIAmOnReportPortalLoginPage() {
        // Navigate to the Report Portal login page
        final ConfigFileReader configFileReader = new ConfigFileReader();
        open(configFileReader.getProperty("url"));
    }

    @When("I login with valid credentials")
    public void whenILoginWithValidCredentials() {
        // Retrieve username and password from config.properties
        String rpUsername = configFileReader.getProperty("reportportal.username");
        String rpPassword = configFileReader.getProperty("reportportal.password");

        loginPage.login(rpUsername, rpPassword);
    }

    @Then("I should be logged in and see the All Dashboards page")
    public void thenIShouldBeLoggedInAndSeeAllDashboardsPage() {
        dashboardPage.waitForDashboardTitle();
        String expectedTab = "ALL DASHBOARDS";
        String actualTab = dashboardPage.getDashboardTitle();
        assertEquals(actualTab, expectedTab, "Verify that All Dashboards page is opened after log in");
    }

    @Given("I select corresponding test account")
    public void givenISelectCorrespondingTestAccount() {
        dashboardPage.selectTestAccount();
    }

    @When("I click on Dashboard tab")
    public void whenClickOnDashboardTab() {
        dashboardPage.clickDashboardTab();
    }

    @Then("I should be located on DEMO DASHBOARD page")
    public void thenIShouldSeeDemoDashboardPage() {
        expectedTitle = "DEMO DASHBOARD";
        actualTitle = dashboardPage.getDemoDashboardTitle().getText();
        assertEquals(expectedTitle, actualTitle, "Verify that DEMO DASHBOARD page is opened");
    }

    @Then("I should see the {string} button with correct text")
    public void thenIShouldSeeButtonWithCorrectText(String buttonName) {
        button = dashboardPage.getButton(buttonName);

        assertEquals(button.text(), buttonName, "Button '" + buttonName + "' has incorrect text.");
    }

    @Then("I should see the {string} widget title")
    public void thenIShouldSeeTitle(String titleName) {
        title = dashboardPage.getTitle(titleName);
        assertTrue(title.exists(), "Expected title '" + titleName + "' not found on page");
    }

    @When("I add shared {string} to the dashboard")
    public void whenIAddSharedToTheDashboard(String widgetName) {
        this.widgetName = widgetName;
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
    }

    @Then("I should see the {string} widget added and displayed")
    public void thenIShouldSeeWidgetAddedAndDisplayed(String widgetName) {
        SelenideElement newWidget = sharedWidgetsPage.getNewWidget(widgetName);
        assertTrue(newWidget.isDisplayed());
    }
}
