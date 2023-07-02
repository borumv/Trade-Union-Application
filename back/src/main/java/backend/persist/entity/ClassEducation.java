package backend.persist.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

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
