package GHEBACKEND.GHEBACKEND.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import GHEBACKEND.GHEBACKEND.model.AnneeAcademique;
import GHEBACKEND.GHEBACKEND.repository.AnneeAcademiqueRepo;
import GHEBACKEND.GHEBACKEND.utils.CodeGenerator;

@Service
public class AnneeAcademiqueService {

    @Autowired
    private AnneeAcademiqueRepo anneeAcademiqueRepo;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private CodeGenerator codeGenerator;

    public List<AnneeAcademique> getAllAnneeAcademique() {
        return anneeAcademiqueRepo.findAll();
    }

    public void addAnneAcademique(AnneeAcademique anneeAcademique) {

        // get code from generateCode util method
        int code = codeGenerator.generateCode("ANNEE_ACADEMIQUE");

         // Set the unique aac code to the aac object
         anneeAcademique.setAacCode(code);
         
         // Set default version to 1 
         anneeAcademique.setAacVersion(1);

         String insertAacQuery = "INSERT INTO T_ANNEE_ACADEMIQUE (AAC_CODE, AAC_LIB, AAC_CREER_PAR, AAC_STATUS, AAC_VERSION) VALUES (?, ?, ?, ?, ?)";
 
         // running insert query
         jdbcTemplate.update(
                insertAacQuery, 
                anneeAcademique.getAacCode(),
                anneeAcademique.getAacLib(),
                anneeAcademique.getAacCreerPar(),
                anneeAcademique.getAacStatus(),
                anneeAcademique.getAacVersion()
             );
    }

    public void updateAnneeAcademique(Integer aacCode, String aacLib, String aacModifierPar, boolean aacStatus) {

        int newVersion = anneeAcademiqueRepo.findProVersion(aacCode) + 1;

        String updateQuery;

        if(aacLib == null) {
            updateQuery = "UPDATE T_ANNEE_ACADEMIQUE SET AAC_STATUS = ?, AAC_MODIFIER_PAR = ?, AAC_VERSION = ? WHERE AAC_CODE = ?";

            // running update query
            jdbcTemplate.update(
                updateQuery, 
                aacStatus,
                aacModifierPar,
                newVersion,
                aacCode
            );
        } 
        else {

            updateQuery = "UPDATE T_ANNEE_ACADEMIQUE SET AAC_LIB = ?, AAC_MODIFIER_PAR = ?, AAC_VERSION = ?, AAC_STATUS = ? WHERE AAC_CODE = ?";

            // running update query
            jdbcTemplate.update(
                updateQuery, 
                aacLib,
                aacModifierPar,
                newVersion,
                aacStatus,
                aacCode
            );

        }

    }
    
}
