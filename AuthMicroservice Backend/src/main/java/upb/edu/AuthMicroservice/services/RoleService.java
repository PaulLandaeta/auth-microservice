package upb.edu.AuthMicroservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import upb.edu.AuthMicroservice.models.Role;
import upb.edu.AuthMicroservice.repositories.RoleRepository;
import java.util.Date;
import java.util.Optional;
import java.util.List;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public Role createRole(Role role) {
        return roleRepository.save(role);
    }

    public boolean deleteRole(Integer id) {
        if (roleRepository.existsById(id)) {
            roleRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    public Role updateRole(Integer id, Role newRole) {
        if (newRole == null || newRole.getName() == null || newRole.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Role name cannot be empty");
        }

        Optional<Role> existingOpt = roleRepository.findById(id);
        if (!existingOpt.isPresent()) {
            return null; 
        }

        String newName = newRole.getName().trim();

        
        Optional<Role> byName = roleRepository.findByName(newName);
        if (byName.isPresent() && byName.get().getId() != id) {
        throw new IllegalArgumentException("Role name already exists");
        }
        Role existing = existingOpt.get();
        existing.setName(newName);
        existing.setUpdatedAt(new Date());
        return roleRepository.save(existing);
    }

}
