package cz.thradec.hello.domain;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.Repository;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

@CacheConfig(cacheNames = "hello")
public interface HelloRepository extends HelloRepositoryCustom, Repository<Hello, Long> {

    static Sort orderById() {
        return Sort.by("id");
    }

    static Sort orderByMessage() {
        return Sort.by("message");
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
    Hello deleteById(Long id);

}