package backend.persist.entity;

import backend.security.Role;
import backend.security.Status;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @Column(name = "email")
    @Email
    @Size(max = 20)
    private String username;

    @Size(min = 2, max = 20, message = "{firstname.size.error}")
    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "password")
    private String password;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "role")
    private Role role;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @ManyToMany
    @JoinColumn(name = "role", referencedColumnName = "role", insertable = false, updatable = false)
    private List<Permission> permissionList;
}
