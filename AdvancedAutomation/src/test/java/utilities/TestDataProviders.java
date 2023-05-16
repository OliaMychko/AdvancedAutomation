package utilities;

import org.testng.annotations.DataProvider;

public class TestDataProviders {

    @DataProvider(name = "sharedWidgets")
    public static Object[][] sharedWidgets() {
        return new Object[][]{
                {"DEMO_FILTER_999"},
                {"DEMO_FILTER_127"},
                {"DEMO_FILTER_546"},
                {"DEMO_FILTER_773"},
                {"DEMO_FILTER_279"},
        };
    }

    @DataProvider(name = "titles")
    public static Object[][] getTitles() {
        return new Object[][]{
                {"FAILED CASES TREND CHART"},
                {"LAUNCHES DURATION CHART"},
                {"LAUNCH STATISTICS BAR"},
                {"LAUNCH STATISTICS AREA"},
                {"OVERALL STATISTICS PANEL"},
        };
    }

    @DataProvider(name = "buttons")
    public static Object[][] getButtonNames() {
        return new Object[][]{
                {"Add new widget"},
                {"Add shared widget"},
                {"Edit"},
                {"Full screen"},
                {"Delete"},
        };
    }
}
