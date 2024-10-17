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

    // service function qui se charge de la recuperation de tous les niveaux
    public List<Niveau> getAllNiveau() {
        return niveauRepo.findAll();
    }

    // add new niveau service function
    @Transactional
    public void addNewNiveau(Niveau niveau) {

        // generate code from utility methods
        int code = utilityMethods.codeGenerator("NIV_CODE", "T_NIVEAU");
        
        // set default version ... 
        Integer defaultVersion = 1;
        
        // run create methods from niveau repo 
        niveauRepo.insertNewNiveauData(code, niveau.getNivLib(), niveau.getNivCreerPar(), defaultVersion);

    }

    // update niveau service function
    @Transactional
    public void updateNiveauData(int nivCode, Niveau niveau) {
        
        // recuperation de la version courante avec la methode getCurrentVersion definie dans utilityMethods  
        //  on obtient la nouvelle version en incrementant la version courante de 1
        int newVersion = utilityMethods.getCurrentVersion(nivCode, "NIV_CODE", "T_NIVEAU", "NIV_VERSION") + 1;
        
        // mise a jour des donnees en utilisant la methode definit dans niveauRepo
        niveauRepo.updateNiveauData(nivCode, niveau.getNivLib(), niveau.getNivModifierPar(), newVersion);

    }

    // delete niveau function
    public void deleteNiveauData(int nivCode) {

        if(niveauRepo.existsById(nivCode)) {
            niveauRepo.deleteById(nivCode);
        } else {
            throw new IllegalArgumentException("Could not find data with the provided code.");
        }

    }

}
