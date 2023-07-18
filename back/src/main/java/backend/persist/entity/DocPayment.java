package backend.persist.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Represents a document payment entity.
 *
 * @author Boris Vlasevsky
 */
@Data
@Entity
@Table(name = "doc_payment")
@SqlResultSetMapping(
        name = "DocPaymentMapping",
        entities = @EntityResult(entityClass = DocPayment.class)
)
@JsonIgnoreProperties({"person", "person.byteBuddyInterceptor"})
public class DocPayment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "updated")
    @JsonIgnore
    @UpdateTimestamp
    private Timestamp updated;

    @Column(name = "created")
    @NotNull
    @CreationTimestamp
    private Date startPay;

    @Column(name = "finished")
    private Date endPay;

    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id", insertable = false, updatable = false)
    private PersonEntity person;

    @Column(name = "person_id")
    @JsonProperty("person_id")
    private int personId;

    @ManyToOne
    @JoinColumn(name = "org_id", referencedColumnName = "id", insertable = false, updatable = false)
    @JsonIgnore
    private TradeUnion tradeUnion;

    @Column(name = "org_id")
    @JsonProperty("org_id")
    private int orgId;

    public DocPayment() {

    }

    @Builder
    public DocPayment(int id, Timestamp updated, Date startPay, Date endPay, PersonEntity person, int personId, TradeUnion tradeUnion, int orgId) {

        this.id = id;
        this.updated = updated;
        this.startPay = startPay;
        this.endPay = endPay;
        this.person = person;
        this.personId = personId;
        this.tradeUnion = tradeUnion;
        this.orgId = orgId;
    }
}
