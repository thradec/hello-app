package cz.thradec.hello;

import static cz.thradec.hello.HelloRepository.orderById;
import static cz.thradec.hello.HelloRepository.orderByMessage;
import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.springframework.test.jdbc.JdbcTestUtils.countRowsInTableWhere;

import java.util.List;

import net.ttddyy.dsproxy.QueryCountHolder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HelloRepositoryTest {

    @Autowired
    private HelloRepository helloRepository;
    @Autowired
    private TestData testData;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Before
    public void init() {
        testData.init();
    }

    @Test
    public void shouldFindAllOrderById() {
        List<Hello> helloList = helloRepository.findAll(orderById());
        assertThat(helloList)
                .containsExactlyElementsOf(
                        testData.getHelloList()
                                .stream()
                                .sorted(comparing(Hello::getId))
                                .collect(toList()));
    }

    @Test
    public void shouldFindAllOrderByMessage() {
        List<Hello> helloList = helloRepository.findAll(orderByMessage());
        assertThat(helloList)
                .containsExactlyElementsOf(
                        testData.getHelloList()
                                .stream()
                                .sorted(comparing(Hello::getMessage))
                                .collect(toList()));
    }

    @Test
    public void shouldFindAllAndCache() {
        QueryCountHolder.clear();

        helloRepository.findAll(orderById());
        helloRepository.findAll(orderById());

        assertThat(QueryCountHolder.getGrandTotal().getSelect()).isEqualTo(1);
    }

    @Test
    public void shouldFindRandom() {
        Hello hello = helloRepository.findRandom();
        assertThat(hello).isIn(testData.getHelloList());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void shouldSaveAndDelete() {
        Hello hello = helloRepository.save(new Hello("test"));

        assertThat(hello.getId()).isNotNull();
        assertThat(countRowsInTableWhere(jdbcTemplate, "Hello", "message = 'test'")).isEqualTo(1);

        helloRepository.delete(hello.getId());

        assertThat(countRowsInTableWhere(jdbcTemplate, "Hello", "message = 'test'")).isEqualTo(0);
    }

    @Test
    @WithAnonymousUser
    public void shouldNotSaveIfNotAuthenticated() {
        assertThatThrownBy(() -> helloRepository.save(new Hello()))
                .isInstanceOf(AccessDeniedException.class);
    }

    @Test
    @WithMockUser
    public void shouldNotDeleteIfNotAdmin() {
        assertThatThrownBy(() -> helloRepository.delete(-1L))
                .isInstanceOf(AccessDeniedException.class);
    }

}