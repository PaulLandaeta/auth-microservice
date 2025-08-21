

package upb.edu.AuthMicroservice.interactors;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import upb.edu.AuthMicroservice.models.Permission;
import upb.edu.AuthMicroservice.services.PermissionService;


@Component
public class PermissionInteractor {


    @Autowired
    private PermissionService permissionService;


    public Permission createPermission(Permission permission) {
        return permissionService.createPermission(permission);
    }

    public Permission updatePermission(String id, Permission permission) {
    return permissionService.updatePermission(id, permission);
    }

    public List<Permission> getAllPermissions() {
        return permissionService.getAllPermissions();
    }
    
    public boolean deletePermission(String id) {
        return permissionService.deletePermission(id);
    }
}
