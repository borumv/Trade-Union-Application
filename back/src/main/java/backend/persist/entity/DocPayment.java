package backend.persist.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.util.Date;

@Data
@Entity
@Table(name = "doc_payment")
@SqlResultSetMapping(
        name = "DocPaymentMapping",
        entities=@EntityResult(entityClass=DocPayment.class)
)
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
    private TradeUnion tradeUnion;

    @Column(name = "org_id")
    @JsonProperty("org_id")
    private int orgId;

}
