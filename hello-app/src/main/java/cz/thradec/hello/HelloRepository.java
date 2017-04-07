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

    static Sort orderById() {
        return new Sort("id");
    }

    static Sort orderByMessage() {
        return new Sort("message");
    }

    @Cacheable
    List<Hello> findAll(Sort sort);

    Hello findById(Long id);

    @Override
    Hello findRandom();

    @CacheEvict(allEntries = true)
    Hello save(Hello hello);

    @CacheEvict(allEntries = true)
    Hello delete(Long id);

}