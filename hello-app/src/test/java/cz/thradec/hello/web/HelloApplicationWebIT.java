package cz.thradec.hello.web;

import cz.thradec.hello.web.page.HomePage;
import cz.thradec.hello.web.page.LoginPage;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class HelloApplicationWebIT extends AbstractWebIT {

    private HomePage homePage;
    private LoginPage loginPage;

    @Override
    protected void initPages() {
        homePage = initPage(HomePage.class);
        loginPage = initPage(LoginPage.class);
    }

    @Test
    void shouldDisplayHomePage() {
        homePage.open();

        assertThat(homePage.getTitle())
                .isEqualTo("hello-app");
        assertThat(homePage.getLinksTitle())
                .contains("Administration")
                .contains("Development zone")
                .contains("Login");
    }

    @Test
    void shouldDisplayHello() {
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
    void shouldLogin() {
        loginPage.login("admin", "123456");
        assertThat(loginPage.hasLogout()).isTrue();
    }

}
