package upb.edu.AuthMicroservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import upb.edu.AuthMicroservice.interactors.PermissionInteractor;Add commentMore actions
import upb.edu.AuthMicroservice.models.PermissionEntity;

@Service
public class PermissionService {

    private final PermissionRepository permissionRepository;
    private static final List<String> VALID_ACTIONS = Arrays.asList("READ", "WRITE", "DELETE", "UPDATE");

    @Autowired
    public PermissionService(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }

    public Permission createPermission(PermissionRequest request) {
        if (!VALID_ACTIONS.contains(request.getAction())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Acción inválida");
        }

        if (permissionRepository.existsByName(request.getName())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "El permiso ya existe");
        }
        Permission permission = new Permission();
        permission.setName(request.getName());
        permission.setAction(request.getAction());

        return permissionRepository.save(permission);
    }
}