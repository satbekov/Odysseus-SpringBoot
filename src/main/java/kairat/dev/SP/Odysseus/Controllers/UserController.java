package kairat.dev.SP.Odysseus.Controllers;

import kairat.dev.SP.Odysseus.Models.Role;
import kairat.dev.SP.Odysseus.Models.User;
import kairat.dev.SP.Odysseus.POJOs.UserRegistration;
import kairat.dev.SP.Odysseus.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/users")
    public List<User> fetchUsers() {
        return userService.getAllUsers();
    }

    @PostMapping(value = "/register")
    public String register(@RequestBody UserRegistration userRegistration) {
        if(!userRegistration.getPassword().equals(userRegistration.getPasswordConfirmation())) {
            return "Passwords do not match";
        }
        else if(userService.getUser(userRegistration.getUsername()) != null) {
            return "User already exists";
        }

        // input sanitization

        userService.save(new User(userRegistration.getUsername(), userRegistration.getPassword(), Arrays.asList(
                new Role("USER")
        ), true));
        return "User successfully created";
    }
}
