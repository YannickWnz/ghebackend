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

}
