package tests;

import com.codeborne.selenide.Configuration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import utilities.ConfigFileReader;
import com.codeborne.selenide.Selenide;

import static com.codeborne.selenide.Selenide.open;

public class BaseTest {
    protected ConfigFileReader configReader;
    protected static final Logger logger = LogManager.getLogger(BaseTest.class);

    @BeforeAll
    public static void setUp() {
        try {
            Configuration.browser = "chrome";
            Configuration.browserSize = "1920x1080";

            // Initialize the ConfigFileReader
            ConfigFileReader configReader = new ConfigFileReader();
            open(configReader.getProperty("url"));
            logger.info("Browser Opened");
        } catch (Exception e) {
            logger.error("Error in setting up the test: ", e);
        }
    }

    @AfterAll
    public static void tearDown() {
        Selenide.closeWebDriver();
        logger.info("Execution Completed");
    }
}
