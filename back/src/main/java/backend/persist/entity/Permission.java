package backend.persist.entity;

import backend.security.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

/**
 * Represents a permission entity.
 *
 * @author Boris Vlasevsky
 */
@Data
@Entity
@Table(name = "permissions")
public class Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private int id;

    @Column(name = "role")
    @Enumerated(value = EnumType.STRING)
    @JsonIgnore
    private Role role;

    @Column(name = "resource")
    private String resource;
    @Column(name = "action")
    private String action;
    @Column(name = "permit")
    private boolean permit;

    @ManyToMany
    @JoinColumn(name = "role", referencedColumnName = "role", insertable = false, updatable = false)
    @JsonIgnore
    private List<User> userList;

}
