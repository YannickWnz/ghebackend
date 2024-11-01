package GHEBACKEND.GHEBACKEND.service.DonneesReferentielles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import GHEBACKEND.GHEBACKEND.model.DonneesReferentielles.EmploiModel;
import GHEBACKEND.GHEBACKEND.repository.DonneesReferentielles.EmploiRepo;
import GHEBACKEND.GHEBACKEND.utils.UtilityMethods;

@Service
public class EmploiService {

    @Autowired
    private EmploiRepo emploiRepo;

    @Autowired
    private UtilityMethods utilityMethods;

    public void addNewEmploi(EmploiModel emploiModel) {

        // recuperation du code apres generation par la methode utilitaire
        Integer code = utilityMethods.codeGenerator("EMP_CODE", "T_EMPLOI");
        
        // version par default a la creation
        Integer defaultVersion = 1;

        // getting datetime from utilityMethod class
        String currentDateTime = utilityMethods.getCurrentDateTime();


        emploiModel.setEmpCode(code);
        emploiModel.setEmpVersion(defaultVersion);
        emploiModel.setEmpDateCreation(currentDateTime);

        emploiRepo.save(emploiModel);

    }

}
