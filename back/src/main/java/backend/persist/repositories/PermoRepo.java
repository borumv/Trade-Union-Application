package backend.persist.repositories;

import backend.persist.entity.Permission;
import backend.security.Role;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PermoRepo  extends CrudRepository<Permission, Integer> {
    List<Permission> findPermissionsByRole(Role role);
}
