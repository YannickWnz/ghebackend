package GHEBACKEND.GHEBACKEND.security.Authentication;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import GHEBACKEND.GHEBACKEND.security.Authentication.Register.AuthenticationRequest;
import GHEBACKEND.GHEBACKEND.security.Authentication.Register.RegisterRequest;
import GHEBACKEND.GHEBACKEND.security.Authentication.Register.RegisterResponse;
import GHEBACKEND.GHEBACKEND.security.Utilisateur.Utilisateur;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("creer-compte")
    public Utilisateur inscription(@RequestBody AuthenticationRequest authenticationRequest){
        return authenticationService.inscrire(authenticationRequest);
    }

    @PostMapping("connexion")
    public RegisterResponse connexion(@RequestBody RegisterRequest registerRequest){
        return authenticationService.connexion(registerRequest);
    }
}
