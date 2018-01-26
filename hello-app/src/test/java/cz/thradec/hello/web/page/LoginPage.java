package cz.thradec.hello.web.page;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.FluentWait;

public class LoginPage extends AbstractPage {

    @FindBy(id = "username")
    private WebElement username;
    @FindBy(id = "password")
    private WebElement password;
    @FindBy(id = "login-btn")
    private WebElement submit;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void login(String user, String pswd) {
        open("/#/login");
        username.sendKeys(user);
        password.sendKeys(pswd);
        submit.click();
    }

    public boolean hasLogout() {
        WebElement logout = new FluentWait<>(driver)
                .ignoring(Exception.class)
                .until(visibilityOfElementLocated(By.id("nav-logout")));
        return logout != null;
    }

}
