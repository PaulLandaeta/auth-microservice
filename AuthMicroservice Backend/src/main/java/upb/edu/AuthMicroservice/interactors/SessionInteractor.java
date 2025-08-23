package upb.edu.AuthMicroservice.interactors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import upb.edu.AuthMicroservice.models.Session;
import upb.edu.AuthMicroservice.repositories.SessionRepository;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class SessionInteractor {

    @Autowired
    private SessionRepository sessionRepository;
    public UUID execute(int userId) {
        Session session = new Session();
        UUID sessionId = UUID.randomUUID();
        UUID accessToken = UUID.randomUUID();

        session.setId(sessionId);
        session.setAccessToken(accessToken);
        session.setUserId(userId);
        session.setCreatedAt(LocalDateTime.now());
        session.setExpiresAt(LocalDateTime.now().plusMinutes(15)); 
        session.setIsValid(true);

        sessionRepository.save(session);
        return sessionId; 
    }

    public java.util.Optional<Session> findById(UUID id) {
        return sessionRepository.findById(id);
    }
}
