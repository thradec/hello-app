package cz.thradec.hello.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Hello implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "hello")
    @SequenceGenerator(name = "hello", sequenceName = "hello_sequence")
    private Long id;

    @NotNull
    @Size(min = 1, max = 255)
    private String message;

    public Hello(String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o instanceof Hello) {
            return id != null && Objects.equals(id, ((Hello) o).getId());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return 31;
    }

}