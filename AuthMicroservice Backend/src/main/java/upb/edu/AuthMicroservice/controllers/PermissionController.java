package upb.edu.AuthMicroservice.controllers;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import upb.edu.AuthMicroservice.models.User;
import upb.edu.AuthMicroservice.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PermissionController {

    @Autowired
    private PermissionInteractor permissionInteractor;

    public ServerResponse createPermission(ServerRequest request) {
        try {
            PermissionEntity permission = request.body(PermissionEntity.class);
            permissionInteractor.createPermission(permission);
            return ServerResponse.ok().body(new BaseResponse("201", "Permiso creado correctamente"));
        } catch (Exception e) {
            return ServerResponse.badRequest().body(new BaseResponse("400", "Error: " + e.getMessage()));
        }
    }
}