package upb.edu.AuthMicroservice.services;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
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

    public Permission updatePermission(Long id, String newName) {
        if (newName == null || newName.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del permiso no puede estar vacío");
        }

        Permission existing = permissionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Permiso con id " + id + " no encontrado"));

        permissionRepository.findByName(newName.trim())
                .filter(p -> p.getId() != id)
                .ifPresent(p -> {
                    throw new DataIntegrityViolationException("Ya existe un permiso con el nombre '" + newName + "'");
                });
        existing.setName(newName.trim());
        return permissionRepository.save(existing);
    }

}
