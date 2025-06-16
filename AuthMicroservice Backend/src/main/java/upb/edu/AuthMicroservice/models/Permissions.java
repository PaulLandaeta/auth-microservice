import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/permissions")
public class PermissionController {

    private final CreatePermissionInteractor interactor;

    public PermissionController(CreatePermissionInteractor interactor) {
        this.interactor = interactor;
    }

    @PostMapping
    public ResponseEntity<BaseResponse> createPermission(@RequestBody CreatePermissionRequest request) {
        BaseResponse response = interactor.execute(request);
        return ResponseEntity.status(201).body(response);
    }
}
