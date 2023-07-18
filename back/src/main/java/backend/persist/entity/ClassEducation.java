package backend.persist.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Represents a class education entity.
 *
 * @author Boris Vlasevsky
 */
@Data
@Entity
@Table(name = "class_education")
public class ClassEducation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Size(min = 1, max = 10, message = "{classeducation.id.size.error}")
    private int id;

    private String name;
    private java.sql.Timestamp updated;

    /**
     * Constructs a new ClassEducation object.
     *
     * @param id      the ID of the class education
     * @param name    the name of the class education
     * @param updated the timestamp of when the class education was last updated
     */
    @Builder
    public ClassEducation(int id, String name, Timestamp updated) {

        this.id = id;
        this.name = name;
        this.updated = updated;
    }

    /**
     * Constructs a new empty ClassEducation object.
     */
    public ClassEducation() {

    }
}
