package GHEBACKEND.GHEBACKEND.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import GHEBACKEND.GHEBACKEND.model.AnneeAcademique;
import GHEBACKEND.GHEBACKEND.repository.AnneeAcademiqueRepo;

@Service
public class AnneeAcademiqueService {

    @Autowired
    private AnneeAcademiqueRepo anneeAcademiqueRepo;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<AnneeAcademique> getAllAnneeAcademique() {
        return anneeAcademiqueRepo.findAll();
    }

}
