package upb.edu.AuthMicroservice.routes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;
import upb.edu.AuthMicroservice.controllers.PermissionController;

import static org.springframework.web.servlet.function.RouterFunctions.route;

@Configuration
public class PermissionRoutes {

    private final PermissionController permissionController;

    @Autowired
    public PermissionRoutes(PermissionController permissionController) {
        this.permissionController = permissionController;
    }

    @Bean
    public RouterFunction<ServerResponse> permissionRouter() {
        return route()
                .POST("/permissions", permissionController::createPermission)
                .GET("/api/permissions", permissionController::getAllPermissions)
                .build();
    }
}