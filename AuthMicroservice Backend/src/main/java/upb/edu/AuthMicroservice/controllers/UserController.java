package upb.edu.AuthMicroservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import upb.edu.AuthMicroservice.models.User;
import upb.edu.AuthMicroservice.services.UserService;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    public User registerUser(User user) {
        return userService.createUser(user);
    }
}