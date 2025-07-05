package upb.edu.AuthMicroservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import upb.edu.AuthMicroservice.models.Role;
import upb.edu.AuthMicroservice.repositories.RoleRepository;

import jakarta.persistence.EntityNotFoundException; 
import java.util.Optional; 
import java.util.Objects;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public Role createRole(Role role) {
        return roleRepository.save(role);
    }

    public Role updateRole(Integer id, Role roleData) {

        Role existing = roleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Rol no encontrado"));
        Optional<Role> duplicate = roleRepository.findByName(roleData.getName());
        if (duplicate.isPresent() && !Objects.equals(duplicate.get().getId(), id)) {
            throw new IllegalArgumentException("Nombre de rol duplicado");
        }

        existing.setName(roleData.getName());
        return roleRepository.save(existing);
    }

}
