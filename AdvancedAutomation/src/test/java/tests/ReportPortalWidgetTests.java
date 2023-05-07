package tests;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.By;
import utilities.ConfigFileReader;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ReportPortalWidgetTests extends BaseTest {

    @Order(1)
    @Test
    public void testReportPortalLogin() {
        // Retrieve username and password from config.properties
        ConfigFileReader configFileReader = new ConfigFileReader();
        String rpUsername = configFileReader.getProperty("reportportal.username");
        String rpPassword = configFileReader.getProperty("reportportal.password");

        // Enter username and password
        $(By.name("login")).setValue(rpUsername);
        $(By.name("password")).setValue(rpPassword);

        // Click on login button
        $(By.cssSelector("button[type='submit']")).click();

        // Verify successful login
        $(By.cssSelector("span[title='All Dashboards']")).shouldBe(visible);
        String expectedTab = "ALL DASHBOARDS";
        String actualTab = $(By.cssSelector("span[title='All Dashboards']")).getText();
        assertEquals(expectedTab, actualTab, "Verify that All Dashboards tab is opened after log in");
    }

    @Order(2)
    @Test
    public void testWidgetsPageIsOpened() {

        // Select test account
        $(By.xpath("(//div)[10]")).click();
        $(By.xpath("//div[@data-placement='right-end']//div//div//a[@href='#olyamychko_personal']//span[@title='olyamychko_personal'][normalize-space()='olyamychko_personal']")).shouldBe(visible).click();

        //Click on Dashboard tab to open it
        $(By.xpath("(//a[contains(text(),'DEMO DASHBOARD')])[2]")).shouldBe(visible).click();

        //Verify that Dashboard tab is opened
        String expectedTitle = "DEMO DASHBOARD";
        String actualTitle = $(By.cssSelector("span[title='DEMO DASHBOARD']")).getText();
        assertEquals(expectedTitle, actualTitle, "Verify that DEMO DASHBOARD page is opened");
    }

    @Order(3)
    @DisplayName("Test all buttons are present")
    @ParameterizedTest
    @ValueSource(strings = {"Add new widget", "Add shared widget", "Edit", "Full screen", "Delete"})
    public void testButtonsArePresent(String buttonName) {
        SelenideElement button = $(By.xpath("//span[normalize-space()='" + buttonName + "']"));

        assertEquals(button.text(), buttonName, "Button '" + buttonName + "' has incorrect text.");
    }

    @Order(4)
    @DisplayName("Test all widget titles exist")
    @ParameterizedTest
    @ValueSource(strings = {"FAILED CASES TREND CHART", "LAUNCHES DURATION CHART", "LAUNCH STATISTICS BAR", "LAUNCH STATISTICS AREA", "OVERALL STATISTICS PANEL"})
    public void testWidgetTitleExists(String titleName) {
        SelenideElement title = $(By.xpath("//div[contains(text(),'" + titleName + "')]")).shouldBe(visible);

        // Verify that the expected titles names are present on the page
        assertTrue(title.exists(), "Expected title '" + titleName + "' not found on page");
    }

    @Order(5)
    @DisplayName("Test adding shared widgets to dashboard")
    @ParameterizedTest
    @ValueSource(strings = {"DEMO_FILTER_999", "DEMO_FILTER_127", "DEMO_FILTER_546", "DEMO_FILTER_773", "DEMO_FILTER_279"})
    public void testAddingSharedWidgetsToDashboard(String widgetName) {
        //tap on Add shared widget button
        $(By.xpath("//span[normalize-space()='Add shared widget']")).click();

        //Search available widgets
        $(By.xpath("//input[@placeholder='Search by name, owner']"))
                .shouldBe(visible)
                .setValue("Demo");

        // Find the available shared widgets and select the one with the given name
        $(By.xpath("//span[@class='inputRadio__children-container--32kGF inputRadio__mode-default--3MEUz']//span[contains(text(),'" + widgetName + "')]"))
                .shouldBe(visible);
    }
}