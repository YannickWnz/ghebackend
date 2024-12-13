package GHEBACKEND.GHEBACKEND.security.Utilisateur;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur,String>{

    Optional<Utilisateur> findByUtiUsername(String username);
}
