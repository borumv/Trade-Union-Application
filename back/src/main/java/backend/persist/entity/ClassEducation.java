package backend.persist.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;

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

    @Builder
    public ClassEducation(int id, String name, Timestamp updated) {

        this.id = id;
        this.name = name;
        this.updated = updated;
    }

    public ClassEducation() {

    }
}
