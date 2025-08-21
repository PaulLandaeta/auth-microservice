package upb.edu.AuthMicroservice.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import upb.edu.AuthMicroservice.models.Permission;


public interface PermissionRepository extends JpaRepository<Permission, String> {
     boolean existsById (String id);
     void deleteById(String id);
     List<Permission> findAll();
}  
