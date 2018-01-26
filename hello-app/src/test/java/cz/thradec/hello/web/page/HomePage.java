package cz.thradec.hello.web.page;

import java.util.List;
import java.util.stream.Stream;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends AbstractPage {

    @FindBy(id = "actual-hello")
    private WebElement actualHello;
    @FindBy(id = "next-hello")
    private WebElement nextHello;
    @FindBy(tagName = "a")
    private List<WebElement> links;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        open("/");
    }

    public void nextHello() {
        nextHello.click();
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public String getActualHello() {
        return actualHello.getText();
    }

    public Stream<String> getLinksTitle() {
        return links.stream().map(e -> e.getAttribute("title"));
    }

}
