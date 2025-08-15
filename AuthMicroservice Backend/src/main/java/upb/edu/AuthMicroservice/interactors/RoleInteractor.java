package upb.edu.AuthMicroservice.interactors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import upb.edu.AuthMicroservice.models.Role;
import upb.edu.AuthMicroservice.services.RoleService;

@Component
public class RoleInteractor {
    @Autowired
    private RoleService roleService;

    public Role createRole(Role role) {
        return roleService.createRole(role);
    }

}
