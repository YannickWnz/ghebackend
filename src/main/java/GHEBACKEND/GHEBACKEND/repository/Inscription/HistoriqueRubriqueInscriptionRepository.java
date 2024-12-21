package GHEBACKEND.GHEBACKEND.repository.Inscription;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import GHEBACKEND.GHEBACKEND.model.Inscription.HistoriqueRubriqueInscription;
import GHEBACKEND.GHEBACKEND.model.Inscription.Inscription;

@Repository
public interface HistoriqueRubriqueInscriptionRepository 
    extends JpaRepository<HistoriqueRubriqueInscription,Integer>{

    List<HistoriqueRubriqueInscription> findByInscription(Inscription inscription);
}
