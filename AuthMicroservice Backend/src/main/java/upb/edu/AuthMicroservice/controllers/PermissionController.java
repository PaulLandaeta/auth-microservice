package upb.edu.AuthMicroservice.controllers;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;

import upb.edu.AuthMicroservice.interactors.PermissionInteractor;
import upb.edu.AuthMicroservice.models.Permission;
import upb.edu.AuthMicroservice.models.Response;


@RestController
public class PermissionController {


    @Autowired
    private PermissionInteractor permissionInteractor;


    public ServerResponse createPermission(ServerRequest request) {
        try {
            Permission permission = request.body(Permission.class);
            permissionInteractor.createPermission(permission);
            return ServerResponse.ok().body(new Response("201", "Permiso creado correctamente"));
        } catch (Exception e) {
            return ServerResponse.badRequest().body(new Response("400", "Error: " + e.getMessage()));
        }
    }

     public ServerResponse deletePermission(ServerRequest request) {
        try {
            String id = request.pathVariable("id");
            if (permissionInteractor.deletePermission(id)) {
                return ServerResponse.noContent().build();
            } else {
                return ServerResponse.notFound().build();
            }
        } catch (Exception e) {
            return ServerResponse.status(500).body(
                Map.of("code", 500, "msg", "Error interno: " + e.getMessage())
            );
        }
    }

}
