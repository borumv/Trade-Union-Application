package backend.security;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * The Role enum represents different roles in the system.
 * Each role is associated with a set of permissions.
 */
public enum Role {
    ADMIN(Stream.of(Permissions.PERSONS_READ, Permissions.PERSONS_WRITE, Permissions.PERSONS_DELETE, Permissions.TRADEUNION_READ,
            Permissions.TRADEUNION_EDIT).collect(Collectors.toSet())),
    USER(Stream.of(Permissions.PERSONS_READ, Permissions.TRADEUNION_READ).collect(Collectors.toSet())),
    SUPERADMIN(Stream.of(Permissions.TRADEUNION_EDIT).collect(Collectors.toSet()));
    private final Set<Permissions> permissions;

    /**
     * Constructs a new Role with the specified set of permissions.
     *
     * @param permissions the set of permissions associated with the role
     */
    Role(Set<Permissions> permissions) {
        this.permissions = permissions;
    }

    /**
     * Retrieves the set of permissions associated with the role.
     *
     * @return the set of permissions
     */
    public Set<Permissions> getPermissions() {
        return permissions;
    }


    /**
     * Retrieves the authorities (SimpleGrantedAuthority) associated with the role.
     * Each authority is created from a permission's string representation.
     *
     * @return the set of authorities
     */
    public Set<SimpleGrantedAuthority> getAuthorities() {
        return getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
    }
}
