package GHEBACKEND.GHEBACKEND.repository.DonneesReferentielles;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import GHEBACKEND.GHEBACKEND.model.DonneesReferentielles.Filiere;

@Repository
public interface FiliereRepo extends JpaRepository<Filiere, Integer> {}
