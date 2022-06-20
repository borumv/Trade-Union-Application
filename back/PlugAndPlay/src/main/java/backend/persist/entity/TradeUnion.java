package backend.persist.entity;

import backend.validator.personValidator.FirstSecondFieldInterface;
import backend.validator.personValidator.NotNullIfAnotherFieldHasValue;
import backend.validator.tradeunionvalidate.TradeUnionValidator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.sql.Timestamp;
import java.util.List;

@Data
@Entity
@Table(name = "class_org")
@NotNullIfAnotherFieldHasValue
public class TradeUnion{

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
