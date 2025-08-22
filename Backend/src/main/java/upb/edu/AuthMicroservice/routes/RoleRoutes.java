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

    public static RouterFunction<ServerResponse> roleRoutes(RoleController roleController) {
        return RouterFunctions.route(GET("/roles"), roleController::getAllRoles)
                      .andRoute(POST("/roles"), roleController::createRole);
    }
}
