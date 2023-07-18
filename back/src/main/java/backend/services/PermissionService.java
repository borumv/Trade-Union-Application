package backend.services;

import backend.persist.entity.Permission;
import backend.persist.repositories.PermoRepo;
import backend.security.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for Permission-related operations
 *
 * @author Boris Vlasevsky
 */
@Service
public class PermissionService {

    @Autowired
    PermoRepo permoRepo;

    /**
     * Retrieves a list of Permission objects associated with the specified role.
     *
     * @param role the role to retrieve permissions for
     * @return a list of Permission objects
     */
    public List<Permission> getPermission(Role role) {

        return permoRepo.findPermissionsByRole(role);
    }
}