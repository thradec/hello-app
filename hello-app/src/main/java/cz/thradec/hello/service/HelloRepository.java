package cz.thradec.hello.service;

import java.util.List;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.Repository;
import org.springframework.security.access.prepost.PreAuthorize;

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

    @PreAuthorize("isAuthenticated()")
    @CacheEvict(allEntries = true)
    Hello save(Hello hello);

    @PreAuthorize("hasRole('ADMIN')")
    @CacheEvict(allEntries = true)
    Hello delete(Long id);

}