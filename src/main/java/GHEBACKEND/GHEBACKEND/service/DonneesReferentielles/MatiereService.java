package GHEBACKEND.GHEBACKEND.service.DonneesReferentielles;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public List<MatiereModel> getAllMatiere() {
        return matiereRepo.findAll();
    }

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

    @Transactional
    public void updateMatiere(int code, MatiereModel matiereModel) {

        int newVersion = utilityMethods.getCurrentVersion(code, "MAT_CODE", "T_MATIERE", "MAT_VERSION") + 1;

        matiereRepo.updateMatiereData(code, matiereModel.getMatLib(), matiereModel.getMatModifierPar(), newVersion);

    }

    public void deleteMatiereData(int code) {

        if(matiereRepo.existsById(code)) {
            matiereRepo.deleteById(code);
        } else {
            throw new IllegalArgumentException("Could not find data with the provided code.");
        }

    }

    public Integer getTotalDataNumber(String tableName) {   
        return utilityMethods.getTotalNumberOfDonneesRef(tableName);
    }

}
