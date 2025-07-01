package upb.edu.AuthMicroservice.routes;Add commentMore actions
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;More actions
import upb.edu.AuthMicroservice.controllers.PermissionController;
import upb.edu.AuthMicroservice.models.Response;
import upb.edu.AuthMicroservice.models.PermissionEntity;
import static org.springframework.web.servlet.function.RouterFunctions.route;

@Configuration
public class Routes {

    @Autowired
    private PermissionController permissionController;

    @Bean
    public RouterFunction<ServerResponse> PermissionRoutes() {
        return route()
                .POST("/permissions", request -> {
                    Permission permission = request.body(Permission.class);
                    PermissionController.(permission);
                    return ServerResponse.ok().body(new Response("201", "Permiso creado correctamente"));
                })
                .build();
    }
}