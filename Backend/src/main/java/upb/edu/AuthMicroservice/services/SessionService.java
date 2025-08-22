package upb.edu.AuthMicroservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import upb.edu.AuthMicroservice.interactors.SessionInteractor;

@Service
public class SessionService {

    @Autowired
    private SessionInteractor interactor;

    public String generateSession(int userId) {
        return interactor.execute(userId).toString();
    }
}
