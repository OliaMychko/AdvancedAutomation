package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.ConfigFileReader;

import java.time.Duration;

public class ReportPortalLoginTest extends BaseTest {

    @Test
    public void testReportPortalLogin() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[name='login']")));
        // Retrieve username and password from config.properties
        ConfigFileReader configFileReader = new ConfigFileReader();
        String rpUsername = configFileReader.getProperty("reportportal.username");
        String rpPassword = configFileReader.getProperty("reportportal.password");

        // Enter username and password
        driver.findElement(By.cssSelector("input[name='login']")).sendKeys(rpUsername);
        driver.findElement(By.cssSelector("input[name='password']")).sendKeys(rpPassword);

        // Click on login button
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        // Verify successful login
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span[title='All Dashboards']")));
        String expectedTab= "ALL DASHBOARDS";
        String actualTab = driver.findElement(By.cssSelector("span[title='All Dashboards']")).getText();
        assertEquals(expectedTab, actualTab, "Verify that All Dashboards tab is opened after log in");
    }
}
