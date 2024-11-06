package GHEBACKEND.GHEBACKEND.service.DonneesReferentielles;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import GHEBACKEND.GHEBACKEND.model.DonneesReferentielles.NationaliteModel;
import GHEBACKEND.GHEBACKEND.repository.DonneesReferentielles.NationaliteRepo;
import GHEBACKEND.GHEBACKEND.utils.UtilityMethods;

@Service
public class NationaliteService {

    @Autowired
    private NationaliteRepo nationaliteRepo;

    @Autowired
    private UtilityMethods utilityMethods;

    
    // add new matiere method
    public List<NationaliteModel> getAllNationaliteData() {
        return nationaliteRepo.findAll();
    }

    // add new matiere method
    public void addNewNationaliteData(NationaliteModel nationaliteModel) {

        // generation du code
        Integer code = utilityMethods.codeGenerator("NAT_CODE", "T_NATIONALITE");

        // version definie sur 1 par default a la creation
        int version = 1;
        
        // getting datetime from utilityMethod class
        String currentDateTime = utilityMethods.getCurrentDateTime();


        nationaliteModel.setNatCode(code);
        nationaliteModel.setNatVersion(version);
        nationaliteModel.setNatDateCreation(currentDateTime);

        nationaliteRepo.save(nationaliteModel);

    }

    public void updateNationaliteData(Integer code, NationaliteModel nationaliteModel) {

        Integer newVersion = utilityMethods.getCurrentVersion(code, "NAT_CODE", "T_NATIONALITE", "NAT_VERSION") + 1;

        NationaliteModel existingNationaliteData = nationaliteRepo.findById(code).orElseThrow(() -> new IllegalArgumentException("Could not find data using the given code"));

        existingNationaliteData.setNatLib(nationaliteModel.getNatLib());
        existingNationaliteData.setNatVersion(newVersion);
        existingNationaliteData.setNatModifierPar(nationaliteModel.getNatModifierPar());

        nationaliteRepo.save(existingNationaliteData);


    }

    public void deleteNationaliteData(int code) {

        if(nationaliteRepo.existsById(code)) {
            nationaliteRepo.deleteById(code);
        } else {
            throw new IllegalArgumentException("Could not find data with the provided code.");
        }

    }

    public Integer getTotalDataNumber(String tableName) {   
        return utilityMethods.getTotalNumberOfDonneesRef(tableName);
    }


}
