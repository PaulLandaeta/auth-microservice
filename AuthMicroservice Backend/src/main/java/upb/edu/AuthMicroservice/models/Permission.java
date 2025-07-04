package upb.edu.AuthMicroservice.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;




@Entity
@Table(name = "permissions")
public class Permission {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String name;


    private String action;


    private LocalDateTime createdAt;


    private LocalDateTime updatedAt;


    @PrePersist
    public void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = createdAt;
    }
    @PreUpdate
    public void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
    public long getId(){
        return id;
    }

    public String getName(){
        return name;
    }
    public String getAction(){
        return action;
    }
    public LocalDateTime getCreatedAt(){
        return createdAt;
    }
    public LocalDateTime getUpdatedAt(){
        return updatedAt;
    }
    public void setName (String name){
        this.name = name;
    }
    public void setAction (String action){
        this.action = action;
    }
    public void setCreatedAt (LocalDateTime createdAt){
        this.createdAt = createdAt;
    }
    public void setUpdatedAt (LocalDateTime updatedAt){
        this.updatedAt = updatedAt;
    }
    public void setId (long id){
        this.id = id;
    }  
   
}
