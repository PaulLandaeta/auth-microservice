package upb.edu.AuthMicroservice.interactors;

import org.springframework.stereotype.Service;

import upb.edu.AuthMicroservice.models.User;
import upb.edu.AuthMicroservice.repositories.UserRepository;

@Service
public class UserInteractor {
    private final UserRepository userRepository;
    
    public UserInteractor(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

}
