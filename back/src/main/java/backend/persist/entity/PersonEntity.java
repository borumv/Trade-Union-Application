package backend.persist.entity;

import backend.validator.classeducationValidator.ClassEducation;
import backend.validator.personValidator.FirstSecondFieldInterface;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * Represents a permission entity.
 *
 * @author Boris Vlasevsky
 */
@Data
@Entity
@Table(name = "person_main")
@SqlResultSetMapping(
        name = "PersonMapping",
        entities = @EntityResult(entityClass = PersonEntity.class)
)
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

    public PersonEntity() {

    }

    @Builder
    public PersonEntity(int id, String firstName,
                        String lastName, String patronymic,
                        Date birth, String education,
                        String address, String phoneNumber,
                        String birthPlace, String livePlace,
                        String regPlace, int maritalPersonId,
                        String citizenShip, String nationality,
                        String comment, Timestamp update,
                        List<DocMember> docMembers,
                        List<WorkPlace> workPlaces,
                        List<DocPayment> docPayments) {

        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.birth = birth;
        this.education = education;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.birthPlace = birthPlace;
        this.livePlace = livePlace;
        this.regPlace = regPlace;
        this.maritalPersonId = maritalPersonId;
        this.citizenShip = citizenShip;
        this.nationality = nationality;
        this.comment = comment;
        this.update = update;
        this.docMembers = docMembers;
        this.workPlaces = workPlaces;
        this.docPayments = docPayments;
    }

}




