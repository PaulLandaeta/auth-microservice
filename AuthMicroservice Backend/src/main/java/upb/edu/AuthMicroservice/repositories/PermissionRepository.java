package upb.edu.AuthMicroservice.repositories;

import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;

import upb.edu.AuthMicroservice.models.Permission;


public interface PermissionRepository extends JpaRepository<Permission, Long> {
    Optional<Permission> findByName(String name);
}  
