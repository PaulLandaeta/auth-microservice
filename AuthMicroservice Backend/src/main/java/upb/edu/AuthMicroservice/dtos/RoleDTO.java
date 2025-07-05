package upb.edu.AuthMicroservice.dtos;

import jakarta.validation.constraints.NotBlank;

public class RoleDTO {
    private Long id;

    @NotBlank(message = "El nombre no puede estar vacío")
    private String name;

    public RoleDTO() {}

    public RoleDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
