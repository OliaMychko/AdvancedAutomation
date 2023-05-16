package stepdefinitions;

import com.codeborne.selenide.Selenide;
import io.cucumber.java.After;

public class UIHooks {
    @After
    public void tearDown() {
        Selenide.closeWindow();
    }
}
