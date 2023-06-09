package tests;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import utilities.ConfigFileReader;
import utilities.TestDataProviders;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;


import static org.testng.Assert.*;

public class ReportPortalWidgetTests extends BaseTest {

    @Test(priority = 1)
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

    @Test(priority = 2)
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

    @Test(priority = 3, dataProvider = "buttons",dataProviderClass = TestDataProviders.class)
    public void testButtonsArePresent(String buttonName) {
        SelenideElement button = $x("//span[normalize-space()='" + buttonName + "']");

        assertEquals(button.text(), buttonName, "Button '" + buttonName + "' has incorrect text.");
    }

    @Test(priority = 4, dataProvider = "titles",dataProviderClass = TestDataProviders.class)
    public void testWidgetTitleExists(String titleName) {
        SelenideElement title=$(By.xpath("//div[contains(text(),'" + titleName + "')]")).shouldBe(visible);

        // Verify that the expected titles names are present on the page
        assertTrue(title.exists(), "Expected title '" + titleName + "' not found on page");


    }

    @Test(priority = 5, dataProvider = "sharedWidgets",dataProviderClass = TestDataProviders.class)
    public void testAddingSharedWidgetsToDashboard(String widgetName) {
        //tap on Add shared widget button
        $(By.xpath("//span[normalize-space()='Add shared widget']")).click();

        //Search available widgets
        $(By.xpath("//input[@placeholder='Search by name, owner']"))
                .shouldBe(visible)
                .setValue("Demo");

        // Find the available shared widgets and select the one with the given name
        $(By.xpath("//span[@class='inputRadio__children-container--32kGF inputRadio__mode-default--3MEUz']//span[contains(text(),'" + widgetName + "')]"))
                .shouldBe(visible)
                .shouldHave(text(widgetName))
                .click();

        //Scroll to "Add" button to make it visible
        $(By.xpath("//button[normalize-space()='Add']")).scrollIntoView(true);

        //Tap on "Add" button to add new widgets
        $(By.xpath("//button[normalize-space()='Add']")).shouldBe(visible).click();

        //Check that new widgets are added and display
        SelenideElement newWidget = $(By.xpath("//div[contains(text(),'" + widgetName + "')]")).shouldBe(visible);
        assertTrue(newWidget.isDisplayed());
    }
}
