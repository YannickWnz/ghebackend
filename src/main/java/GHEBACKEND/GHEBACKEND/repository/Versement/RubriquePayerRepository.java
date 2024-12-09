package GHEBACKEND.GHEBACKEND.repository.Versement;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import GHEBACKEND.GHEBACKEND.model.DonneesReferentielles.RubriqueModel;
import GHEBACKEND.GHEBACKEND.model.Inscription.Inscription;
import GHEBACKEND.GHEBACKEND.model.Versement.RubriquePayer;



@Repository
public interface RubriquePayerRepository extends JpaRepository<RubriquePayer, String>{
    Optional<List<RubriquePayer>> findByInscriptionOrderByRbpCodeAsc(Inscription inscription);

    @Query("select max(r.rbpCode) from RubriquePayer r")
    Optional<String> findMaxRbpCode();

    RubriquePayer findByInscriptionAndRubriqueOrderByRbpCodeAsc(Inscription inscription, RubriqueModel rubrique);
}
