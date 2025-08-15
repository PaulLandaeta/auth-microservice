package upb.edu.AuthMicroservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import upb.edu.AuthMicroservice.models.Session;

import java.util.UUID;

public interface SessionRepository extends JpaRepository<Session, UUID> {
}
