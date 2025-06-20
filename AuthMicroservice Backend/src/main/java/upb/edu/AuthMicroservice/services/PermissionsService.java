import org.springframework.stereotype.Service;

@Service
public class PermissionService {

    private final PermissionRepository permissionRepository;

    public PermissionService(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }

    public void createPermission(String name, String action) {
        Permission permission = new Permission();
        permission.setName(name);
        permission.setAction(action);
        permissionRepository.save(permission);
    }
}

