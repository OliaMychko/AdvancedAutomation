package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import utilities.ConfigFileReader;


public class BaseTest {
    protected WebDriver driver;
    protected ConfigFileReader configReader;
    public static Logger logger = LogManager.getLogger();


    @BeforeEach
    public void setUp() {
        try {
            ChromeOptions option = new ChromeOptions();
            option.addArguments("--remote-allow-origins=*");

            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver(option);
            driver.manage().window().maximize();
            logger.info("Drivers Initialised");

            // Initialize the ConfigFileReader
            configReader = new ConfigFileReader();
            driver.get(configReader.getProperty("url"));
            logger.info("Browser Opened");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @AfterEach
    public void tearDown() {
        logger.info("Execution Completed");
        if (driver != null) {
            driver.quit();
        }
    }
}