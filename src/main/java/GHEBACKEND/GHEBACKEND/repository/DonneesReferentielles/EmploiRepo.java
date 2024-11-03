package GHEBACKEND.GHEBACKEND.repository.DonneesReferentielles;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import GHEBACKEND.GHEBACKEND.model.DonneesReferentielles.EmploiModel;

@Repository
public interface EmploiRepo extends JpaRepository<EmploiModel, Integer> {}
