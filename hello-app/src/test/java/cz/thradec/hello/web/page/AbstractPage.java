package cz.thradec.hello.web.page;

import lombok.RequiredArgsConstructor;
import org.openqa.selenium.WebDriver;
import org.springframework.boot.web.server.LocalServerPort;

@RequiredArgsConstructor
public abstract class AbstractPage {

    protected final WebDriver driver;

    @LocalServerPort
    private int port;

    protected final void open(String path) {
        driver.get("http://localhost:" + port + path);
    }

}
