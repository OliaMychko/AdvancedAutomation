package tests;

import com.codeborne.selenide.Configuration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import utilities.ConfigFileReader;
import com.codeborne.selenide.Selenide;

import static com.codeborne.selenide.Selenide.open;

public class BaseTest {
    protected ConfigFileReader configReader;
    protected static final Logger logger = LogManager.getLogger(BaseTest.class);

    @BeforeSuite
    public void setUp() {
        try {
            Configuration.browser = "chrome";
            Configuration.browserSize = "1920x1080";

            // Initialize the ConfigFileReader
            configReader = new ConfigFileReader();
            open(configReader.getProperty("url"));
            logger.info("Browser Opened");
        } catch (Exception e) {
            logger.error("Error in setting up the test: ", e);
        }
    }

    @AfterSuite
    public void tearDown() {
        Selenide.closeWebDriver();
        logger.info("Execution Completed");
    }
}
