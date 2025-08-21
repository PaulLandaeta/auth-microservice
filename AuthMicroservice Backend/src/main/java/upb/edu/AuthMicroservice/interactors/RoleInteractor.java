package upb.edu.AuthMicroservice.interactors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import upb.edu.AuthMicroservice.models.Role;
import upb.edu.AuthMicroservice.services.RoleService;
import java.util.List;

@Component
public class RoleInteractor {
    @Autowired
    private RoleService roleService;

    public Role createRole(Role role) {
        return roleService.createRole(role);
    }
  
    public boolean deleteRole(Integer id) {
        return roleService.deleteRole(id);
    }

     public List<Role> getAllRoles() {
        return roleService.getAllRoles();
    }

    public Role updateRole(Integer id, Role role) {
        return roleService.updateRole(id, role);
    }
    
}
