package upb.edu.AuthMicroservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import upb.edu.AuthMicroservice.models.Role;
import upb.edu.AuthMicroservice.repositories.RoleRepository;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public Role createRole(Role role) {
        return roleRepository.save(role);
    }

}
