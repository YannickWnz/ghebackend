package GHEBACKEND.GHEBACKEND.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import GHEBACKEND.GHEBACKEND.model.User;
import GHEBACKEND.GHEBACKEND.service.UserService;

@RestController
// @RequestMapping("/api/auth")
// @RequestMapping("/api/auth")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    // public User registerUser(@RequestBody User user) {
    //     // userService.registerUser(user);
    //     return user;
    // }
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        userService.registerUser(user);
        return ResponseEntity.ok("User successfully registered.");
    }

    // @GetMapping
    // @PostMapping("/login")
    // public String returnSmth() {
    //     return "trying ....";
    // }


}
