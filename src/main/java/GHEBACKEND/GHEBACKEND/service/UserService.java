package GHEBACKEND.GHEBACKEND.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.batch.BatchProperties;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;


import GHEBACKEND.GHEBACKEND.model.User;
import GHEBACKEND.GHEBACKEND.repository.UserRepository;
import GHEBACKEND.GHEBACKEND.security.JwtUtils;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private JwtUtils jwtUtils;


    // user signup function
    public void signupUser(User user) {}

    // user registration function
    public void registerUser(User user) {

        String lname = user.getNom();
        String fname = user.getPrenom();
    
        String username = fname + lname.charAt(0);

        String encodedPassword = passwordEncoder.encode(user.getPassword());

        String insertUserQuery = "INSERT INTO T_UTILISATEURS (UTI_CODE, UTI_NOM, UTI_PRENOM, UTI_PASSWORD) VALUES (?, ?, ?, ?)";

        jdbcTemplate.update(insertUserQuery, username.toLowerCase(), user.getNom(), user.getPrenom(), encodedPassword);
    
        // jdbcTemplate.update(insertUserQuery, user.getCode(), user.getNom(), user.getPrenom(), encodedPassword);

        // user.setPassword(passwordEncoder.encode(user.getPassword()));
        // return userRepository.save(user);
    }

    // user login function
    public String loginUser(String code, String password) {
        Optional<User> user = userRepository.findById(code);
        if (user.isPresent() && passwordEncoder.matches(password, user.get().getPassword()) ) {
            return jwtUtils.generateJwtToken(user.get().getCode());
        }
        return null;
    }

}
