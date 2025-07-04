package upb.edu.AuthMicroservice.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import jakarta.servlet.ServletException;

import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;

import upb.edu.AuthMicroservice.models.User;
import upb.edu.AuthMicroservice.services.UserService;
import upb.edu.AuthMicroservice.models.Response;

@Component
public class UserController {
private static final Logger log = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    public ServerResponse registerUser(ServerRequest request) {

        try{
            User user = request.body(User.class);
            User created = userService.createUser(user);
            Response response = new Response("201", "Ok");
            return ServerResponse
               .status(201)
               .body(response);
        } catch (IOException | ServletException  e) {
        log.error("Error binding request or authenticating user", e);
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "Login failed",
                e
            );
        }
    }
}
