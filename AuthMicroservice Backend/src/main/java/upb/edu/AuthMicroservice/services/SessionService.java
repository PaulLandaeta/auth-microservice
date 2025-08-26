package upb.edu.AuthMicroservice.services;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import upb.edu.AuthMicroservice.interactors.SessionInteractor;
import upb.edu.AuthMicroservice.models.Session;
import upb.edu.AuthMicroservice.repositories.SessionRepository;
import upb.edu.AuthMicroservice.repositories.UserRepository;

@Service
public class SessionService {

    @Autowired
    private SessionInteractor interactor;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SessionRepository sessionRepository;

    public static class SessionCreationResult {
        private final UUID sessionId;
        private final UUID accessToken;

        public SessionCreationResult(UUID sessionId, UUID accessToken) {
            this.sessionId = sessionId;
            this.accessToken = accessToken;
        }
        public UUID getSessionId() { return sessionId; }
        public UUID getAccessToken() { return accessToken; }
    }
    public SessionCreationResult generateSession(int userId) {
        if (userId <= 0) {
            throw new IllegalArgumentException("El user_id proporcionado no es válido o no existe");
        }
        if (!userRepository.existsById(userId)) {
            throw new IllegalArgumentException("El user_id proporcionado no es válido o no existe");
        }
        UUID sessionId = interactor.execute(userId);

        Optional<Session> maybe = sessionRepository.findById(sessionId);
        if (maybe.isEmpty()) {
            throw new RuntimeException("La sesión fue creada pero no se pudo recuperar");
        }
        Session created = maybe.get();
        return new SessionCreationResult(created.getId(), created.getAccessToken());
    }
    public Optional<Session> getSessionById(UUID sessionId) {
        return interactor.findById(sessionId);
    }
}
