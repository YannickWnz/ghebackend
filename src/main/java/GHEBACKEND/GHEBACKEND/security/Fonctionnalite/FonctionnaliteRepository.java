package GHEBACKEND.GHEBACKEND.security.Fonctionnalite;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FonctionnaliteRepository 
    extends JpaRepository<Fonctionnalite,Integer>{
    
}
