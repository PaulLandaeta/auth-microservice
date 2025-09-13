package upb.edu.AuthMicroservice.routes;

import static org.springframework.web.servlet.function.RouterFunctions.route;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import upb.edu.AuthMicroservice.controllers.UserController;

@Component
public class UserRoutes {

    @Bean
    public static RouterFunction<ServerResponse> userRouter(UserController userController) {
        return route()
                .POST("/register-user", userController::registerUser)
                .POST("/login",        userController::login)
                .POST("/auth/change-password", userController::changePassword)
                .POST("/email-validation", userController::validateEmail)
                .build();
    }
}
