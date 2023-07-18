package backend.persist.repositories;

import backend.persist.entity.Permission;
import backend.security.Role;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PermoRepo extends CrudRepository<Permission, Integer> {

    /**
     * Retrieves a list of Permission objects based on the specified Role.
     *
     * @param role the Role object to filter the permissions
     * @return a list of Permission objects
     */
    List<Permission> findPermissionsByRole(Role role);
}
