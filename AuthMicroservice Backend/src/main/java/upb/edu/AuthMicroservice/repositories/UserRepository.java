package upb.edu.AuthMicroservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import upb.edu.AuthMicroservice.models.User;

public interface UserRepository extends JpaRepository<User, String> {
}