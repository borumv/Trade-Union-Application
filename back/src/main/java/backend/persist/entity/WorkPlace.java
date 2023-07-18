package backend.persist.entity;

import backend.validator.personValidator.FirstSecondFieldInterface;
import backend.validator.personValidator.NotNullIfAnotherFieldHasValue;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Represents a workplace entity.
 *
 * @author Boris Vlasevsky
 */
@Data
@Entity
@Table(name = "doc_job")
@NotNullIfAnotherFieldHasValue
public class WorkPlace implements FirstSecondFieldInterface {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id", insertable = false, updatable = false)
    private PersonEntity person;
    @Column(name = "person_id")
    @JsonProperty("person_id")
    private int personId;

    @Column(name = "place")
    private String nameWokPlace;

    @Column(name = "post")
    private String profession;

    @Column(name = "created")
    private Date startWork;

    @Column(name = "finished")
    private Date endWork;

    @Column(name = "updated")
    @JsonIgnore
    private Timestamp updated;

    @Override
    public String getFirstField() {

        return profession;
    }

    @Override
    public String getSecondField() {

        return nameWokPlace;
    }

    @Override
    public String toString() {

        return "WorkPlace{" +
                "nameWokPlace='" + nameWokPlace + '\'' +
                ", profession='" + profession + '\'' +
                '}';
    }
}
