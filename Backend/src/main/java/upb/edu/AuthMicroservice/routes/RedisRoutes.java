package upb.edu.AuthMicroservice.routes;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;
import static org.springframework.web.servlet.function.RouterFunctions.route;
import static org.springframework.web.servlet.function.RequestPredicates.POST;
import org.springframework.beans.factory.annotation.Autowired;

import upb.edu.AuthMicroservice.controllers.RedisController;

@Configuration
public class RedisRoutes {

    @Autowired
    private RedisController redisController;

    @Bean
    public RouterFunction<ServerResponse> redisRouter() {
        return route(POST("/redis-test"), redisController::saveKeyValueFunc);
    }
}
