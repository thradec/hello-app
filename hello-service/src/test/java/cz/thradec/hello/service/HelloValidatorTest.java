package cz.thradec.hello.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class HelloValidatorTest extends AbstractTest {

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
