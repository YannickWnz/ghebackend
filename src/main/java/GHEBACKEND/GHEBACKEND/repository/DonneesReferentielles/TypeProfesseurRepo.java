package GHEBACKEND.GHEBACKEND.repository.DonneesReferentielles;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import GHEBACKEND.GHEBACKEND.model.DonneesReferentielles.TypeProfesseur;

@Repository
public interface TypeProfesseurRepo extends JpaRepository<TypeProfesseur, Integer> {}
