package upb.edu.AuthMicroservice.routes;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import upb.edu.AuthMicroservice.controllers.RoleController;
import upb.edu.AuthMicroservice.controllers.UserController;
import upb.edu.AuthMicroservice.controllers.SessionController;

import static org.springframework.web.servlet.function.RouterFunctions.route;
import static org.springframework.web.servlet.function.RouterFunctions.nest;
import static org.springframework.web.servlet.function.RequestPredicates.path;

@Configuration
public class Routes {

    private final UserController userController;
    private final SessionController sessionController;

    public Routes(UserController userController, SessionController sessionController) {
        this.userController = userController;
        this.sessionController = sessionController;
    }

    @Bean
    public RouterFunction<ServerResponse> roleRoutes(RoleController roleController) {
        return route()
                .path("/api", builder -> builder.add(RoleRoutes.roleRouter(roleController)))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> userRoutes() {
        return nest(path("/api"),
                route()
                        .POST("/register-user", userController::registerUser)
                        .POST("/login", userController::login)
                        .PUT("/change-password", userController::changePassword)
                        .build()
        );
    }

    @Bean
    public RouterFunction<ServerResponse> sessionRoutes() {
        return nest(path("/api"),
                route()
                        .POST("/generate-session", sessionController::generateSession)
                        .build()
        );
    }
}