package upb.edu.AuthMicroservice.routes;

import static org.springframework.web.servlet.function.RouterFunctions.route;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import upb.edu.AuthMicroservice.controllers.RoleController;

@Component
public class RoleRoutes {

    @Autowired
    private RoleController roleController;

    @Bean
    public RouterFunction<ServerResponse> roleRouter() {
        return route()
                .POST("/", roleController::createRole)
                .build();
    }
}
