package backend.persist.entity;

import backend.validator.personValidator.NotNullIfAnotherFieldHasValue;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.sql.Timestamp;

/**
 * Represents a trade union entity.
 *
 * @author Boris Vlasevsky
 */
@Data
@Entity
@Table(name = "class_org")
@NotNullIfAnotherFieldHasValue
public class TradeUnion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    @NotEmpty
    private String nameUnion;

    @Column(name = "city")
    private String city;

    @Column(name = "address")
    private String address;

    @Column(name = "updated")
    private Timestamp updated;

    @JsonIgnore
    public String getFirstField() {

        return address;
    }

    @JsonIgnore
    public String getSecondField() {

        return city;
    }
}
