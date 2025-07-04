package upb.edu.AuthMicroservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;
import static org.springframework.web.servlet.function.ServerResponse.*;

import upb.edu.AuthMicroservice.interactors.PermissionInteractor;
import upb.edu.AuthMicroservice.models.Permission;
import upb.edu.AuthMicroservice.models.Response;

@RestController
@RequestMapping("/api/permissions")
public class PermissionController {

    private final PermissionInteractor permissionInteractor;

    @Autowired
    public PermissionController(PermissionInteractor permissionInteractor) {
        this.permissionInteractor = permissionInteractor;
    }

    
    public ServerResponse createPermission(ServerRequest request) {
        try {
            Permission permission = request.body(Permission.class);
            permissionInteractor.createPermission(permission);
            return ok().body(new Response("201", "Permiso creado correctamente"));
        } catch (IllegalArgumentException | DataIntegrityViolationException e) {
            return badRequest().body(new Response("400", e.getMessage()));
        } catch (Exception e) {
            return status(HttpStatus.INTERNAL_SERVER_ERROR)
                   .body(new Response("500", "Error interno: " + e.getMessage()));
        }
    }

    
    public ServerResponse updatePermission(ServerRequest request) {
        try {
            Long id = Long.valueOf(request.pathVariable("id"));
            Permission dto = request.body(Permission.class);
            permissionInteractor.updatePermission(id, dto.getName());
            return ok().body(new Response("200", "Permiso actualizado"));
        } catch (IllegalArgumentException | DataIntegrityViolationException e) {
            String msg = e.getMessage();
            if (msg.contains("not found")) {
                return status(HttpStatus.NOT_FOUND).body(new Response("404", msg));
            }
            return badRequest().body(new Response("400", msg));
        } catch (Exception e) {
            return status(HttpStatus.INTERNAL_SERVER_ERROR)
                   .body(new Response("500", "Error interno: " + e.getMessage()));
        }
    }

    
    @PutMapping("/{id}")
    public ResponseEntity<Response> annotatedUpdate(
            @PathVariable Long id,
            @RequestBody Permission dto
    ) {
        try {
            permissionInteractor.updatePermission(id, dto.getName());
            return ResponseEntity.ok(new Response("200", "Permiso actualizado"));
        } catch (IllegalArgumentException | DataIntegrityViolationException e) {
            String msg = e.getMessage();
            if (msg.contains("not found")) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                     .body(new Response("404", msg));
            }
            return ResponseEntity.badRequest().body(new Response("400", msg));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body(new Response("500", "Error interno: " + e.getMessage()));
        }
    }
}
