package upb.edu.AuthMicroservice.services;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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

    public Permission updatePermission(String id, Permission permission) {
        
        if (!permissionRepository.existsById(id)) {
            return null; 
        }

        if (permission.getName() == null || permission.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del permiso no puede estar vacío");
        }

        Optional<Permission> byNameOpt = permissionRepository.findByName(permission.getName());
        if (byNameOpt.isPresent()) {
            Permission found = byNameOpt.get();
            String foundId = String.valueOf(found.getId());
            if (!foundId.equals(id)) {
                throw new IllegalArgumentException("El nombre del permiso ya existe");
            }
        }

        Optional<Permission> existingOpt = permissionRepository.findById(id);
        if (existingOpt.isEmpty()) return null; 
        Permission existing = existingOpt.get();

        existing.setName(permission.getName());
        existing.setAction(permission.getAction());
        existing.setUpdatedAt(LocalDateTime.now());

        return permissionRepository.save(existing);
    }

    public Permission createPermission(Permission permission) {
        return permissionRepository.save(permission);
    }

        public boolean deletePermission(String id) {
        if (permissionRepository.existsById(id)) {
            permissionRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Permission> getAllPermissions() {
        return permissionRepository.findAll();
    }
}
