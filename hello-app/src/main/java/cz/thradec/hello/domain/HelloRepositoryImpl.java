package cz.thradec.hello.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
class HelloRepositoryImpl implements HelloRepositoryCustom {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public Hello findRandom() {
        return jdbcTemplate.queryForObject("select * from hello order by random() limit 1", (rs, rowNum) -> {
            Hello hello = new Hello();
            hello.setId(rs.getLong("id"));
            hello.setMessage(rs.getString("message"));
            return hello;
        });
    }

}