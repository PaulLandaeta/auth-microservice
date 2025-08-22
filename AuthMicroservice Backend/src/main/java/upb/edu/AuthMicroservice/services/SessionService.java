package upb.edu.AuthMicroservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import upb.edu.AuthMicroservice.interactors.SessionInteractor;

import java.util.Map;

@Service
public class SessionService {

    @Autowired
    private SessionInteractor interactor;

    public ResponseEntity<Object> generateSession(int userId) {
        try {
            var result = interactor.execute(userId);
            return ResponseEntity.status(201).body(Map.of(
                "code", 201,
                "msg", "Sesión creada exitosamente",
                "session_id", result.sessionId.toString(),
                "access_token", result.accessToken,
                "refresh_token", result.refreshToken.toString()
            ));
        } catch (Exception ex) {
            return ResponseEntity.status(500).body(Map.of(
                "code", 500,
                "msg", "Error al generar el refresh token"
            ));
        }
    }
}
