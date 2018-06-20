package cz.thradec.hello.domain;

import cz.thradec.hello.AbstractIT;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class HelloValidatorIT extends AbstractIT {

    private final Validator validator;

    @Autowired
    HelloValidatorIT(Validator validator) {
        this.validator = validator;
    }

    @Test
    void shouldValidate() {
        Hello hello = new Hello("");
        Set<ConstraintViolation<Hello>> violations = validator.validate(hello);

        assertThat(violations)
                .hasSize(1)
                .extracting((v) -> v.getPropertyPath() + " " + v.getMessage())
                .contains("message size must be between 1 and 255");
    }

}
