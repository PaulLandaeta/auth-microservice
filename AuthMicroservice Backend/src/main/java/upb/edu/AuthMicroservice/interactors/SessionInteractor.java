package upb.edu.AuthMicroservice.interactors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;
import upb.edu.AuthMicroservice.models.Session;
import upb.edu.AuthMicroservice.repositories.SessionRepository;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class SessionInteractor {

    @Autowired
    private SessionRepository sessionRepository;

    public class SessionResult {
        public UUID sessionId;
        public String accessToken;
        public UUID refreshToken;

        public SessionResult(UUID sessionId, String accessToken, UUID refreshToken) {
            this.sessionId = sessionId;
            this.accessToken = accessToken;
            this.refreshToken = refreshToken;
        }
    }

    public SessionResult execute(int userId) {
        Session session = new Session();
        session.setId(UUID.randomUUID());
        session.setUserId(userId);
        session.setCreatedAt(LocalDateTime.now());
        session.setExpiresAt(LocalDateTime.now().plusHours(1));
        session.setIsValid(true);

        session.setRefreshToken(UUID.randomUUID());
        session.setRefreshTokenExpiresAt(LocalDateTime.now().plusDays(7));

        int attempts = 0;
        while (true) {
            try {
                sessionRepository.save(session);
                return new SessionResult(session.getId(), session.getId().toString(), session.getRefreshToken());
            } catch (DataIntegrityViolationException ex) {
                attempts++;
                if (attempts >= 3) {
                    throw new RuntimeException("Error al generar el refresh token", ex);
                }
                session.setRefreshToken(UUID.randomUUID());
            }
        }
    }
}
