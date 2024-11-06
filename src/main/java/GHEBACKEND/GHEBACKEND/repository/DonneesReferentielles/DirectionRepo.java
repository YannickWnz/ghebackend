package GHEBACKEND.GHEBACKEND.repository.DonneesReferentielles;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import GHEBACKEND.GHEBACKEND.model.DonneesReferentielles.DirectionModel;

@Repository
public interface DirectionRepo extends JpaRepository<DirectionModel, Integer> {}
