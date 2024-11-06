package GHEBACKEND.GHEBACKEND.service.DonneesReferentielles;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import GHEBACKEND.GHEBACKEND.model.DonneesReferentielles.TypeDocumentModel;
import GHEBACKEND.GHEBACKEND.repository.DonneesReferentielles.TypeDocumentRepo;
import GHEBACKEND.GHEBACKEND.utils.UtilityMethods;

@Service
public class TypeDocumentService {


    @Autowired
    private TypeDocumentRepo typeDocumentRepo;

    @Autowired
    private UtilityMethods utilityMethods;

    // Function fetching type dco data
    public List<TypeDocumentModel> getAllTypeDocsData() {

        List<TypeDocumentModel> fetchedTypeDocsData = typeDocumentRepo.findAll();

        return fetchedTypeDocsData;

    }

    // Function creating new type doc data
    public void addNewTypeDocs(TypeDocumentModel typeDocumentModel) {

        // recuperation du code apres generation par la methode utilitaire
        Integer code = utilityMethods.codeGenerator("TDC_CODE", "T_TYPE_DOCUMENT");
        
        // version par default a la creation
        Integer defaultVersion = 1;

        // getting datetime from utilityMethod class
        String currentDateTime = utilityMethods.getCurrentDateTime();


        typeDocumentModel.setTdcCode(code);
        typeDocumentModel.setTdcVersion(defaultVersion);
        typeDocumentModel.setTdcDateCreation(currentDateTime);
        
        typeDocumentRepo.save(typeDocumentModel);

    }

    // Function editing existing type doc data
    public void updateTypeDocsData(Integer code, TypeDocumentModel typeDocumentModel) {

        // get current promotion version from repo function
        Integer currentVersion = utilityMethods.getCurrentVersion(code, "TDC_CODE", "T_TYPE_DOCUMENT", "TDC_VERSION");
        
        // get a new version by increment current by 1
        Integer newVersion = currentVersion + 1;

        TypeDocumentModel existingTypeDocData = typeDocumentRepo.findById(code)
            .orElseThrow(() -> new IllegalArgumentException("Could not find data using code provided"));
        
        existingTypeDocData.setTdcLib(typeDocumentModel.getTdcLib());
        existingTypeDocData.setTdcModifierPar(typeDocumentModel.getTdcModifierPar());
        existingTypeDocData.setTdcVersion(newVersion);

        typeDocumentRepo.save(existingTypeDocData);

    }

    public void deleteTypeDocData(Integer code) {

        if(typeDocumentRepo.existsById(code)) {
            typeDocumentRepo.deleteById(code);
        } else {
            throw new IllegalArgumentException("Could not find data using code provided");
        }

    }

    public Integer getTotalDataNumber(String tableName) {   
        return utilityMethods.getTotalNumberOfDonneesRef(tableName);
    }


}
