package upb.edu.AuthMicroservice.interactors;

import org.springframework.stereotype.Service;
import upb.edu.AuthMicroservice.models.User;
import upb.edu.AuthMicroservice.repositories.UserRepository;

import java.util.Optional;

@Service
public class UserInteractor {
    private final UserRepository userRepository;

    public UserInteractor(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
