package GHEBACKEND.GHEBACKEND.repository.Inscription;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import GHEBACKEND.GHEBACKEND.model.Inscription.HistoriqueRubriqueInscription;
import GHEBACKEND.GHEBACKEND.model.Inscription.Inscription;

@Repository
public interface HistoriqueRubriqueInscriptionRepository 
    extends JpaRepository<HistoriqueRubriqueInscription,Integer>{

    List<HistoriqueRubriqueInscription> findByInscription(Inscription inscription);

    @Query("select sum(h.hisMontantPrevu) from HistoriqueRubriqueInscription h where h.inscription = :inscription")
    Double findSumHisMontantPrevuByInscription(@Param("inscription") Inscription inscription);

    @Query(value = "select h.* from t_historique_ins_rub h, rubriquePayer r where h.his_rub_code not in (select rub_code from rubriquePayer)",nativeQuery = true)
    List<HistoriqueRubriqueInscription> findAllByRbpMontantRestant();
}
