package upb.edu.AuthMicroservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import upb.edu.AuthMicroservice.models.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

}
