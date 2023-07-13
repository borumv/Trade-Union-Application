package backend.services;
import backend.persist.entity.Permission;
import backend.persist.repositories.PermoRepo;
import backend.security.Permissions;
import backend.security.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith({MockitoExtension.class})
class PermissionServiceTest {

    @Mock
    private PermoRepo permoRepo;

    @InjectMocks
    private PermissionService permissionService;


    @Test
    public void testGetPermission() {
        Role role = Role.USER;
        Permission permission1 = new Permission();
        permission1.setResource("persons");
        permission1.setAction("read");

        Permission permission2 = new Permission();
        permission2.setResource("tradeunion");
        permission2.setAction("read");

        List<Permission> expectedPermissions = Arrays.asList(permission1, permission2);

        // Mock the repository method to return the expected permissions
        when(permoRepo.findPermissionsByRole(role)).thenReturn(expectedPermissions);

        List<Permission> actualPermissions = permissionService.getPermission(role);

        assertEquals(expectedPermissions, actualPermissions);
    }
}