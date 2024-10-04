package GHEBACKEND.GHEBACKEND.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import GHEBACKEND.GHEBACKEND.model.User;
import GHEBACKEND.GHEBACKEND.security.JwtUtils;
import GHEBACKEND.GHEBACKEND.service.UserService;

@RestController
// @RequestMapping("/api/auth")
// @RequestMapping("/api/auth")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("/register")
    // public String registerUser(@RequestBody User user) {
    //     // userService.registerUser(user);

    //     int a = 0;
    //     int b = 0;
    //     int c = 0;

    //     int num = a + b + c;

    //     String lname = user.getNom();
    //     String fname = user.getPrenom();
    //     String lNameFirstLetter = user.getNom().substring(0, 1);
    //     String fNameFirstLetter = user.getPrenom().substring(0, 1);
        
    //     return lNameFirstLetter.concat(fNameFirstLetter);
    // }
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        userService.registerUser(user);
        String token = userService.loginUser(user.getCode(), user.getPassword());
        // String token = jwtUtils.generateJwtToken(user.getNom());
        return ResponseEntity.ok("User successfully registered." + token);
    }

    @PostMapping("/login")
    // public ResponseEntity<?> loginUser(@RequestParam String code, @RequestParam String password) {
    public ResponseEntity<?> loginUser(@RequestBody User user) {
        String token = userService.loginUser(user.getCode(), user.getPassword());
        if (token != null) {
            return ResponseEntity.ok("User successfully logged in" + token);
        } else {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
        // return ResponseEntity.badRequest().body("Invalid credentials");
    }

    // @GetMapping
    // @PostMapping("/login")
    // public String returnSmth() {
    //     return "trying ....";
    // }


}
