package upb.edu.AuthMicroservice.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private String email;
    private String password;
    
    @JsonProperty("is_validated")
    private boolean isValidated = false;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_profile_id", referencedColumnName = "id")
    private UserProfile userProfile;

    public User() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isValidated() {
        return isValidated;
    }

    public void setValidated(boolean validated) {
        isValidated = validated;
    }

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }

    @JsonProperty("first_name")
    public String getFirstName() {
        return userProfile != null ? userProfile.getFirstName() : null;
    }

    @JsonProperty("first_name")
    public void setFirstName(String firstName) {
        if (userProfile == null) {
            userProfile = new UserProfile();
        }
        userProfile.setFirstName(firstName);
    }

    @JsonProperty("middle_name")
    public String getMiddleName() {
        return userProfile != null ? userProfile.getMiddleName() : null;
    }

    @JsonProperty("middle_name")
    public void setMiddleName(String middleName) {
        if (userProfile == null) {
            userProfile = new UserProfile();
        }
        userProfile.setMiddleName(middleName);
    }

    @JsonProperty("last_names")
    public String getLastName() {
        return userProfile != null ? userProfile.getLastName() : null;
    }

    @JsonProperty("last_names")
    public void setLastName(String lastName) {
        if (userProfile == null) {
            userProfile = new UserProfile();
        }
        userProfile.setLastName(lastName);
    }

    public String getCode() {
        return userProfile != null ? userProfile.getCode() : null;
    }

    public void setCode(String code) {
        if (userProfile == null) {
            userProfile = new UserProfile();
        }
        userProfile.setCode(code);
    }

    @JsonProperty("enrollment_date")
    public java.util.Date getEnrollmentDate() {
        return userProfile != null ? userProfile.getEnrollmentDate() : null;
    }

    @JsonProperty("enrollment_date")
    public void setEnrollmentDate(java.util.Date enrollmentDate) {
        if (userProfile == null) {
            userProfile = new UserProfile();
        }
        userProfile.setEnrollmentDate(enrollmentDate);
    }
}