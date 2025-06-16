import org.springframework.stereotype.Component;

@Component
public class CreatePermissionInteractor {

    private final PermissionService permissionService;

    public CreatePermissionInteractor(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    public BaseResponse execute(CreatePermissionRequest request) {
        permissionService.createPermission(request.getName(), request.getAction());
        return new BaseResponse(201, "Permiso creado correctamente");
    }
}


