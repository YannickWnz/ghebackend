package GHEBACKEND.GHEBACKEND.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import GHEBACKEND.GHEBACKEND.model.AnneeAcademique;

@Repository
public interface AnneeAcademiqueRepo extends JpaRepository<AnneeAcademique, Integer> {}
