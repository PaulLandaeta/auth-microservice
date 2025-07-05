package upb.edu.AuthMicroservice.interactors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import upb.edu.AuthMicroservice.dtos.RoleDTO;
import upb.edu.AuthMicroservice.models.Role;
import upb.edu.AuthMicroservice.services.RoleService;

@Component
public class RoleInteractor {

    @Autowired
    private RoleService roleService;

    public Role createRole(Role role) {
        return roleService.createRole(role);
    }

    public RoleDTO updateRole(Integer id, RoleDTO dto) {
        Role roleToUpdate = new Role();
        roleToUpdate.setName(dto.getName());

        Role updated = roleService.updateRole(id, roleToUpdate);

        RoleDTO out = new RoleDTO();
        out.setId(Long.valueOf(updated.getId()));  
        out.setName(updated.getName());
        return out;
    }
}
