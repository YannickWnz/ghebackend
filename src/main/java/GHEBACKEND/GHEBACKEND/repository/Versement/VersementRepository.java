package GHEBACKEND.GHEBACKEND.repository.Versement;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import GHEBACKEND.GHEBACKEND.model.Versement.Versement;

import java.time.LocalDate;
import java.util.List;
import GHEBACKEND.GHEBACKEND.model.Inscription.Inscription;


@Repository
public interface VersementRepository extends JpaRepository<Versement,String> {

    Optional<List<Versement>> findByInscriptionOrderByVerDateAsc(Inscription inscription);

    @Query("select max(v.verCode) from Versement v")
    Optional<String> findMaxVerCode();
    
    /* 
     * Afficher les versements entre intervalle de date ok
     */
    List<Versement> findByVerDateBetween(LocalDate startDate,LocalDate endDate);

    @Query("select v from Versement v join v.inscription i where FUNCTION('YEAR', i.insDate) = :currentYear")
    List<Versement> findVersementsByAnneeInscription(@Param("currentYear") int currentYear);

    List<Versement> findByVerDate(LocalDate verDate);
}
