package upb.edu.AuthMicroservice.interactors;


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
}
