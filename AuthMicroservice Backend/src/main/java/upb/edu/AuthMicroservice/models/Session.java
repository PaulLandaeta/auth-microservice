package upb.edu.AuthMicroservice.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "session")
public class Session {

    @Id
    private UUID id;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "access_token", columnDefinition = "uuid")
    private UUID accessToken;

    @Column(name = "refresh_token", columnDefinition = "uuid", unique = true)
    private UUID refreshToken;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "expires_at")
    private LocalDateTime expiresAt;

    @Column(name = "refresh_token_expires_at")
    private LocalDateTime refreshTokenExpiresAt;

    @Column(name = "is_valid")
    private boolean isValid;

    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public UUID getAccessToken() {
        return accessToken;
    }
    public void setAccessToken(UUID accessToken) {
        this.accessToken = accessToken;
    }
    public UUID getRefreshToken() {
        return refreshToken;
    }
    public void setRefreshToken(UUID refreshToken) {
        this.refreshToken = refreshToken;
    }    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    public LocalDateTime getExpiresAt() {
        return expiresAt;
    }
    public void setExpiresAt(LocalDateTime expiresAt) {
        this.expiresAt = expiresAt;
    }
    public LocalDateTime getRefreshTokenExpiresAt() { 
        return refreshTokenExpiresAt; 
    }
    public void setRefreshTokenExpiresAt(LocalDateTime refreshTokenExpiresAt) { 
        this.refreshTokenExpiresAt = refreshTokenExpiresAt; 
    }
    public boolean isValid() {
        return isValid;
    }
    public void setIsValid(boolean isValid) {
        this.isValid = isValid;
    }
}

