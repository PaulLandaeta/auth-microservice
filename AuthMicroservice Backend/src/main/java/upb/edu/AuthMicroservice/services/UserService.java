package upb.edu.AuthMicroservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import upb.edu.AuthMicroservice.interactors.SessionInteractor;


import upb.edu.AuthMicroservice.interactors.UserInteractor;
import upb.edu.AuthMicroservice.models.User;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;



@Service
public class UserService {
    private final UserInteractor userInteractor;
    private final SessionInteractor sessionInteractor;

    @Autowired
    public UserService(UserInteractor userInteractor, SessionInteractor sessionInteractor) {
        this.userInteractor = userInteractor;
        this.sessionInteractor = sessionInteractor;
    }

    public User createUser(User user) {
        return userInteractor.createUser(user);
    }

    public ResponseEntity<Object> login(String email, String password) {
        Optional<User> userOpt = userInteractor.findByEmail(email);

        if (userOpt.isEmpty() || !userOpt.get().getPassword().equals(password)) {
            return ResponseEntity
                    .status(401)
                    .body(Map.of("code", 401, "msg", "Unauthorized"));
        }

        int userId = userOpt.get().getId();
        try {
            var sessionResult = sessionInteractor.execute(userId);
            return ResponseEntity.status(201).body(Map.of(
                "code", 201,
                "msg", "Sesión creada exitosamente",
                "session_id", sessionResult.sessionId.toString(),
                "access_token", sessionResult.accessToken,
                "refresh_token", sessionResult.refreshToken.toString()
            ));
        } catch (Exception ex) {
            return ResponseEntity.status(500).body(Map.of(
                "code", 500,
                "msg", "Error al generar el refresh token"
            ));
        }
    }

    public ResponseEntity<Object> changePassword(String email, String oldPassword, String newPassword) {
        boolean success = userInteractor.changePassword(email, oldPassword, newPassword);

        if (success) {
            return ResponseEntity.ok(Map.of("code", 200, "msg", "Ok"));
        } else {
            return ResponseEntity
                    .status(401)
                    .body(Map.of("code", 401, "msg", "Datos incorrectos"));
        }
    }
}
