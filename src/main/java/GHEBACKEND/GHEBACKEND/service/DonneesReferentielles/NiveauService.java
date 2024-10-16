package GHEBACKEND.GHEBACKEND.service.DonneesReferentielles;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import GHEBACKEND.GHEBACKEND.model.DonneesReferentielles.Niveau;
import GHEBACKEND.GHEBACKEND.repository.DonneesReferentielles.NiveauRepo;
import GHEBACKEND.GHEBACKEND.utils.UtilityMethods;

@Service
public class NiveauService {

    @Autowired
    private UtilityMethods utilityMethods;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NiveauRepo niveauRepo;

    public List<Niveau> getAllNiveau() {

        return niveauRepo.findAll();
    }

    @Transactional
    public void addNewNiveau(Niveau niveau) {

        int code = utilityMethods.codeGenerator("NIV_CODE", "T_NIVEAU");

        Integer defaultVersion = 1;

        niveauRepo.insertNewNiveauData(code, niveau.getNivLib(), niveau.getNivCreerPar(), defaultVersion);

    }

    @Transactional
    public void updateNiveauData(int nivCode, Niveau niveau) {

        int newVersion = utilityMethods.getCurrentVersion(nivCode, "NIV_CODE", "T_NIVEAU", "NIV_VERSION") + 1;

        niveauRepo.updateNiveauData(nivCode, niveau.getNivLib(), niveau.getNivModifierPar(), newVersion);

    }

}
