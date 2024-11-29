package GHEBACKEND.GHEBACKEND.repository.Inscription;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import GHEBACKEND.GHEBACKEND.model.Inscription.Inscription;

@Repository
public interface InscriptionRepository extends  JpaRepository<Inscription,Integer>{
    Optional<List<Inscription>> findByInsNiveauValidation(int insNiveauValidation);
}
