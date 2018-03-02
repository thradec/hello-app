package cz.thradec.hello.domain;

import cz.thradec.hello.AbstractTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class HelloValidatorTest extends AbstractTest {

    @Autowired
    private Validator validator;

    @Test
    public void shouldValidate() {
        Hello hello = new Hello("");
        Set<ConstraintViolation<Hello>> violations = validator.validate(hello);

        assertThat(violations)
                .hasSize(1)
                .extracting((v) -> v.getPropertyPath() + " " + v.getMessage())
                .contains("message size must be between 1 and 255");
    }

}
