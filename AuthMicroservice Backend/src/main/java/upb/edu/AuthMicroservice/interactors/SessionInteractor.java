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
        session.setId(UUID.randomUUID());
        session.setUserId(userId);
        session.setCreatedAt(LocalDateTime.now());
        session.setExpiresAt(LocalDateTime.now().plusHours(1));
        session.setIsValid(true);

        sessionRepository.save(session);
        return session.getId();
    }
}
