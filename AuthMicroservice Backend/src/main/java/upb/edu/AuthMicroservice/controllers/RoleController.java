package upb.edu.AuthMicroservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;
import upb.edu.AuthMicroservice.dtos.RoleDTO;
import upb.edu.AuthMicroservice.interactors.RoleInteractor;
import upb.edu.AuthMicroservice.models.Response;
import upb.edu.AuthMicroservice.models.Role;

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

    public ServerResponse updateRole(ServerRequest request) {
        try {
            Integer id = Integer.valueOf(request.pathVariable("id"));
            RoleDTO dto = request.body(RoleDTO.class);
            RoleDTO updated = roleInteractor.updateRole(id, dto);
            return ServerResponse.ok().body(updated);
        } catch (jakarta.persistence.EntityNotFoundException e) {
            return ServerResponse.status(404).body(new Response("404", "Rol no encontrado"));
        } catch (IllegalArgumentException e) {
            return ServerResponse.status(400).body(new Response("400", e.getMessage()));
        } catch (Exception e) {
            return ServerResponse.status(500).body(new Response("500", "Error interno"));
        }
    }
}
