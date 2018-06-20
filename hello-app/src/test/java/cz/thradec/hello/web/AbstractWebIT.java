package cz.thradec.hello.web;

import cz.thradec.hello.AbstractIT;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;

import static org.apache.commons.lang3.SystemUtils.USER_NAME;
import static org.junit.jupiter.api.Assumptions.assumeFalse;

public abstract class AbstractWebIT extends AbstractIT {

    static {
        ChromeDriverManager.getInstance().setup();
    }

    private WebDriver driver;

    @Autowired
    private AutowireCapableBeanFactory beanFactory;

    @BeforeEach
    public final void init() {
        assumeFalse(USER_NAME.equalsIgnoreCase("travis"));
        initWebDriver();
        initPages();
    }

    @AfterEach
    public final void quit() {
        if (driver != null) {
            driver.quit();
        }
    }

    protected abstract void initPages();

    final <T> T initPage(Class<T> pageClass) {
        T page = PageFactory.initElements(driver, pageClass);
        beanFactory.autowireBeanProperties(page, AutowireCapableBeanFactory.AUTOWIRE_NO, false);
        beanFactory.initializeBean(page, pageClass.getName());
        return page;
    }

    private void initWebDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        driver = new ChromeDriver(options);
    }

}
