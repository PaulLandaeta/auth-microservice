package upb.edu.AuthMicroservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;
import org.springframework.stereotype.Component;


import upb.edu.AuthMicroservice.interactors.RoleInteractor;
import upb.edu.AuthMicroservice.models.Response;
import upb.edu.AuthMicroservice.models.Role;
import java.util.List;

@Component

public class RoleController {

    @Autowired
    private RoleInteractor roleInteractor;

    public ServerResponse createRole(ServerRequest request) {
        try {
            Role role = request.body(Role.class);
            roleInteractor.createRole(role);
            return ServerResponse.ok().body(new Response("201", "OK"));
        } catch (Exception e) {
            return ServerResponse.badRequest().body(new Response("400", "Bad Request"));
        }
    }

    public ServerResponse getAllRoles(ServerRequest request) {
        List<Role> roles = roleInteractor.getAllRoles();
        return ServerResponse.ok().body(roles);
    }

}
