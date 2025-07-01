package upb.edu.AuthMicroservice.interactors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import upb.edu.AuthMicroservice.models.PermissionEntity;
import upb.edu.AuthMicroservice.services.PermissionService;

@Component
public class PermissionInteractor {

    @Autowired
    private PermissionService permissionService;

    public PermissionEntity createPermission(PermissionEntity permission) {
        return permissionService.createPermission(permission);
    }
}