package backend.security;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum Role {
    ADMIN(Stream.of(Permissions.PERSONS_READ, Permissions.PERSONS_WRITE, Permissions.PERSONS_DELETE, Permissions.TRADEUNION_READ,
                    Permissions.TRADEUNION_EDIT).collect(Collectors.toSet())),
    USER(Stream.of(Permissions.PERSONS_READ, Permissions.TRADEUNION_READ).collect(Collectors.toSet())),
    SUPERADMIN(Stream.of(Permissions.TRADEUNION_EDIT).collect(Collectors.toSet()));
    private final Set<Permissions> permissions;

    Role(Set<Permissions> permissions) {
        this.permissions = permissions;
    }

    public Set<Permissions> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getAuthorities() {
        return getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
    }
}
