package upb.edu.AuthMicroservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import upb.edu.AuthMicroservice.models.ChangePasswordRequest;
import upb.edu.AuthMicroservice.models.EmailValidationRequest;
import upb.edu.AuthMicroservice.models.LoginRequest;
import upb.edu.AuthMicroservice.models.Response;
import upb.edu.AuthMicroservice.models.User;
import upb.edu.AuthMicroservice.services.UserService;

import java.io.IOException;
import java.util.Map;

import jakarta.servlet.ServletException;

import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Component
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    public ServerResponse registerUser(ServerRequest request) {
        try {
            User user = request.body(User.class);
            userService.createUser(user);
            return ServerResponse
                    .status(201)
                    .body(new Response("201", "Ok"));
        } catch (IOException | ServletException e) {
            log.error("Error binding request for registerUser", e);
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Register failed",
                    e
            );
        }
    }

    public ServerResponse changePassword(ServerRequest request) {
        log.info("Received change password request");
        try {
            ChangePasswordRequest changeRequest = request.body(ChangePasswordRequest.class);
            log.info("Processing change password for email: {}", changeRequest.getEmail());
            ResponseEntity<Object> response = userService.changePassword(
                changeRequest.getEmail(),
                changeRequest.getOldPassword(),
                changeRequest.getNewPassword()
            );

            Object body = response.getBody();
            int status = response.getStatusCodeValue();
            String msg = "";
            Object codeObj = null;

            if (body instanceof Map) {
                Map<?, ?> map = (Map<?, ?>) body;
                codeObj = map.get("code");
                Object msgObj = map.get("msg");
                if (msgObj != null) msg = msgObj.toString();
            } else if (body != null) {
                msg = body.toString();
            }

            String codeStr = codeObj != null ? String.valueOf(codeObj) : String.valueOf(status);

            return ServerResponse
                    .status(status)
                    .body(new Response(codeStr, msg));
        } catch (IOException | ServletException e) {
            log.error("Error binding request for changePassword", e);
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Change password failed",
                    e
            );
        } catch (Exception e) {
            log.error("Unexpected error in changePassword", e);
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Internal server error",
                    e
            );
        }
    }

    public ServerResponse login(ServerRequest request) {
        try {
            LoginRequest dto = request.body(LoginRequest.class);
            var resp = userService.login(dto.getEmail(), dto.getPassword());
            return ServerResponse
                    .status(resp.getStatusCodeValue())
                    .body(resp.getBody());
        } catch (IOException | ServletException e) {
            log.error("Error binding request for login", e);
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Login failed",
                    e
            );
        }
    }

    public ServerResponse validateEmail(ServerRequest request) {
        log.info("Received email validation request at: {}", request.uri().getPath());
        try {
            log.info("Attempting to read request body");
            EmailValidationRequest validationRequest = request.body(EmailValidationRequest.class);
            log.info("Successfully parsed request body. Validating email: {}", validationRequest.getEmail());
            ResponseEntity<Object> response = userService.validateEmail(validationRequest.getEmail());
            
            return ServerResponse
                    .status(response.getStatusCodeValue())
                    .body(response.getBody());
        } catch (IOException | ServletException e) {
            log.error("Error binding request for email validation", e);
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Email validation failed",
                    e
            );
        }
    }
}
