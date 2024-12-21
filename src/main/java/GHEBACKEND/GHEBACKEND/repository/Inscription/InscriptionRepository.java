package GHEBACKEND.GHEBACKEND.repository.Inscription;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import GHEBACKEND.GHEBACKEND.model.Inscription.Inscription;

import GHEBACKEND.GHEBACKEND.model.PriseEnCharge.EtudiantModel;

import GHEBACKEND.GHEBACKEND.model.PriseEnCharge.StudentInscriptionDetailsProjection;

/* 
 * 
 */
@Repository
public interface InscriptionRepository extends  JpaRepository<Inscription,Integer>{
    Optional<List<Inscription>> findByInsNiveauValidationOrderByInsCodeAsc(int insNiveauValidation);

    @Query("select max(i.insCode) from Inscription i")
    Optional<Integer> findMaxInsCode();

    @Query("select i.insCode from Inscription i where i.etudiant.etd_code = :etuCode and i.promotion.pro_code = :proCode and i.classe.cla_code = :claCode and i.anneeAcademique.aac_code = :anneeCode")
    Integer existsInscription(
        @Param("etuCode") Integer etuCode,
        @Param("proCode") int proCode,
        @Param("claCode") int claCode,
        @Param("anneeCode") int anneeCode
        );
    
    List<Inscription> findByEtudiantOrderByInsCodeAscInsDateAsc(EtudiantModel etudiant);

    //select * from t_inscription where insCode not in (listCode)
    List<Inscription> findByEtudiantAndInsCodeNotInOrderByInsCodeAscInsDateAsc
    (EtudiantModel etudiant,List<Integer> InsCodes);

    @Query(value="SELECT AAC_LIB, CLA_LIB, PRO_LIB, FIL_LIB, NIV_LIB FROM T_ANNEE_ACADEMIQUE AAC, T_PROMOTION PRO, T_CLASSE CLA, T_INSCRIPTION INS, T_FILIERE FIL, T_NIVEAU NIV  WHERE INS.ETD_CODE = ?1 AND INS.aac_code = AAC.AAC_CODE AND INS.CLA_CODE = CLA.CLA_CODE AND INS.PRO_CODE = PRO.PRO_CODE AND CLA.FIL_CODE = FIL.FIL_CODE AND CLA.NIV_CODE= NIV.NIV_CODE ORDER BY INS_CODE DESC", nativeQuery=true)
    List<StudentInscriptionDetailsProjection> getStudentInscriptionDetails(Integer code);

    @Query("select max(i.insCode) from Inscription i where i.etudiant = :etudiantModel")
    Optional<Integer> findMaximumByEtudiant(@Param("etudiantModel") EtudiantModel etudiantModel);
}
