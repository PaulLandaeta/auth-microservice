package upb.edu.AuthMicroservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import upb.edu.AuthMicroservice.interactors.UserInteractor;
import upb.edu.AuthMicroservice.models.User;

@Service
public class UserService {
    private final UserInteractor userInteractor;

    @Autowired
    public UserService(UserInteractor userInteractor) {
        this.userInteractor = userInteractor;
    }

    public User createUser(User user) {
        return userInteractor.createUser(user);
    }
}
