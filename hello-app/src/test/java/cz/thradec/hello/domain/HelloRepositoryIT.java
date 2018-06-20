package cz.thradec.hello.domain;

import cz.thradec.hello.AbstractIT;
import cz.thradec.hello.TestData;
import lombok.RequiredArgsConstructor;
import net.ttddyy.dsproxy.QueryCountHolder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.List;

import static cz.thradec.hello.domain.HelloRepository.orderById;
import static cz.thradec.hello.domain.HelloRepository.orderByMessage;
import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.springframework.test.jdbc.JdbcTestUtils.countRowsInTableWhere;

class HelloRepositoryIT extends AbstractIT {

    private final HelloRepository helloRepository;
    private final TestData testData;
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    HelloRepositoryIT(HelloRepository helloRepository, TestData testData, JdbcTemplate jdbcTemplate) {
        this.helloRepository = helloRepository;
        this.testData = testData;
        this.jdbcTemplate = jdbcTemplate;
    }

    @BeforeEach
    void init() {
        testData.init();
    }

    @Test
    void shouldFindAllOrderById() {
        List<Hello> helloList = helloRepository.findAll(orderById());
        assertThat(helloList)
                .containsExactlyElementsOf(
                        testData.getHelloList()
                                .stream()
                                .sorted(comparing(Hello::getId))
                                .collect(toList()));
    }

    @Test
    void shouldFindAllOrderByMessage() {
        List<Hello> helloList = helloRepository.findAll(orderByMessage());
        assertThat(helloList)
                .containsExactlyElementsOf(
                        testData.getHelloList()
                                .stream()
                                .sorted(comparing(Hello::getMessage))
                                .collect(toList()));
    }

    @Test
    void shouldFindAllAndCache() {
        QueryCountHolder.clear();

        helloRepository.findAll(orderById());
        helloRepository.findAll(orderById());

        assertThat(QueryCountHolder.getGrandTotal().getSelect()).isEqualTo(1);
    }

    @Test
    void shouldFindRandom() {
        Hello hello = helloRepository.findRandom();
        assertThat(hello).isIn(testData.getHelloList());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void shouldSaveAndDelete() {
        Hello hello = helloRepository.save(new Hello("test"));

        assertThat(hello.getId()).isNotNull();
        assertThat(countRowsInTableWhere(jdbcTemplate, "Hello", "message = 'test'")).isEqualTo(1);

        helloRepository.deleteById(hello.getId());

        assertThat(countRowsInTableWhere(jdbcTemplate, "Hello", "message = 'test'")).isEqualTo(0);
    }

    @Test
    @WithAnonymousUser
    void shouldNotSaveIfNotAuthenticated() {
        assertThatThrownBy(() -> helloRepository.save(new Hello()))
                .isInstanceOf(AccessDeniedException.class);
    }

    @Test
    @WithMockUser
    void shouldNotDeleteIfNotAdmin() {
        assertThatThrownBy(() -> helloRepository.deleteById(-1L))
                .isInstanceOf(AccessDeniedException.class);
    }

}