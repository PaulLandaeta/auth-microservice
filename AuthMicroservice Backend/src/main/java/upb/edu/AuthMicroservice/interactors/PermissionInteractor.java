package upb.edu.AuthMicroservice.interactors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import upb.edu.AuthMicroservice.models.Permission;
import upb.edu.AuthMicroservice.services.PermissionService;

@Component
public class PermissionInteractor {

    private final PermissionService permissionService;

    @Autowired
    public PermissionInteractor(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    public Permission createPermission(Permission permission) {
        return permissionService.createPermission(permission);
    }

    public Permission updatePermission(Long id, String newName) {

        return permissionService.updatePermission(id, newName);
    }
}
