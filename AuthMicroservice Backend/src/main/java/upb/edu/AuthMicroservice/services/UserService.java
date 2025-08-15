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
        UUID sessionId = sessionInteractor.execute(userId);

        Map<String, Object> data = new HashMap<>();
        data.put("session", sessionId.toString());

        return ResponseEntity.ok(Map.of(
                "code", 200,
                "msg",  "Ok",
                "data", data
        ));
    }
}
