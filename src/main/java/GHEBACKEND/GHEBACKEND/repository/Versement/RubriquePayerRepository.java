package GHEBACKEND.GHEBACKEND.repository.Versement;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

    @Query(value = "SELECT * FROM T_RUBRIQUE_PAYER WHERE INS_CODE = ?1 AND CLA_CODE = ?2 ORDER BY RUB_CODE ASC", nativeQuery = true)
    List<RubriquePayer> findByInscriptionAndClasseOrderByRubCode(Integer insCode,Integer claCode);

    RubriquePayer findByRubriqueAndInscription(RubriqueModel rubrique,Inscription inscription);


    @Query("select r from RubriquePayer r where r.inscription = :inscription and r.rubrique.rub_code = :rubCode")
    RubriquePayer findByInscriptionAndRubCode(@Param("inscription") Inscription inscription,@Param("rubCode") String rubCode);

    @Query("select sum(r.rbpMontant) from RubriquePayer r where r.inscription = :inscription")
    Double findSumRubMontantByInscription(@Param("inscription") Inscription inscription);
}

