package pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class SharedWidgetsPage {
    public void clickAddSharedWidgetButton() {
        $(By.xpath("//span[normalize-space()='Add shared widget']")).click();
    }

    public void searchWidget(String widgetName) {
        $(By.xpath("//input[@placeholder='Search by name, owner']"))
                .shouldBe(visible)
                .setValue("Demo");
    }

    public void selectSharedWidget(String widgetName) {
        $(By.xpath("//span[@class='inputRadio__children-container--32kGF inputRadio__mode-default--3MEUz']//span[contains(text(),'" + widgetName + "')]"))
                .shouldBe(visible)
                .shouldHave(text(widgetName))
                .click();
    }

    public void scrollToAddButton() {
        $(By.xpath("//button[normalize-space()='Add']")).scrollIntoView(true);
    }

    public void clickAddButton() {
        $(By.xpath("//button[normalize-space()='Add']")).shouldBe(visible).click();
    }

    public SelenideElement getNewWidget(String widgetName) {
        return $(By.xpath("//div[contains(text(),'" + widgetName + "')]")).shouldBe(visible);
    }
}

