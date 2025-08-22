package upb.edu.AuthMicroservice.interactors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import upb.edu.AuthMicroservice.models.Session;
import upb.edu.AuthMicroservice.repositories.SessionRepository;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Component
public class RefreshTokenInteractor {

    @Autowired
    private SessionRepository sessionRepository;

    public UUID execute(UUID refreshToken, int minutesToAdd) {
        Optional<Session> opt = sessionRepository.findByRefreshToken(refreshToken);
        if (opt.isEmpty()) {
            return null;
        }
        Session s = opt.get();
        if (!s.isValid()) {
            throw new IllegalStateException("SESSION_INVALID");
        }
        if (s.getRefreshTokenExpiresAt() == null || s.getRefreshTokenExpiresAt().isBefore(LocalDateTime.now())) {
            return null;
        }
        UUID newAccess = UUID.randomUUID();
        s.setAccessToken(newAccess);
        s.setExpiresAt(LocalDateTime.now().plusMinutes(minutesToAdd));
        sessionRepository.save(s);
        return newAccess;
    }
}
