package backend.security;

public enum Permissions {
    PERSONS_READ("persons:read"),
    PERSONS_WRITE("persons:write"),
    PERSONS_DELETE("persons:delete"),
    TRADEUNION_READ("tradeunion:read"),
    TRADEUNION_EDIT("tradeunion:edit");


    private final String permission;
    public String getPermission() {
        return permission;
    }
    Permissions(String permission) {
        this.permission = permission;
    }

}
