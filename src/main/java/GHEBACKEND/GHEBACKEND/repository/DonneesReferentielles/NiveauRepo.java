package GHEBACKEND.GHEBACKEND.repository.DonneesReferentielles;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import GHEBACKEND.GHEBACKEND.model.DonneesReferentielles.Niveau;

@Repository
public interface NiveauRepo extends JpaRepository<Niveau, Integer> {

    @Modifying
    @Query(value = "INSERT INTO T_NIVEAU (NIV_CODE, NIV_LIB, NIV_CREER_PAR, NIV_VERSION) VALUES (?1, ?2, ?3, ?4)", nativeQuery = true)
    void insertNewNiveauData(Integer nivCode, String nivLiv, String nivCreerPar, Integer nivVersion);
    
    @Modifying
    @Query(value = "UPDATE T_NIVEAU SET NIV_LIB = ?2, NIV_MODIFIER_PAR = ?3, NIV_VERSION = ?4 WHERE NIV_CODE = ?1", nativeQuery = true)
    void updateNiveauData(Integer nivCode, String nivLib, String nivModifierPar, Integer nivVersion);

}
