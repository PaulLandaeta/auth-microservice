package upb.edu.AuthMicroservice.services;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import upb.edu.AuthMicroservice.models.Permission;
import upb.edu.AuthMicroservice.repositories.PermissionRepository;


@Service
public class PermissionService {


    private final PermissionRepository permissionRepository;
    private static final List<String> VALID_ACTIONS = Arrays.asList("READ", "WRITE", "DELETE", "UPDATE");


    @Autowired
    public PermissionService(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }

    public Permission createPermission(Permission permission) {
        return permissionRepository.save(permission);
    }
}
