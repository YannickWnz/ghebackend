package GHEBACKEND.GHEBACKEND.service.DonneesReferentielles;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import GHEBACKEND.GHEBACKEND.model.DonneesReferentielles.ClasseDataProjection;
import GHEBACKEND.GHEBACKEND.model.DonneesReferentielles.ClasseModel;
import GHEBACKEND.GHEBACKEND.repository.DonneesReferentielles.ClasseRepo;
import GHEBACKEND.GHEBACKEND.utils.UtilityMethods;

@Service
public class ClasseService {

    @Autowired
    private ClasseRepo classeRepo;

    @Autowired
    private UtilityMethods utilityMethods;

    public List<ClasseDataProjection> getClassesData() {
        return classeRepo.getClassesData();
    }
    // public List<ClasseModel> getClassesData() {
    //     return classeRepo.getClassesData();
    // }

    public List<ClasseModel> getAllCLasse() {
        return classeRepo.findAll();
    }

    public void addNewClasse(ClasseModel classeModel) {

        Integer code = utilityMethods.codeGenerator("CLA_CODE", "T_CLASSE");

         // version definie sur 1 par default a la creation
         Integer version = 1;
        
         // getting datetime from utilityMethod class
         String currentDateTime = utilityMethods.getCurrentDateTime();
 
 
         classeModel.setCla_code(code);
         classeModel.setCla_version(version);
         classeModel.setCla_date_creation(currentDateTime);

         classeRepo.save(classeModel);

    }

    @Transactional
    public void updateClasseData(Integer code, ClasseModel classeModel) {

        Integer newVersion = utilityMethods.getCurrentVersion(code, "CLA_CODE", "T_CLASSE", "CLA_VERSION") + 1;

        classeRepo.updateClasseData(code, classeModel.getCla_lib(), classeModel.getCla_modifier_par(), newVersion);

    }

    
    public void deleteClasseData(int code) {

        if(classeRepo.existsById(code)) {
            classeRepo.deleteById(code);
        } else {
            throw new IllegalArgumentException("Could not find data with the provided code.");
        }

    }

    public List<ClasseModel> getClasseInNiveauAndFiliere(Integer nivCode, Integer filCode) {
        return classeRepo.getClasse(nivCode, filCode);

    }

    public Integer getTotalDataNumber(String tableName) {
        return utilityMethods.getTotalNumberOfDonneesRef(tableName);
    }

    public ClasseModel getClasseModelByClaCode(int code){
        return classeRepo
            .findById(code)
            .orElseThrow(() ->
                new IllegalStateException(
                    String.format(
                        "La classe de numéro %s n'existe pas",
                        code)));
    }

}
