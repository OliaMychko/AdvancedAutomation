package pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;



public class LoginPage {
    public void login(String username, String password) {
        $(By.name("login")).setValue(username);
        $(By.name("password")).setValue(password);
        $(By.cssSelector("button[type='submit']")).click();
    }
}
