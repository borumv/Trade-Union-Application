package backend.security;

/**
 * The Permissions enum represents different types of permissions in a system.
 * Each permission is associated with a specific string value.
 */
public enum Permissions {
    PERSONS_READ("persons:read"),
    PERSONS_WRITE("persons:write"),
    PERSONS_DELETE("persons:delete"),
    TRADEUNION_READ("tradeunion:read"),
    TRADEUNION_EDIT("tradeunion:edit");
    private final String permission;
    /**
     * Constructs a new permission with the specified string representation.
     *
     * @param permission the string representation of the permission
     */
    Permissions(String permission) {
        this.permission = permission;
    }

    /**
     * Retrieves the string representation of the permission.
     *
     * @return the string representation of the permission
     */
    public String getPermission() {
        return permission;
    }

}
