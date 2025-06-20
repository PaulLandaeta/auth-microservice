package upb.edu.AuthMicroservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import upb.edu.AuthMicroservice.models.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<Permission, Long> {
}