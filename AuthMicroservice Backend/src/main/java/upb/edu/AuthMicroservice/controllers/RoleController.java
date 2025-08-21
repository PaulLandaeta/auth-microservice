package upb.edu.AuthMicroservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;
import org.springframework.stereotype.Component;

import upb.edu.AuthMicroservice.interactors.RoleInteractor;
import upb.edu.AuthMicroservice.models.Response;
import upb.edu.AuthMicroservice.models.Role;

import java.io.IOException;
import java.util.List;

@RestController
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

    public ServerResponse deleteRole(ServerRequest request) {
        try {
            Integer id = Integer.parseInt(request.pathVariable("id"));
            boolean deleted = roleInteractor.deleteRole(id);
            if (deleted) {
                return ServerResponse.noContent().build();
            } else {
                return ServerResponse.status(404).body(new Response("404", "Role not found"));
            }
        } catch (Exception e) {
            return ServerResponse.badRequest().body(new Response("400", "Invalid ID format"));
        }
    }

    public ServerResponse getAllRoles(ServerRequest request) {
        List<Role> roles = roleInteractor.getAllRoles();
        return ServerResponse.ok().body(roles);
    }

    public ServerResponse updateRole(ServerRequest request) throws Exception {
        try {
            Integer id = Integer.parseInt(request.pathVariable("id"));
            Role body = request.body(Role.class);

            if (body.getName() == null || body.getName().trim().isEmpty()) {
                return ServerResponse.badRequest().body(new Response("400", "Role name cannot be empty"));
            }

            try {
                Role updated = roleInteractor.updateRole(id, body);
                if (updated == null) {
                    return ServerResponse.status(404).body(new Response("404", "Role not found"));
                }
                return ServerResponse.ok().body(updated);
            } catch (IllegalArgumentException ex) {
                return ServerResponse.badRequest().body(new Response("400", ex.getMessage()));
            } catch (Exception ex) {
                return ServerResponse.badRequest().body(new Response("400", "Error updating role: " + ex.getMessage()));
            }

        } catch (NumberFormatException nfe) {
            return ServerResponse.badRequest().body(new Response("400", "Invalid ID format"));
        } catch (IOException ioe) {
            return ServerResponse.badRequest().body(new Response("400", "Invalid request body"));
        }
    }

}
