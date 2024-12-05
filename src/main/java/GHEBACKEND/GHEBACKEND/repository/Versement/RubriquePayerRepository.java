package GHEBACKEND.GHEBACKEND.repository.Versement;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import GHEBACKEND.GHEBACKEND.model.Versement.RubriquePayer;
import java.util.List;
import GHEBACKEND.GHEBACKEND.model.Inscription.Inscription;


@Repository
public interface RubriquePayerRepository extends JpaRepository<RubriquePayer, String>{
    Optional<List<RubriquePayer>> findByInscriptionOrderByRbpCodeAsc(Inscription inscription);
}
