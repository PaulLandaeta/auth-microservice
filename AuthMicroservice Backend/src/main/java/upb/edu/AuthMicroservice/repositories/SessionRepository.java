package upb.edu.AuthMicroservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import upb.edu.AuthMicroservice.models.Session;

import java.util.UUID;
import java.util.Optional;

@Repository
public interface SessionRepository extends JpaRepository<Session, UUID> {
    Optional<Session> findByRefreshToken(UUID refreshToken);

}
