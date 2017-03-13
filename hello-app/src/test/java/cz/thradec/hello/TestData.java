package cz.thradec.hello;

import static java.util.Arrays.asList;
import static java.util.Collections.unmodifiableList;
import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

import java.util.List;

import javax.persistence.EntityManager;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Scope(SCOPE_PROTOTYPE)
@RequiredArgsConstructor
@Getter
public class TestData {

    private final Hello hello = new Hello("hello");
    private final Hello ahoj = new Hello("ahoj");
    private final Hello tschus = new Hello("tschus");
    private final Hello salut = new Hello("salut");
    private final Hello ciao = new Hello("ciao");

    private final List<Hello> helloList = unmodifiableList(asList(
            hello,
            ahoj,
            tschus,
            salut,
            ciao));

    private final EntityManager em;
    private final CacheManager cacheManager;

    @Transactional
    public void init() {
        clearCache();
        clearDatabase();
        initDatabase();
    }

    private void clearCache() {
        cacheManager.getCache("hello").clear();
    }

    private void clearDatabase() {
        em.createQuery("delete from Hello").executeUpdate();
    }

    private void initDatabase() {
        helloList.forEach(em::persist);
        em.flush();
    }

}