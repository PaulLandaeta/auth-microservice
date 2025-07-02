package upb.edu.AuthMicroservice.routes;

import static org.springframework.web.servlet.function.RouterFunctions.route;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import upb.edu.AuthMicroservice.controllers.UserController;
import upb.edu.AuthMicroservice.models.Response;
import upb.edu.AuthMicroservice.models.User;
import upb.edu.AuthMicroservice.controllers.RedisController;
import java.util.Map;

@Configuration
public class Routes {

    @Autowired
    private RoleRoutes roleRoutes;
    @Autowired
    private RedisRoutes redisRoutes;
    private UserController userController;
    @Autowired
    private RedisController redisController;

    private RoleRoutes roleRoutes;

   
    @Autowired
    private RedisRoutes redisRoutes;

    @Bean
    public RouterFunction<ServerResponse> routerFunction() {
        return route()
                .path("api/roles", roleRoutes::roleRouter)
                .add(redisRoutes.redisRouter())
                .build();
    }
}