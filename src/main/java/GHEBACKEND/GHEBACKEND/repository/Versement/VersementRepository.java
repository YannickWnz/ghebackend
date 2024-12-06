package GHEBACKEND.GHEBACKEND.repository.Versement;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import GHEBACKEND.GHEBACKEND.model.Versement.Versement;
import java.util.List;
import GHEBACKEND.GHEBACKEND.model.Inscription.Inscription;


@Repository
public interface VersementRepository extends JpaRepository<Versement,String> {

    Optional<List<Versement>> findByInscriptionOrderByVerDateAsc(Inscription inscription);

    @Query("select max(v.verCode) from Versement v")
    Optional<String> findMaxVerCode();
}
