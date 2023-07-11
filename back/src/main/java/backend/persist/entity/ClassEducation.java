package backend.persist.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;
import java.io.Serializable;
@Data
@Entity
@Table(name = "class_education")

public class ClassEducation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Size(min = 1, max = 10, message ="{classeducation.id.size.error}" )
    private int id;
    private String name;
    private java.sql.Timestamp updated;
}
