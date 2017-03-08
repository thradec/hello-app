package cz.thradec.hello.service;

import java.util.List;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.Repository;

@CacheConfig(cacheNames = "hello")
public interface HelloRepository extends Repository<Hello, Long> {

    @Cacheable
    List<Hello> findAll();

    Hello findById(Long id);

    @CacheEvict(allEntries = true)
    Hello save(Hello hello);

    @CacheEvict(allEntries = true)
    Hello delete(Long id);

}