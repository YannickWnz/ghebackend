package GHEBACKEND.GHEBACKEND.repository.Inscription;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import GHEBACKEND.GHEBACKEND.model.DonneesReferentielles.ClasseModel;
import GHEBACKEND.GHEBACKEND.model.Inscription.Inscription;

@Repository
public interface InscriptionRepository extends  JpaRepository<Inscription,Integer>{
    Optional<List<Inscription>> findByInsNiveauValidation(int insNiveauValidation);

    @Query("select max(i.insCode) from Inscription i")
    Optional<Integer> findMaxInsCode();

    @Query("select i from Inscription i where i.etudiant.etd_code = :etuCode and i.promotion.pro_code = :proCode and i.classe.cla_code = :claCode and i.anneeAcademique.aac_code = :anneeCode")
    Optional<Inscription> findByInscription(
        @Param("etuCode") Integer etuCode,
        @Param("proCode") int proCode,
        @Param("claCode") int claCode,
        @Param("anneeCode") int anneeCode
        );
}
