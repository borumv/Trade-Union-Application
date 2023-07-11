package backend.persist.entity;

import backend.validator.classeducationValidator.ClassEducation;
import backend.validator.personValidator.FirstSecondFieldInterface;
import backend.validator.personValidator.NotNullIfAnotherFieldHasValue;
import backend.validator.dateValidator.CustomDate;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "person_main")
@SqlResultSetMapping(
        name="PersonMapping",
        entities=@EntityResult(entityClass=PersonEntity.class))
//@NotNullIfAnotherFieldHasValue
//@Validated
public class PersonEntity implements FirstSecondFieldInterface {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "fn")
    @Size(min = 2, max = 20, message = "{person.firstname.size.error}")
    @NotEmpty
    private String firstName;

    @Column(name = "ln")
    @Size(min = 2, max = 20, message = "{person.lastname.size.error}")
    @NotEmpty
    private String lastName;

    @Size(min = 2, max = 20, message = "{person.patronymic.size.error}")
    @Column(name = "mn")
    private String patronymic;

    @Column(name = "birth")
    @NotNull
    @Temporal(TemporalType.DATE)
    private Date birth;

    @Column(name = "education")
    @ClassEducation
    @NotEmpty(message = "Please provide a education")
    private String education;

    @Column(name = "address")
    private String address;

    @Column(name = "phone")
    private String phoneNumber;

    @Column(name = "birth_place")
    private String birthPlace;

    @Column(name = "live_place")
    private String livePlace;

    @Column(name = "reg_place")
    private String regPlace;

    @Column(name = "marital_id")

    private int maritalPersonId;

    @Column(name = "citizenship")
    private String citizenShip;

    @Column(name = "nationality")
    private String nationality;

    @Column(name = "comment")
    private String comment;

    @Column(name = "updated")
    @JsonIgnore
    private Timestamp update;

    @OneToMany
    @JoinColumn(name = "person_id", referencedColumnName = "id", insertable = false, updatable = false)
    @JsonIgnore
    private List<DocMember> docMembers;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id", referencedColumnName = "id", insertable = false, updatable = false)
    @JsonIgnore
    private List<WorkPlace> workPlaces;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "person_id", referencedColumnName = "id", insertable = false, updatable = false)
    @JsonIgnore
    private List<DocPayment> docPayments;

    @Override
    @JsonIgnore
    public String getFirstField() {
        return livePlace;
    }

    @Override
    @JsonIgnore
    public String getSecondField() {
        return regPlace;
    }

    @Override
    public String toString() {
        return "PersonEntity{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", education='" + education + '\'' +
                '}';
    }
}




