package cz.thradec.hello;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import cz.thradec.hello.domain.Hello;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HelloValidatorTest {

    @Autowired
    private Validator validator;

    @Test
    public void shouldValidate() {
        Hello hello = new Hello(1L, "");
        Set<ConstraintViolation<Hello>> violations = validator.validate(hello);

        assertThat(violations)
                .hasSize(1)
                .extracting((v) -> v.getPropertyPath() + " " + v.getMessage())
                .contains("message size must be between 1 and 255");
    }

}
