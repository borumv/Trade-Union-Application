package backend.persist.entity;

import backend.validator.personValidator.NotNullIfAnotherFieldHasValue;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.sql.Timestamp;
import java.util.Date;

@Data
@Entity
@Table(name = "doc_member")
@NotNullIfAnotherFieldHasValue
public class DocMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "num")
    @Size(min = 1, message = "{docmember.num.size.error}")
    private int membershipCard;

    @Column(name = "updated")
    @JsonIgnore
    private Timestamp updated;

    @Column(name = "created")
    @NotNull
    private Date entryDate;

    @Column(name = "completed")
    private Date completed;

    @Column(name = "finished")
    private Date leaveDate;

    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id", insertable = false, updatable = false)
    @JsonIgnore
    private PersonEntity person;

    @Size(min = 1, message = "{person.id.size.error}")
    @Column(name = "person_id")
    @JsonProperty("person_id")
    private int personId;


}
