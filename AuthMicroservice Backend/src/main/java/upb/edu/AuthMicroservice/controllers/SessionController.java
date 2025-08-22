package upb.edu.AuthMicroservice.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @PostMapping("/generate-session")
    public ResponseEntity<Object> generateSession(@RequestParam int userId) {
        return sessionService.generateSession(userId);
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
        ResponseEntity<Object> responseEntity = sessionService.generateSession(dto.getUser_id());
        int status = responseEntity.getStatusCodeValue();
        Object body = responseEntity.getBody();

        return ServerResponse
                .status(status)
                .body(body);
    }
}