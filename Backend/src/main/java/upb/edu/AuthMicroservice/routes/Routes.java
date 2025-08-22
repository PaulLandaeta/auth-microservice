package upb.edu.AuthMicroservice.routes;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;
import org.springframework.web.servlet.function.RouterFunctions;

import upb.edu.AuthMicroservice.controllers.RoleController;
import upb.edu.AuthMicroservice.controllers.UserController;
import upb.edu.AuthMicroservice.controllers.SessionController;

import static org.springframework.web.servlet.function.RequestPredicates.*;
import static org.springframework.web.servlet.function.RouterFunctions.route;

@Configuration
public class Routes {

    private final UserController userController;
    private final SessionController sessionController;

    public Routes(UserController userController, SessionController sessionController) {
        this.userController = userController;
        this.sessionController = sessionController;
    }
    @Bean
    public RouterFunction<ServerResponse> routerFunction(RoleController roleController) {
        return route()
            .nest(path("/api"), builder -> builder
                .POST("/register-user", userController::registerUser)
                .POST("/login", userController::login)
                .PUT("/change-password", userController::changePassword)
                .POST("/generate-session", sessionController::generateSession)
                .add(RoleRoutes.roleRoutes(roleController)))
            .build();
    }
}
