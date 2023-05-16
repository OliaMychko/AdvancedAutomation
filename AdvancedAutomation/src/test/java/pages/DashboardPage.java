package pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class DashboardPage {
    private SelenideElement testAccount = $(By.xpath("(//div)[10]"));
    private SelenideElement accountLink = $(By.xpath("//div[@data-placement='right-end']//div//div//a[@href='#olyamychko_personal']//span[@title='olyamychko_personal'][normalize-space()='olyamychko_personal']"));
    private SelenideElement dashboardTab = $(By.xpath("(//a[contains(text(),'DEMO DASHBOARD')])[2]"));
    private SelenideElement dashboardTitle = $(By.xpath("(//span[@title='All Dashboards'])[1]"));
    private SelenideElement demoDashboardTitle = $(By.xpath("//span[@title='DEMO DASHBOARD']"));

    public void clickDashboardTab() {
        dashboardTab.shouldBe(visible).click();
    }

    public String getDashboardTitle() {
        return dashboardTitle.getText();
    }

    public void selectTestAccount() {
        testAccount.click();
        accountLink.shouldBe(visible).click();
    }

    public void waitForDashboardTitle() {
        dashboardTitle.shouldBe(visible);
    }
    public SelenideElement getButton(String buttonName) {
        return $x("//span[normalize-space()='" + buttonName + "']");
    }
    public SelenideElement getTitle(String titleName) {
        return $(By.xpath("//div[contains(text(),'" + titleName + "')]")).shouldBe(visible);
    }

    public SelenideElement getDemoDashboardTitle() {
        return demoDashboardTitle.shouldBe(visible);
    }
}