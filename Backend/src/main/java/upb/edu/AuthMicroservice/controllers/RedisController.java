package upb.edu.AuthMicroservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;
import upb.edu.AuthMicroservice.interactors.RedisInteractor;
import upb.edu.AuthMicroservice.models.Response;

import java.util.Map;

@Component
public class RedisController {

    @Autowired
    private RedisInteractor redisInteractor;

    public ServerResponse saveKeyValueFunc(ServerRequest request) {
        try {
            Map<String, String> payload = request.body(Map.class);
            String key = payload.get("key");
            String value = payload.get("value");

            redisInteractor.saveKeyValue(key, value);

            Response response = new Response("201", "Valor guardado correctamente");
            return ServerResponse.ok().body(response);
        } catch (Exception e) {
            Response errorResponse = new Response("500", "Error al procesar la solicitud");
            return ServerResponse.status(500).body(errorResponse);
        }
    }
}
