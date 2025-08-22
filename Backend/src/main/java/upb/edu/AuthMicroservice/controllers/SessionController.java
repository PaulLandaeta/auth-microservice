package upb.edu.AuthMicroservice.controllers;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;
import upb.edu.AuthMicroservice.dtos.GenerateSessionRequest;
import upb.edu.AuthMicroservice.services.SessionService;

import java.util.Map;

@Component
public class SessionController {

    private final SessionService sessionService;

    public SessionController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    public ServerResponse generateSession(ServerRequest request) {
        GenerateSessionRequest dto;
        try {
            dto = request.body(GenerateSessionRequest.class);
        } catch (Exception e) {
            return ServerResponse
                    .badRequest()
                    .body(Map.of(
                            "code", 400,
                            "msg",  "JSON inválido: " + e.getMessage()
                    ));
        }
        String sessionId = sessionService.generateSession(dto.getUser_id());

        Map<String, Object> body = Map.of(
                "code",       201,
                "msg",        "Sesión creada exitosamente",
                "session_id", sessionId
        );
        return ServerResponse
                .status(201)
                .body(body);
    }
}