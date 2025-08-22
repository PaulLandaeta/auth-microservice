package upb.edu.AuthMicroservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import upb.edu.AuthMicroservice.interactors.RefreshTokenInteractor;
import upb.edu.AuthMicroservice.interactors.SessionInteractor;

import java.util.UUID;

@Service
public class SessionService {

    @Autowired
    private SessionInteractor interactor;

    @Autowired
    private RefreshTokenInteractor refreshTokenInteractor;

    public String generateSession(int userId) {
        return interactor.execute(userId).toString();
    }

    public String refreshAccessToken(String refreshToken) {
        UUID rt;
        try {
            rt = UUID.fromString(refreshToken);
        } catch (Exception e) {
            return null;
        }
        try {
            UUID newAccess = refreshTokenInteractor.execute(rt, 15);
            if (newAccess == null) {
                return "401";
            }
            return newAccess.toString();
        } catch (IllegalStateException ex) {
            if ("SESSION_INVALID".equals(ex.getMessage())) {
                return "403";
            }
            return null;
        }
    }
}