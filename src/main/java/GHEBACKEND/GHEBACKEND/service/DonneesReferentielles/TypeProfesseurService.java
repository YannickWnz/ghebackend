package GHEBACKEND.GHEBACKEND.service.DonneesReferentielles;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import GHEBACKEND.GHEBACKEND.model.DonneesReferentielles.TypeProfesseur;
import GHEBACKEND.GHEBACKEND.repository.DonneesReferentielles.TypeProfesseurRepo;
import GHEBACKEND.GHEBACKEND.utils.UtilityMethods;

@Service
public class TypeProfesseurService {

    @Autowired
    private TypeProfesseurRepo typeProfesseurRepo;

    @Autowired
    private UtilityMethods utilityMethods;

    
    public List<TypeProfesseur> getAllTypeProf() {
        return typeProfesseurRepo.findAll();
    }

    public void addNewTypeProfesseur(TypeProfesseur typeProfesseur) {

        Integer code = utilityMethods.codeGenerator("TPR_CODE", "T_TYPE_PROFESSEUR");

         // version definie sur 1 par default a la creation
         Integer version = 1;
        
         // getting datetime from utilityMethod class
         String currentDateTime = utilityMethods.getCurrentDateTime();
 
 
         typeProfesseur.setTprCode(code);
         typeProfesseur.setTprVersion(version);
         typeProfesseur.setTprDateCreation(currentDateTime);

         typeProfesseurRepo.save(typeProfesseur);

    }

    public void updateTypeProfesseurData(Integer code, TypeProfesseur typeProfesseur) {

        Integer newVersion = utilityMethods.getCurrentVersion(code, "TPR_CODE", "T_TYPE_PROFESSEUR", "TPR_VERSION") + 1;

        // find data before update else throw error
        TypeProfesseur existingProfData = typeProfesseurRepo.findById(code)
        .orElseThrow(() -> new IllegalArgumentException("Could not find data with given code"));
        
        // set new data then save ...
            existingProfData.setTprLib(typeProfesseur.getTprLib());
            existingProfData.setTprModifierPar(typeProfesseur.getTprModifierPar());
            existingProfData.setTprTauxHoraire(typeProfesseur.getTprTauxHoraire());
            existingProfData.setTprVersion(newVersion);

            typeProfesseurRepo.save(existingProfData);

    }

    
    public void deleteTypeProfesseurData(Integer code) {

        if(typeProfesseurRepo.existsById(code)) {
            typeProfesseurRepo.deleteById(code);
        } else {
            throw new IllegalArgumentException("Could not find data with the provided code.");
        }

    }

    public Integer getTotalDataNumber(String tableName) {   
        return utilityMethods.getTotalNumberOfDonneesRef(tableName);
    }


}
