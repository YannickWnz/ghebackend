package GHEBACKEND.GHEBACKEND.security.Authentication.Register;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequest {
    private String nom;
    private String prenom;
    private String email;
    private String username;
    private String password;
}
