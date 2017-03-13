package cz.thradec.hello;

import java.util.List;

import cz.thradec.hello.internal.HelloRepositoryCustom;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.Repository;

@CacheConfig(cacheNames = "hello")
public interface HelloRepository extends HelloRepositoryCustom, Repository<Hello, Long> {

    interface Order {
        Sort BY_ID = new Sort("id");
        Sort BY_MESSAGE = new Sort("message");
    }

    @Cacheable
    List<Hello> findAll(Sort sort);

    Hello findById(Long id);

    @CacheEvict(allEntries = true)
    Hello save(Hello hello);

    @CacheEvict(allEntries = true)
    Hello delete(Long id);

}