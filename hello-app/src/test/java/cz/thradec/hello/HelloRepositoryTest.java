package cz.thradec.hello;

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
    public void shouldFindAll() {
        List<Hello> helloList = helloRepository.findAll();
        assertThat(helloList).containsAll(testData.getHelloList());
    }

    @Test
    public void shouldCacheFindAll() {
        QueryCountHolder.clear();

        helloRepository.findAll();
        helloRepository.findAll();

        assertThat(QueryCountHolder.getGrandTotal().getSelect()).isEqualTo(1);
    }

}