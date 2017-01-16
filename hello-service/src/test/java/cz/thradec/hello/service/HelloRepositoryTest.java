package cz.thradec.hello.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import net.ttddyy.dsproxy.QueryCountHolder;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;

public class HelloRepositoryTest extends AbstractTest {

    @Autowired
    private CacheManager cacheManager;
    @Autowired
    private HelloRepository helloRepository;

    @Test
    public void shouldFindAll() {
        List<Hello> helloList = helloRepository.findAll();
        assertThat(helloList)
                .isNotEmpty()
                .contains(new Hello(1L, "hello"));
    }

    @Test
    public void shouldCacheFindAll() {
        QueryCountHolder.clear();

        cacheManager.getCache("hello").clear();
        helloRepository.findAll();
        helloRepository.findAll();

        assertThat(QueryCountHolder.getGrandTotal().getSelect()).isEqualTo(1);
    }

}