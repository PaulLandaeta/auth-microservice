package upb.edu.AuthMicroservice.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UpdatePermissionDto {
    @NotBlank(message = "El nombre es obligatorio")
    private String name;
}
