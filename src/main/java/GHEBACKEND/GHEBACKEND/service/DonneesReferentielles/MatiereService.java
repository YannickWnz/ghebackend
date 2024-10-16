package GHEBACKEND.GHEBACKEND.service.DonneesReferentielles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import GHEBACKEND.GHEBACKEND.model.DonneesReferentielles.MatiereModel;
import GHEBACKEND.GHEBACKEND.repository.DonneesReferentielles.MatiereRepo;
import GHEBACKEND.GHEBACKEND.utils.UtilityMethods;

@Service
public class MatiereService {

    @Autowired
    private MatiereRepo matiereRepo;

    @Autowired
    private UtilityMethods utilityMethods;

    // add new matiere method
    public void addNewMatiere(MatiereModel matiereModel) {

        // generation du code
        Integer code = utilityMethods.codeGenerator("MAT_CODE", "T_MATIERE");

        // version definie sur 1 par default a la creation
        int version = 1;
        
        // getting datetime from utilityMethod class
        String currentDateTime = utilityMethods.getCurrentDateTime();


        matiereModel.setMatCode(code);
        matiereModel.setMatVersion(version);
        matiereModel.setMatDateCreation(currentDateTime);

        matiereRepo.save(matiereModel);
    }

}
