package GHEBACKEND.GHEBACKEND.repository.DonneesReferentielles;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import GHEBACKEND.GHEBACKEND.model.DonneesReferentielles.RubriqueModel;

@Repository
public interface RubriqueRepo extends JpaRepository<RubriqueModel, Integer> {}
