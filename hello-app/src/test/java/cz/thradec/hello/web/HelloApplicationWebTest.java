package cz.thradec.hello.web;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashSet;
import java.util.Set;

import cz.thradec.hello.web.page.HomePage;
import cz.thradec.hello.web.page.LoginPage;
import org.junit.Test;

public class HelloApplicationWebTest extends AbstractWebTest {

    private HomePage homePage;
    private LoginPage loginPage;

    @Override
    protected void initPages() {
        homePage = initPage(HomePage.class);
        loginPage = initPage(LoginPage.class);
    }

    @Test
    public void shouldDisplayHomePage() {
        homePage.open();

        assertThat(homePage.getTitle())
                .isEqualTo("hello-app");
        assertThat(homePage.getLinksTitle())
                .contains("Administration")
                .contains("Development zone")
                .contains("Login");
    }

    @Test
    public void shouldDisplayHello() {
        Set<String> hellos = new HashSet<>();

        homePage.open();
        hellos.add(homePage.getActualHello());
        homePage.nextHello();
        hellos.add(homePage.getActualHello());
        homePage.nextHello();
        hellos.add(homePage.getActualHello());

        assertThat(hellos).size().isGreaterThan(1);
    }

    @Test
    public void shouldLogin() {
        loginPage.login("admin", "123456");
        assertThat(loginPage.hasLogout()).isTrue();
    }

}
