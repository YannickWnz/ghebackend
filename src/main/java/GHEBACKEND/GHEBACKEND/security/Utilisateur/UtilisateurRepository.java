package GHEBACKEND.GHEBACKEND.security.Utilisateur;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur,String>{

    Optional<Utilisateur> findByUtiUsername(String utiUsername);

    @Query("select max(u.utiCode) from Utilisateur u")
    Optional<String> findMaximumUtilisateur();

    boolean existsByUtiUsername(String username);
}
