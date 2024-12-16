package GHEBACKEND.GHEBACKEND.repository.PriseEnCharge;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import GHEBACKEND.GHEBACKEND.model.PriseEnCharge.PersonneContactModel;
import GHEBACKEND.GHEBACKEND.model.PriseEnCharge.StudentContactDataProjection;

@Repository
public interface PersonneContactRepo extends JpaRepository<PersonneContactModel, Integer> {

    
    @Query(value="SELECT LIE_LIB, CON_NOM, CON_PRENOM, CON_ADDRESSE, CON_EMAIL, CON_PHONE FROM T_PERSONNE_CONTACT CON, T_LIEN LIE WHERE CON.ETD_CODE = ?1 AND LIE.LIE_CODE = CON.LIE_CODE", nativeQuery=true)
    List<StudentContactDataProjection> getContactsByStudentCode(Integer code);

    // List<PersonneContactModel> findByEtdCode(Integer code);

}
