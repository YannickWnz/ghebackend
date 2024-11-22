package GHEBACKEND.GHEBACKEND.service.DonneesReferentielles;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import GHEBACKEND.GHEBACKEND.model.DonneesReferentielles.DeviseModel;
import GHEBACKEND.GHEBACKEND.repository.DonneesReferentielles.DeviseRepo;
import GHEBACKEND.GHEBACKEND.utils.UtilityMethods;

@Service
public class DeviseService {

    @Autowired
    private DeviseRepo deviseRepo;

    @Autowired
    private UtilityMethods utilityMethods;
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    
    public List<DeviseModel> getAllDevise() {
        return deviseRepo.findAll();
    }

    public void addNewDevise(DeviseModel deviseModel) {

        Integer devCode = utilityMethods.codeGenerator("DEV_CODE", "T_DEVISE");

        Integer defaultVersion = 1;

        String currentDateTime = utilityMethods.getCurrentDateTime();

        deviseModel.setDevCode(devCode);
        deviseModel.setDevVersion(defaultVersion);
        deviseModel.setDevDateCreation(currentDateTime);

        deviseRepo.save(deviseModel);

    }

    
   
    public void updateDevise(Integer devCode, String devLib, String devModifierPar, boolean devStatus) {

        Integer newVersion = utilityMethods.getCurrentVersion(devCode, "DEV_CODE", "T_DEVISE", "DEV_VERSION") + 1;


        String updateQuery;
 
        if(devLib == null) {

            if(devStatus == true) {
                String setAllToFalse = "UPDATE T_DEVISE SET DEV_STATUS = ?";  

                boolean status = false;

                jdbcTemplate.update(
                    setAllToFalse, 
                    "false"
                );

            }
            updateQuery = "UPDATE T_DEVISE SET DEV_STATUS = ?, DEV_MODIFIER_PAR = ?, DEV_VERSION = ? WHERE DEV_CODE = ?";

            // running update query
            jdbcTemplate.update(
                updateQuery, 
                devStatus,
                devModifierPar,
                newVersion,
                devCode
            );

        } 
        else {

            updateQuery = "UPDATE T_DEVISE SET DEV_LIB = ?, DEV_MODIFIER_PAR = ?, DEV_VERSION = ?, DEV_STATUS = ? WHERE DEV_CODE = ?";

            // running update query
            jdbcTemplate.update(
                updateQuery, 
                devLib,
                devModifierPar,
                newVersion,
                devStatus,
                devCode
            );

        }

    }



    public void deleteDeviseData(Integer code) {

        if(deviseRepo.existsById(code)) {
            deviseRepo.deleteById(code);
        } else {
            throw new IllegalArgumentException("Couldnt find data ...");
        }

    }

    
    public Integer getTotalNumberOfData(String tableName) {   
        return utilityMethods.getTotalNumberOfDonneesRef(tableName);
    }


}
