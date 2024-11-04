package GHEBACKEND.GHEBACKEND.service.DonneesReferentielles;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import GHEBACKEND.GHEBACKEND.model.DonneesReferentielles.ServiceModel;
import GHEBACKEND.GHEBACKEND.repository.DonneesReferentielles.ServiceRepo;
import GHEBACKEND.GHEBACKEND.utils.UtilityMethods;

@Service
public class ServiceService {

    @Autowired
    private ServiceRepo serviceRepo;

    @Autowired
    private UtilityMethods utilityMethods;

    
    public Integer getTotalDataNumber(String tableName) {   
        return utilityMethods.getTotalNumberOfDonneesRef(tableName);
    }

    public List<ServiceModel> getAllServiceData() {
        return serviceRepo.findAll();
    }

    public List<ServiceModel> getAllServiceInSelectedDirection(Integer code) {
        return serviceRepo.getServiceInSelectedDirection(code);
    }

    public void addNewService(Integer dirCode, ServiceModel serviceModel) {

        
        Integer code = utilityMethods.codeGenerator("SCE_CODE", "T_SERVICE");

         // version definie sur 1 par default a la creation
         Integer version = 1;
        
         // getting datetime from utilityMethod class
         String currentDateTime = utilityMethods.getCurrentDateTime();

         serviceModel.setSceCode(code);
         serviceModel.setDirCode(dirCode);
         serviceModel.setSceVersion(version);
         serviceModel.setSceDateCreation(currentDateTime);

         serviceRepo.save(serviceModel);

    }

    public void updateServiceData(Integer code, ServiceModel serviceModel) {

        Integer newVersion = utilityMethods.getCurrentVersion(code, "SCE_CODE", "T_SERVICE", "SCE_VERSION") + 1;

        ServiceModel existingServiceData = serviceRepo.findById(code).orElseThrow(() -> new IllegalArgumentException("Could not find data using the given code"));

        existingServiceData.setSceModifierPar(serviceModel.getSceModifierPar());
        existingServiceData.setSceVersion(newVersion);
        existingServiceData.setSceLib(serviceModel.getSceLib());
        
        serviceRepo.save(existingServiceData);
    }

    
    public void deleteServiceData(int code) {

        if(serviceRepo.existsById(code)) {
            serviceRepo.deleteById(code);
        } else {
            throw new IllegalArgumentException("Could not find data with the provided code.");
        }

    }


}
