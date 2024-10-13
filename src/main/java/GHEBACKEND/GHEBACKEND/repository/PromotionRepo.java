package GHEBACKEND.GHEBACKEND.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.*;

import GHEBACKEND.GHEBACKEND.model.Promotion;
import jakarta.transaction.Transactional;

import org.springframework.stereotype.Repository;


@Repository
public interface PromotionRepo extends JpaRepository<Promotion, Integer> {}
