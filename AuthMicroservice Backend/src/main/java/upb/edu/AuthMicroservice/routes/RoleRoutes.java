package upb.edu.AuthMicroservice.routes;

import static org.springframework.web.servlet.function.RouterFunctions.route;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;
import static org.springframework.web.servlet.function.RequestPredicates.GET;
import static org.springframework.web.servlet.function.RequestPredicates.POST;
import org.springframework.web.servlet.function.RouterFunctions;

import upb.edu.AuthMicroservice.controllers.RoleController;

@Component
public class RoleRoutes {

    @Bean
    public static RouterFunction<ServerResponse> roleRouter(RoleController roleController) {
        return route()
                .POST("/roles", roleController::createRole)
                .DELETE("/roles/{id}", roleController::deleteRole) 
                .GET("/roles", roleController::getAllRoles)
                .PUT("/api/roles/{id}", roleController::updateRole) 
                .build();
    }
}
