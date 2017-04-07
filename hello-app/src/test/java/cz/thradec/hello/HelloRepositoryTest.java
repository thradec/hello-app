package cz.thradec.hello;

import static cz.thradec.hello.HelloRepository.orderById;
import static cz.thradec.hello.HelloRepository.orderByMessage;
import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import net.ttddyy.dsproxy.QueryCountHolder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HelloRepositoryTest {

    @Autowired
    private HelloRepository helloRepository;
    @Autowired
    private TestData testData;

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

}