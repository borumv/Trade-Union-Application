package backend.services;

import backend.persist.entity.Permission;
import backend.persist.repositories.PermoRepo;
import backend.security.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionService {
    @Autowired
    PermoRepo permoRepo;


    public List<Permission> getPermission(Role role) {
        return permoRepo.findPermissionsByRole(role);
    }
}
